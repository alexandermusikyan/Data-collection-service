package netris.demo.service.services;

import lombok.AllArgsConstructor;
import netris.demo.service.models.CameraDTO;
import netris.demo.service.models.CameraInformationDTO;
import netris.demo.service.models.Worker;
import netris.demo.service.properties.ApplicationProperties;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CameraService {

    private final ApplicationProperties applicationProperties;
    private final RequestService requestService;

    public List<CameraDTO> getAllCameras() throws InterruptedException {
        Vector<CameraDTO> result = new Vector<>();

        LinkedList<CameraInformationDTO> cameraInformations =
                new LinkedList<>(
                        List.of(
                                (CameraInformationDTO[]) requestService.getRequest(applicationProperties.getUrl(), CameraInformationDTO[].class)
                        )
                );

        Integer threadCount = applicationProperties.getThreadCount();
        Thread[] workers = new Worker[threadCount];

        while (!cameraInformations.isEmpty()) {
            for (int i = 0; i < threadCount; ++i) {
                if (workers[i] == null || !workers[i].isAlive()) {
                    CameraInformationDTO task = cameraInformations.removeLast();

                    workers[i] = new Worker(task, result, requestService);
                    workers[i].start();
                }
            }
        }

        for (int i = 0; i < threadCount; ++i) {
            if (workers[i] != null) {
                workers[i].join();
            }
        }

        return result;
    }
}
