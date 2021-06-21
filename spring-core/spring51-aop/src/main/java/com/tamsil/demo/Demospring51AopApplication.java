package com.tamsil.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demospring51AopApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Demospring51AopApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
//        SpringApplication.run(Demospring51AopApplication.class, args);
    }

}
