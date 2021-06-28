package me.kobeshow.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
@ComponentScan("me.kobeshow")
public class AppConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("classpath:/applicationContext.xml");
        context.setServletContext(servletContext);

        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }
}
