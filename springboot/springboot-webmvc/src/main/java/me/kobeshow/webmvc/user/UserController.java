package me.kobeshow.webmvc.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/hello")
    public @ResponseBody String hello() {
        return "hello";
    }

    @PostMapping("/users/create")
    public User create(@RequestBody User user) {
        return user;
    }

}
