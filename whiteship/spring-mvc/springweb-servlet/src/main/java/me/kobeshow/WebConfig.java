package me.kobeshow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//@ComponentScan(useDefaultFilters = false, includeFilters = @ComponentScan.Filter(Controller.class))
@ComponentScan
@EnableWebMvc
public class WebConfig {

//    @Bean
//    public HandlerMapping handlerMapping() {
//        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
//        return handlerMapping;
//    }
//
//    @Bean
//    public HandlerAdapter handlerAdapter() {
//        RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
//        return handlerAdapter;
//    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
