package me.kobeshow.springbootloggroup;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @PostConstruct
    public void initData() {
        Book book = new Book();
        book.setIsbn(RandomString.make(20));
        book.setTitle("Spring Boot Update");
        bookRepository.save(book);
    }
}
