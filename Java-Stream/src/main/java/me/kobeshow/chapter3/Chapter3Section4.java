package me.kobeshow.chapter3;

import me.kobeshow.chapter3.util.TriFunction;

public class Chapter3Section4 {
    public static void main(String[] args) {
        TriFunction<Integer, Integer, Integer, Integer> addThreeNumbers = (x, y, z) -> x + y + z;
        Integer result = addThreeNumbers.apply(3, 2, 5);
        System.out.println("result = " + result);
    }
}
