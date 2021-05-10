package me.kobeshow.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

// 작업이 모두 끝난 다음에 처리하는 경우
public class AppCompletableFuture4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> result = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
        result.get().forEach(System.out::println);

        // 둘 중에 먼저 도착하는 걸로 처리할 경우
        CompletableFuture<Void> future2 = CompletableFuture.anyOf(hello, world).thenAccept((s) -> System.out.println(s));
        future2.get();
    }
}
