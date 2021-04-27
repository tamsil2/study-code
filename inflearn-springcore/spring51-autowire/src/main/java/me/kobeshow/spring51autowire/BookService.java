package me.kobeshow.spring51autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class BookService {

    @Autowired
    List<BookRepository> bookRepository;

    @PostConstruct
    public void setup() {
        this.bookRepository.forEach(System.out::println);
    }
}
