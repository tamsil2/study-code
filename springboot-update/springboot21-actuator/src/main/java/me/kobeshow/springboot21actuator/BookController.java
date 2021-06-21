package me.kobeshow.springboot21actuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/book")
    public String book() {
        return "Hello Book";
    }
}
