package com.kobeshow.springbootjar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootJarApplication {

    @GetMapping("/")
    public String hello() {
        return "Hello Spring";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJarApplication.class, args);
    }

}
