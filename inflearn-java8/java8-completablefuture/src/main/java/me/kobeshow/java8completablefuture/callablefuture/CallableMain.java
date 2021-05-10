package me.kobeshow.java8completablefuture.callablefuture;

import java.util.concurrent.*;

public class CallableMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone());
        System.out.println("Started!!!");

        helloFuture.cancel(true);

        System.out.println(helloFuture.isDone());

        helloFuture.get(); // Blocking Call

        System.out.println(helloFuture.isDone());
        System.out.println("End!!!");
        executorService.shutdown();
    }
}
