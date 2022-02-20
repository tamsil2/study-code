package me.kobeshow.configurationproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    ServiceProperties serviceProperties;

    @GetMapping("/hello")
    public String hello() {
        return serviceProperties.getMessage();
    }
}
