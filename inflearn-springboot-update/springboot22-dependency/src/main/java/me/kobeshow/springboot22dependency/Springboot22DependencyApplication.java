package me.kobeshow.springboot22dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Springboot22DependencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot22DependencyApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            ChickenProperties chickenProperties;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                System.out.println(chickenProperties.getName());
                System.out.println(chickenProperties.getCount());
                System.out.println(chickenProperties.getSize());
            }
        };
    }
}
