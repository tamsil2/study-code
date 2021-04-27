package me.kobeshow.springbootautoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

//    @GetMapping("/book")
//    public String book() {
//        bookService.hello();
//        return "hello";
//    }

    @Scheduled(fixedDelay = 1000 * 2)
    public void hi() {
        System.out.println("hi " + Thread.currentThread().getName());
    }

    @GetMapping("/book")
    public Book book() {
        bookRepository.findAll().forEach(System.out::println);
        Book book = bookRepository.findByIsbn("123123");

        return book;
    }
}
