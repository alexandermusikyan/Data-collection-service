package netris.demo.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "main")
public class ApplicationProperties {
    private String url;
    private Integer threadCount;
}
