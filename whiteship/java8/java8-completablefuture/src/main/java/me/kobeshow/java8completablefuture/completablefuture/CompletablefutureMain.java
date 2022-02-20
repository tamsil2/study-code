package me.kobeshow.java8completablefuture.completablefuture;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletablefutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 리턴이 없는 작업일 경우 - runAsync
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        future.get();

        // 리턴타입이 있는 작업일 경우
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(future2.get());

        // 콜백을 주되 리턴값이 있는 경우
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase(Locale.ROOT);
        });
        System.out.println(future3.get());

        // 콜백을 주되 리턴값이 없는 경우
        CompletableFuture<Void> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase(Locale.ROOT));
        });
        future4.get();

        // 리턴도 없고 그냥 Runnable을 실행
        CompletableFuture<Void> future5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        future5.get();
    }
}
