package me.kobeshow.querydsl2;

import com.querydsl.core.types.QBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void test() {
        Book book = new Book();
        book.setTitle("spring");
        book.setContent("data");
        Book newBook = bookRepository.save(book);

        assertTrue(bookRepository.contains(newBook));

        Optional<Book> ring = bookRepository.findOne(QBook.book.title.contains("ring"));
        assertTrue(ring.isPresent());
    }
}
