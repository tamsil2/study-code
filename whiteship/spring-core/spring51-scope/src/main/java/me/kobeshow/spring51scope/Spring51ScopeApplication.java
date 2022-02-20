package me.kobeshow.spring51scope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/app.properties")
public class Spring51ScopeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring51ScopeApplication.class, args);
    }

}
