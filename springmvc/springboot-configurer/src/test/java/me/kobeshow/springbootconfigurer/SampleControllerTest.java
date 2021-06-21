package me.kobeshow.springbootconfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.StringEndsWith;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ObjectMapper objectMapper;

//    @Autowired
//    Marshaller marshaller;

    @Test
    public void hello() throws Exception{
        this.mockMvc.perform(get("/hello/tamsil"))
                .andDo(print())
                .andExpect(content().string("hello tamsil"));
    }

    @Test
    public void hi() throws Exception {
        this.mockMvc.perform(get("/hi")
                    .param("name", "tamsil"))
                .andDo(print())
                .andExpect(content().string("hi tamsil"));
    }

    @Test
    public void helloByIdTest() throws Exception {
        Person person = new Person();
        person.setName("tamsil");
        Person savedPerson = personRepository.save(person);

        this.mockMvc.perform(get("/helloid")
                    .param("id", savedPerson.getId().toString()))
                .andDo(print())
                .andExpect(content().string("hello tamsil"));
    }

    @Test
    public void helloStatic() throws Exception {
        this.mockMvc.perform(get("/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Index")));
    }

    @Test
    public void helloStatic2() throws Exception {
        this.mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Mobile")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL));
    }

    @Test
    public void stringMessage() throws Exception {
        this.mockMvc.perform(get("/message")
                .content("hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void jsonMessage() throws Exception {
        Person person = new Person();
        person.setId(2021l);
        person.setName("tamsil");

        String jsonString = objectMapper.writeValueAsString(person);

        this.mockMvc.perform(get("/jsonMessage")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2021))
                .andExpect(jsonPath("$.name").value("tamsil"));
    }

//    @Test
//    public void xmlMessage() throws Exception {
//        Person person = new Person();
//        person.setId(2021l);
//        person.setName("tamsil");
//
//        StringWriter stringWriter = new StringWriter();
//        Result result = new StreamResult(stringWriter);
//        marshaller.marshal(person, result);
//        String xmlString = stringWriter.toString();
//
//        this.mockMvc.perform(get("/jsonMessage")
//                .contentType(MediaType.APPLICATION_XML)
//                .accept(MediaType.APPLICATION_XML)
//                .content(xmlString))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(xpath("person/name").string("tamsil"))
//                .andExpect(xpath("person/id").string("2021"));
//    }
}
