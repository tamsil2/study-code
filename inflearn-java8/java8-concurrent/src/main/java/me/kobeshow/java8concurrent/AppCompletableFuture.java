package me.kobeshow.java8concurrent;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AppCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 리턴값이 없는 경우
        System.out.println("=== 리턴값이 없는 경우 ===");
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        future.get();

        // 리턴값이 있는 경우
        System.out.println("=== 리턴값이 있는 경우 ===");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(future1.get());

        // 콜백을 주는 경우
        System.out.println("=== 콜백이 있는 경우 ===");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase(Locale.ROOT);
        });
        System.out.println(future2.get());

        // 콜백인데 리턴이 없는 경우
        CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase(Locale.ROOT));
        });
        future3.get();

        // 결과값을 참고도 하지 않고 그냥 실행만 되는 경우
        CompletableFuture<Void> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        future4.get();

    }
}
