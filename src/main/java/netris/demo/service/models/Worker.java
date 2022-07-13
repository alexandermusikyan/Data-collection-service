package netris.demo.service.models;

import lombok.AllArgsConstructor;
import netris.demo.service.services.RequestService;
import java.util.Vector;

@AllArgsConstructor
public class Worker extends Thread {
    private CameraInformationDTO task;
    private Vector<CameraDTO> result;
    private RequestService requestService;

    @Override
    public void run() {
        super.run();

        SourceDataUrlDTO sourceDataUrlDTO = (SourceDataUrlDTO)requestService.getRequest(task.getSourceDataUrl(), SourceDataUrlDTO.class);
        TokenDataUrlDTO tokenDataUrlDTO = (TokenDataUrlDTO)requestService.getRequest(task.getTokenDataUrl(), TokenDataUrlDTO.class);

        CameraDTO cameraDTO = new CameraDTO()
                .setId(task.getId())
                .setUrlType(sourceDataUrlDTO.getUrlType())
                .setVideoUrl(sourceDataUrlDTO.getVideoUrl())
                .setValue(tokenDataUrlDTO.getValue())
                .setTtl(tokenDataUrlDTO.getTtl());

        result.add(cameraDTO);
    }
}
