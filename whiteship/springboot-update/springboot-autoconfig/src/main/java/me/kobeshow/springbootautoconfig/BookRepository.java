package me.kobeshow.springbootautoconfig;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query("select * from Book where isbn = :isbn")
    Book findByIsbn(@Param("isbn") String isbn);
}
