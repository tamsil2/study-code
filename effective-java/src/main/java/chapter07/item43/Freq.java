package chapter07.item43;

import java.util.Map;
import java.util.TreeMap;

// map.merge를 이용해 구현한 빈도표 - 람다 방식과 메서드 참조 방식을 비교해보자.
public class Freq {
    public static void main(String[] args) {
        Map<String, Integer> frequencyTable = new TreeMap<>();

        for(String s : args) {
            frequencyTable.merge(s, 1, (count, incr) -> count + incr); // 람다
        }
        System.out.println(frequencyTable);

        frequencyTable.clear();
        for(String s : args) {
            frequencyTable.merge(s, 1, Integer::sum);
        }
        System.out.print(frequencyTable);
    }
}
