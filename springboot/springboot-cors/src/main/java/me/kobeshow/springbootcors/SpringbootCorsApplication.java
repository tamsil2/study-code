package me.kobeshow.springbootcors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:18080")
@RestController
public class SpringbootCorsApplication {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCorsApplication.class, args);
    }

}
