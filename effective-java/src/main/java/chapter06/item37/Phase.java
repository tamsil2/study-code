package chapter06.item37;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

// 코드 37-6 중첩 EnumMap으로 데이터와 열거 타입 쌍을 연결했다.
public enum Phase {
    SOLID, LIQUID, GAS, PLASMA;
    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID),
        IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS);

        private final Phase from;
        private final Phase to;
        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        // 상전이 맵을 초기화한다.
        private static final Map<Phase, Map<Phase, Transition>> m =
                Stream.of(values()).collect(groupingBy(t -> t.from, () -> new EnumMap<>(Phase.class),
                        toMap(t -> t.to, t -> t, (x, y) -> y, () -> new EnumMap<Phase, Transition>(Phase.class))));

        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }
}
