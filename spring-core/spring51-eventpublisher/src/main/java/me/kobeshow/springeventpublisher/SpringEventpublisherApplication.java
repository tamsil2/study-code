package me.kobeshow.springeventpublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringEventpublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEventpublisherApplication.class, args);
    }

}
