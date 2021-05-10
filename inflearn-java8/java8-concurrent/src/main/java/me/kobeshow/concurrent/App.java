package me.kobeshow.concurrent;

import java.util.Arrays;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        executorService.submit(getRunnable("Hello"));
//        executorService.submit(getRunnable("Tamsil"));
//        executorService.submit(getRunnable("The"));
//        executorService.submit(getRunnable("Java"));
//        executorService.submit(getRunnable("Thread"));

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> tamsil = () -> {
            Thread.sleep(1000L);
            return "Tamsil";
        };

        String s = executorService.invokeAny(Arrays.asList(hello, java, tamsil));
        System.out.println(s);
        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
