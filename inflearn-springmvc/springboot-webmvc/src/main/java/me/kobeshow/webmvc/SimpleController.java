package me.kobeshow.webmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SimpleController {

    @GetMapping("/events")
    @ResponseBody
    public String events() {
        return "events";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public String getEventsWithId(@PathVariable int id) {
        return "event " + id;
    }

    @DeleteMapping("/events/{id}")
    @ResponseBody
    public String removeAnEvents(@PathVariable int id) {
        return "event";
    }
}
