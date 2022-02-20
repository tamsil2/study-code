package me.kobeshow.springbootautoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class SpringbootAutoconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAutoconfigApplication.class, args);
    }

}
