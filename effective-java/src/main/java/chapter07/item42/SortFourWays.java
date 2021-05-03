package chapter07.item42;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 함수 객체로 정의하기
public class SortFourWays {
    public static void main(String[] args) {
        List<String> words = Arrays.asList(args);

        // 코드 42-1 익명 클래스의 인스턴스를 함수 객체로 사용 - 낡은 기법이다!
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        System.out.println(words);
        Collections.shuffle(words);

        // 코드 42-2 람다식을 함수 객체로 사용 - 익명 클래스 대체
        Collections.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
        System.out.println(words);
        Collections.shuffle(words);

        // 람다 자리에 비교자 생성 메서드(메서드 참조와 함께)를 사용
        Collections.sort(words, Comparator.comparingInt(String::length));
        System.out.println(words);
        Collections.sort(words);

        // 비교자 생성 메서드와 List.sort를 사용
        words.sort(Comparator.comparingInt(String::length));
        System.out.println(words);
    }
}
