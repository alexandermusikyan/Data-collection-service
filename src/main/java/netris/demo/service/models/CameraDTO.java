package netris.demo.service.models;

import lombok.*;
import lombok.experimental.Accessors;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CameraDTO {
    private Integer id;

    private String urlType;
    private String videoUrl;

    private String value;
    private Integer ttl;
}
