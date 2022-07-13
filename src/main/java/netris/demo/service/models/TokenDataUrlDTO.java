package netris.demo.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenDataUrlDTO {
    private String value;
    private Integer ttl;
}
