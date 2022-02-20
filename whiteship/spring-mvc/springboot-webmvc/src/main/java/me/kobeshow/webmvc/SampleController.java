package me.kobeshow.webmvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/hello")
public class SampleController {

    @RequestMapping(value = "/hello",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetHelloMapping
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/header", headers = HttpHeaders.AUTHORIZATION)
    public String header() {
        return "header";
    }

    @GetMapping(value = "/header2", headers = "!" + HttpHeaders.AUTHORIZATION)
    public String header2() {
        return "header2";
    }

    @GetMapping(value = "/header2", headers = HttpHeaders.AUTHORIZATION + "=" + "111")
    public String header3() {
        return "header3";
    }

    @GetMapping(value = "/param", params = "name")
    public String param() {
        return "param";
    }

    @GetMapping(value = "/param2", params = "name=spring")
    public String param2() {
        return "param2";
    }
}
