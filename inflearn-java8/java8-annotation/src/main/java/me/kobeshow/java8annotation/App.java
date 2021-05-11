package me.kobeshow.java8annotation;

import java.util.Arrays;

@Chicken("양념")
@Chicken("마늘간장")
public class App {
    public static void main(String[] args) {
        Chicken[] chickens = App.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println(c.value());
        });

        // 컨테이너 타입으로 가져오는 방법
        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });
    }
}
