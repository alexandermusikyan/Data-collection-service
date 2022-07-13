package netris.demo.service.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "main")
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationProperties {
    private String url;
    private Integer threadCount;
}
