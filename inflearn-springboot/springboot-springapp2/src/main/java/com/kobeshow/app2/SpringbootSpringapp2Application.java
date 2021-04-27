package com.kobeshow.app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootSpringapp2Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootSpringapp2Application.class);
//        app.addListeners(new SampleListener());
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
//        SpringApplication.run(SpringbootSpringapp2Application.class, args);
    }

}
