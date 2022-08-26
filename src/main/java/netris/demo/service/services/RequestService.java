package netris.demo.service.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class RequestService {

    public static <T> T getRequest(String url, Class<T> responseClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            return mapper.readValue(new URL(url), responseClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
