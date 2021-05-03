package chapter07;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {
    public static void main(String[] args) {
        System.out.println("sideEffectSum : " + sideEffectSum(10));
        System.out.println("sideEffectParallelSum : " + sideEffectParallelSum(10));
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public long iterativeSum(long n) {
        long result = 0;
        for(long i=1L; i<=n; i++) {
            result += i;
        }
        return result;
    }

    public long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static class Accumulator {
        public long total = 0;
        public void add(long value) {
            total += value;
        }
    }
}
