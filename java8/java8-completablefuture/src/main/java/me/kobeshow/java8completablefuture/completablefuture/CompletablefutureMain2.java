package me.kobeshow.java8completablefuture.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletablefutureMain2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        // 두 작업이 연관관계가 있는 경우
        CompletableFuture<String> future = hello.thenCompose(CompletablefutureMain2::getWorld);
        System.out.println(future.get());

        // 두 작업이 따로 실행하는 경우
        hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(future.get());

        // 여러 Task가 실행되고 다 끝났을때 처리되는 경우(끝난 작업결과들을 리스트로 만든다)
        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        CompletableFuture[] futureArray = futures.toArray(new CompletableFuture[futures.size()]);
        CompletableFuture<List<String>> results = CompletableFuture.allOf(futureArray)
                .thenApply(v -> {
                    return futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList());
                });
        results.get().forEach(System.out::println);

        // 여러 Task가 실행되고 아무거나 한개 빨리 끝났을때 처리되는 경우
        CompletableFuture<Void> future2 = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        future2.get();

        // 에러 처리(exceptionally)
        boolean throwError = true;
        CompletableFuture<String> hello2 = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });
        System.out.println(hello2.get());

        // 에러 처리(handle)
        CompletableFuture<String> hello3 = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "ERROR!";
            }
            return result;
        });
        System.out.println(hello3.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
