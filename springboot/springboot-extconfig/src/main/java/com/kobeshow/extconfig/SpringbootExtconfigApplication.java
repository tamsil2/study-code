package com.kobeshow.extconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(KobeshowProperties.class)
public class SpringbootExtconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExtconfigApplication.class, args);
    }

}
