package me.kobeshow.springboot23graceful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Springboot23GracefulApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot23GracefulApplication.class, args);
    }

    @RestController
    class SlowController {

        @GetMapping("/slow")
        public String slow() throws InterruptedException {
            System.out.println("got the request");
            Thread.sleep(10000L);
            return "slow";
        }
    }
}
