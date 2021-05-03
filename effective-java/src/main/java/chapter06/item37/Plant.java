package chapter06.item37;

// EnumMap을 사용해 열거 타입에 데이터를 연관시키기

import chapter06.item34.Planet;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

// 식물을 아주 단순하게 표현한 클래스
public class Plant {
    enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Plant[] garden = {
                new Plant("바질", LifeCycle.ANNUAL),
                new Plant("캐러웨이", LifeCycle.BIENNIAL),
                new Plant("딜", LifeCycle.ANNUAL),
                new Plant("라벤더", LifeCycle.PERENNIAL),
                new Plant("파슬리", LifeCycle.BIENNIAL),
                new Plant("로즈마리", LifeCycle.PERENNIAL)
        };

        // 코드 37-1 ordinal()을 배열 인덱스로 사용 - 따라 하지 말 것!
        Set<Plant>[] plantsByLifeCycleArr = (Set<Plant>[]) new Set[LifeCycle.values().length];
        for(int i=0; i<plantsByLifeCycleArr.length; i++)
            plantsByLifeCycleArr[i] = new HashSet<>();
        for(Plant p : garden)
            plantsByLifeCycleArr[p.lifeCycle.ordinal()].add(p);
        // 결과 출력
        for(int i=0; i<plantsByLifeCycleArr.length; i++) {
            System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plantsByLifeCycleArr[i]);
        }

        // 코드 37-2 EnumMap을 사용해 데이터와 열거 타입을 매핑한다.
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<LifeCycle, Set<Plant>>(Plant.LifeCycle.class);
        for(Plant.LifeCycle lc : Plant.LifeCycle.values())
            plantsByLifeCycle.put(lc, new HashSet<>());
        for(Plant p : garden)
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        System.out.println(plantsByLifeCycle);

        // 코드 37-3 스트림을 사용한 코드 1 - EnumMap을 사용하지 않는다!
        System.out.println(Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle)));

        // 코드 37-4 스트림을 사용한 코드 2 - EnumMap을 이용해 데이터와 열거 타입을 매핑했다.
        System.out.println(Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toSet())));
    }
}
