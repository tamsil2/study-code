package me.kobeshow.springbootdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringbootDatajpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDatajpaApplication.class, args);
    }

}
