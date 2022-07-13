package netris.demo.service.controllers;

import lombok.RequiredArgsConstructor;
import netris.demo.service.models.CameraDTO;
import netris.demo.service.services.CameraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/camera")
public class CameraController {
    private final CameraService cameraService;

    @GetMapping(
            path = "/all_cameras",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CameraDTO>> getAllCameras() throws InterruptedException {
        List<CameraDTO> solutionDTOs = cameraService.getAllCameras();

        return new ResponseEntity<>(solutionDTOs, HttpStatus.OK);
    }
}
