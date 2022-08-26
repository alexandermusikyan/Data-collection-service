package netris.demo.service.services;

import netris.demo.service.models.CameraDTO;
import netris.demo.service.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig
public class CameraServiceTests {

    @Mock
    private ApplicationProperties applicationProperties;

    @InjectMocks
    private CameraService cameraService;

    @Test
    public void testGetRequest() throws InterruptedException {
        Mockito.when(applicationProperties.getUrl()).thenReturn("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");
        Mockito.when(applicationProperties.getThreadCount()).thenReturn(4);
        List<CameraDTO> response = cameraService.getAllCameras();

        Integer rightSize = 4;
        Assertions.assertEquals(response.size(), rightSize);

        String rightUrlTypeForFirstCamera = "LIVE";
        CameraDTO firstCamera = response.stream().filter(camera -> camera.getId().equals(1)).findFirst().orElseThrow();
        Assertions.assertEquals(firstCamera.getUrlType(), rightUrlTypeForFirstCamera);

        String rightVideoUrlForSecondCamera = "rtsp://127.0.0.1/2";
        CameraDTO secondCamera = response.stream().filter(camera -> camera.getId().equals(20)).findFirst().orElseThrow();
        Assertions.assertEquals(secondCamera.getVideoUrl(), rightVideoUrlForSecondCamera);
    }
}
