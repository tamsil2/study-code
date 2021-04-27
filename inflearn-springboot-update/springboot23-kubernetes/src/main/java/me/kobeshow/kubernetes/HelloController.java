package me.kobeshow.kubernetes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    ApplicationAvailability availability;

    @Autowired
    LocalHostService localHostService;

    @GetMapping("/hello")
    public String hello() {
        return "Application is now " + availability.getLivenessState()
                + " " + availability.getReadinessState()
                + " " + localHostService.getLocalHostInfo();
    }
}
