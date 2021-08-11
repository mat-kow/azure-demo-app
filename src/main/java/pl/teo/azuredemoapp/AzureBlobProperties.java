package pl.teo.azuredemoapp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@ConfigurationProperties("azure.myapp")
@Component
public class AzureBlobProperties {
    private String connectionString;
    private String container;
}
