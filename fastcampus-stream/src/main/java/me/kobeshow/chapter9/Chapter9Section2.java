package me.kobeshow.chapter9;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter9Section2 {
    public static void main(String[] args) {
//        if (returnTrue() || returnFalse()) {
//            System.out.println("true");
//        }

        // 아래의 경우에는 returnTrue(), returnFalse()가 모두 호출된 후에야 메서드가 실행된다
        // 이유는 메서드 매개변수의 값을 모두 안 다음에야 메서드가 실행되기 때문이다.
        if (or(returnTrue(), returnFalse())) {
            System.out.println("true");
        }

        // 아래와 같이 Supplier 형태로 메개변수를 만들어 주면 앞에 Supplier가 조건에 성립되면 뒤에 Supplier는 실행을 하지 않는다
        if (lazyOr(() -> returnTrue(), () -> returnFalse())) {
            System.out.println("true");
        }

        // Lazy Eveluation Stream활용 예시
        Stream<Integer> integerStream = Stream.of(3, -2, 5, 8, -3, 10)
                .filter(x -> x > 0)
                .peek(x -> System.out.println("peeking " + x))
                .filter(x -> x % 2 == 0);
        System.out.println("Before collect");

        List<Integer> integers = integerStream.collect(Collectors.toList());
        System.out.println("After collect: " + integers);
    }

    public static boolean or(boolean x, boolean y) {
        return x || y;
    }

    public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
        return x.get() || y.get();
    }

    public static boolean returnTrue() {
        System.out.println("Returning true");
        return true;
    }

    public static boolean returnFalse() {
        System.out.println("Returning false");
        return false;
    }
}
