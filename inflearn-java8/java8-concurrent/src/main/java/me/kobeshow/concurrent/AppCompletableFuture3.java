package me.kobeshow.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// 두개의 결과가 동시에 와서 같이 출력할 경우
public class AppCompletableFuture3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(future.get());
    }

}
