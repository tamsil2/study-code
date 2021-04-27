package me.kobeshow.querydsl2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookRepository extends CustomRepository<Book, Long>, QuerydslPredicateExecutor<Book> {
}
