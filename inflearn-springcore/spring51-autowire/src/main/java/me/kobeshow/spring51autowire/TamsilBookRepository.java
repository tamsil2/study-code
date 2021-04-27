package me.kobeshow.spring51autowire;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository @Primary
public class TamsilBookRepository implements BookRepository{
}
