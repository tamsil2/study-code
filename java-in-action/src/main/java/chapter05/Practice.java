package chapter05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Practice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. 2011년에 일어난 모든 트랜잭션을 찾아서 값을 오름차순으로 정렬
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        // 2. 거래자가 근무하는 모든 도시를 중복없이 나열
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        // 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        // 4. 모든 거래자의 이름을 알파벳수능로 정렬
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        // 5. 밀라노에 거래자가 있는지 확인
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        // 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 7. 전체 트랜잭션 중 최댓값
        Optional<Integer> highstValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        // 8. 전체 트랜잭션 중 최소값
        Optional<Transaction> smallestTransaction =
                transactions.stream()
                .reduce((t1, t2) ->
                        t1.getValue() < t2.getValue() ? t1 : t2);
    }
}
