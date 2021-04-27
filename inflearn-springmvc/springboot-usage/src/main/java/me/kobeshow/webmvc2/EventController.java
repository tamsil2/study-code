package me.kobeshow.webmvc2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"event"})
public class EventController {

    @GetMapping("/events/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable Integer id) {
        Event event = new Event();
        event.setId(id);
        return event;
    }

    @GetMapping("/matrix/{id}")
    @ResponseBody
    public Event getEventMatrix(@PathVariable Integer id, @MatrixVariable String name) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        return event;
    }

//    @PostMapping("/events")
//    @ResponseBody
//    public Event getEventPost(@RequestParam String name, @RequestParam Integer limit) {
//        Event event = new Event();
//        event.setName(name);
//        event.setLimit(limit);
//        return event;
//    }

    @GetMapping("/events/form")
    public String eventsForm(Model model, HttpSession httpSession) {
        Event newEvent = new Event();
        newEvent.setLimit(50);
        model.addAttribute("event", newEvent);
//        httpSession.setAttribute("event", newEvent);
        return "/events/form";
    }

    @PostMapping("/events")
    public String getEventPost(@Validated @ModelAttribute Event event,
                               BindingResult bindingResult,
                               Model model) {
        if(bindingResult.hasErrors()) {
            return "/events/form";
        }

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        model.addAttribute(eventList);
        return "redirect:/events/list";
    }

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsFormNameSubmit(@Validated @ModelAttribute Event event,
                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "/events/form-name";
        }
        return "redirect:/events/list/limit";
    }

    @GetMapping("/events/list/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "/events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsFormLimitSubmit(@Validated @ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        RedirectAttributes attributes) {
        if(bindingResult.hasErrors()) {
            return "/events/form-limit";
        }
        sessionStatus.setComplete();
        // RedirectAttributes에 담는 경우
//        attributes.addAttribute("name", event.getName());
//        attributes.addAttribute("limit", event.getLimit());
        // FlashAttributes에 담는 경우
        attributes.addFlashAttribute("newEvent", event);
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(// @RequestParam String name,
                            // @RequestParam Integer limit,
                            @ModelAttribute("newEvent") Event event,
                            Model model,
                            @SessionAttribute LocalDateTime visitTime) {
        System.out.println(visitTime);

        // @RequestParam으로 받는 경우
//        Event newEvent = new Event();
//        newEvent.setName(name);
//        newEvent.setLimit(limit);

        Event spring = new Event();
        spring.setName("spring");
        spring.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(spring);
        eventList.add(event);

        model.addAttribute(eventList);

        return "/events/list";
    }


}
