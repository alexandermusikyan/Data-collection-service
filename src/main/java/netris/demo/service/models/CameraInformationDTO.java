package netris.demo.service.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "sourceDataUrl", "tokenDataUrl" })
public class CameraInformationDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("sourceDataUrl")
    private String sourceDataUrl;
    @JsonProperty("tokenDataUrl")
    private String tokenDataUrl;
}
