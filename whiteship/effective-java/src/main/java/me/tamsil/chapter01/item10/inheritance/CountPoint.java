package me.tamsil.chapter01.item10.inheritance;

import me.tamsil.chapter01.item10.Point;

import java.util.concurrent.atomic.AtomicInteger;

public class CountPoint extends Point {
    private static final AtomicInteger counter = new AtomicInteger();

    public CountPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public static int numberCreated() {
        return counter.get();
    }
}
