package com.kobeshow.extconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

//    @Value("${kobeshow.name}")
//    private String name;
//
//    @Value("${kobeshow.age}")
//    private int age;
//
//    @Value("${kobeshow.fullName}")
//    private String fullName;

    @Autowired
    KobeshowProperties kobeshowProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("======================");
        System.out.println(kobeshowProperties.getName());
        System.out.println(kobeshowProperties.getAge());
        System.out.println(kobeshowProperties.getSessionTimeout());
        System.out.println("======================");
    }
}
