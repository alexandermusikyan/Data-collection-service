package netris.demo.service.services;

import netris.demo.service.models.SourceDataUrlDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class RequestServiceTests {

	@Test
	public void testGetRequest() {
		String sourceDataUrl = "http://www.mocky.io/v2/5c51b230340000094f129f5d";
		SourceDataUrlDTO response = RequestService.getRequest(sourceDataUrl, SourceDataUrlDTO.class);

		SourceDataUrlDTO rightAnswer = new SourceDataUrlDTO().setUrlType("LIVE").setVideoUrl("rtsp://127.0.0.1/1");

		Assertions.assertEquals(response.getUrlType(), rightAnswer.getUrlType());
		Assertions.assertEquals(response.getVideoUrl(), rightAnswer.getVideoUrl());
	}
}
