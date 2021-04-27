package me.kobeshow.java8concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

// 에러 처리에 관한 케이스
public class AppCompletableFuture5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        boolean throwError = true;
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });
        System.out.println(hello.get());

        CompletableFuture<String> hello2 = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello2 " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if(ex != null){
                System.out.println(ex);
                return "ERROR!";
            }
            return result;
        });

        System.out.println(hello2.get());
    }
}
