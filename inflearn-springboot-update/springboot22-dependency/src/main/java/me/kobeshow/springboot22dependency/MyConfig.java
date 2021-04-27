package me.kobeshow.springboot22dependency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyConfig {

    @Bean
    public Object myBean() {
        return new Object();
    }
}
