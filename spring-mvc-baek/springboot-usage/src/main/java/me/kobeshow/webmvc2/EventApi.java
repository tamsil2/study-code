package me.kobeshow.webmvc2;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

//@RestController
@Controller
@RequestMapping("/api/events")
public class EventApi {

    @ExceptionHandler
    public ResponseEntity errorHandler() {
        return ResponseEntity.badRequest().body("can't create event as ...");
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
        // save event
//        MediaType contentType = request.getHeaders().getContentType();;
//        System.out.println(contentType);
//        return request.getBody();
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(event);
    }

}
