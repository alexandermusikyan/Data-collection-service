package netris.demo.service.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import netris.demo.service.models.*;
import netris.demo.service.properties.ApplicationProperties;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CameraService {

    private final ApplicationProperties applicationProperties;

    @SneakyThrows
    public List<CameraDTO> getAllCameras() {
        List<CameraInformationDTO> cameraInfos =
                List.of(RequestService.getRequest(applicationProperties.getUrl(), CameraInformationDTO[].class)
                );

        ExecutorService executorService = Executors.newFixedThreadPool(applicationProperties.getThreadCount());

        List<Future<CameraDTO>> futures = executorService.invokeAll(
                cameraInfos
                        .stream()
                        .map(CallableTask::new)
                        .collect(Collectors.toList())
        );

        executorService.shutdown();

        return futures.stream().map(future ->
        {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    private static class CallableTask implements Callable<CameraDTO> {
        private final CameraInformationDTO cameraInf;

        public CallableTask(final CameraInformationDTO cameraInf) {
            this.cameraInf = cameraInf;
        }

        @Override
        public CameraDTO call() {
            SourceDataUrlDTO sourceDataUrlDTO = RequestService.getRequest(cameraInf.getSourceDataUrl(), SourceDataUrlDTO.class);
            TokenDataUrlDTO tokenDataUrlDTO = RequestService.getRequest(cameraInf.getTokenDataUrl(), TokenDataUrlDTO.class);

            return new CameraDTO()
                    .setId(cameraInf.getId())
                    .setUrlType(sourceDataUrlDTO.getUrlType())
                    .setVideoUrl(sourceDataUrlDTO.getVideoUrl())
                    .setValue(tokenDataUrlDTO.getValue())
                    .setTtl(tokenDataUrlDTO.getTtl());
        }
    }
}
