package chapter03.TotalUsed;

import chapter03.Apple;
import chapter03.Color;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class SummaryProcess {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        // 1. 코드 전달
        inventory.sort(new AppleComparator());

        // 2. 익명 클래스 사용
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        // 3. 람다 표현식 사용
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
        inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());

        Comparator<Apple> c = comparing((Apple a) -> a.getWeight());

        inventory.sort(comparing(apple -> apple.getWeight()));

        inventory.sort(comparing(Apple::getWeight));
    }

}
