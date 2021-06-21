package com.kobeshow.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//        SpringApplication app = new SpringApplication(Application.class);
//        app.setBanner(new Banner() {
//            @Override
//            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//                out.println("=====================");
//                out.println("Hone Banner");
//                out.println("=====================");
//            }
//        });
//        app.run(args);
        new SpringApplicationBuilder()
                .sources(Application.class)
                .run(args);
    }

}
