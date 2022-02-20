package me.kobeshow.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootProfileApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootProfileApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
//        SpringApplication.run(SpringbootProfileApplication.class, args);
    }

}
