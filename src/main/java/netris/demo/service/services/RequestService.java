package netris.demo.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

@Service
public class RequestService {

    public Object getRequest(String url, Class responseClass) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Object cameraInformations;

        try {
            cameraInformations = mapper.readValue(output, responseClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return cameraInformations;
    }
}
