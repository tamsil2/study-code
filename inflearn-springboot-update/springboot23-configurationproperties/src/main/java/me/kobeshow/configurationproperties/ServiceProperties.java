package me.kobeshow.configurationproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties("service")
public class ServiceProperties {

    private String message;

    public ServiceProperties(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
