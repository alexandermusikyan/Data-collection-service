package netris.demo.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class SourceDataUrlDTO {
    private String urlType;
    private String videoUrl;
}
