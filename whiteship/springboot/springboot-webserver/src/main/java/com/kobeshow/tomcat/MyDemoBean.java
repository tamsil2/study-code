package com.kobeshow.tomcat;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

//@Component
public class MyDemoBean implements ApplicationListener<ApplicationStartedEvent> {

    private ServletContext servletContext;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        ConfigurableApplicationContext applicationContext = applicationStartedEvent.getApplicationContext();
        this.servletContext = ((WebApplicationContext) applicationContext).getServletContext();
        System.out.println("applicationContext = " + applicationContext);
    }
}
