package me.kobeshow.springbootdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Async
    ListenableFuture<List<Comment>> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);
}
