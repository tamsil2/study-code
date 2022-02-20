package me.kobeshow.java8completablefuture.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorMain {
    public static void main(String[] args) {
        // 일반적인 방법
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService1.submit(() -> {
            System.out.println("Thread " + Thread.currentThread().getName());
        });
        executorService1.shutdown();

        // 쓰레드를 여러개로 만들 경우
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        executorService2.submit(getRunnable("Hello"));
        executorService2.submit(getRunnable("Tamsil"));
        executorService2.submit(getRunnable("The"));
        executorService2.submit(getRunnable("Java"));
        executorService2.submit(getRunnable("Thread"));

        executorService2.shutdown();

        // 스케줄
        ScheduledExecutorService executorService3 = Executors.newSingleThreadScheduledExecutor();
        executorService3.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS); // 3초 후에 실행
        executorService3.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS); // 1초마다 2회 실행
    }
    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }


}
