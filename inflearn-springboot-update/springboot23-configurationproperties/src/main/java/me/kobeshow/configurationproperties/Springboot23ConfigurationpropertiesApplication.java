package me.kobeshow.configurationproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Springboot23ConfigurationpropertiesApplication {

    @Value("${service.message}")
    private String message;

    public static void main(String[] args) {
        SpringApplication.run(Springboot23ConfigurationpropertiesApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> System.out.println(message);
    }
}
