package me.kobeshow.chapter3.util;

import java.util.function.Function;

public class Adder implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
