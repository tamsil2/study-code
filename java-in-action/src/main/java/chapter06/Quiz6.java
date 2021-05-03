package chapter06;

import java.util.List;
import java.util.function.Predicate;

public class Quiz6 {
    public static void main(String[] args) {

    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    public static <A>List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for(A item : list) {
            if(!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
