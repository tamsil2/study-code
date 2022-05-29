package me.tamsil.chapter01.item07.executor;

import java.util.concurrent.*;

public class ExecutorExample {
    public static void main(String[] args) throws Exception {
        int numberOfCpu = Runtime.getRuntime().availableProcessors();

        // Fixed Thread Pool
        ExecutorService service = Executors.newFixedThreadPool(numberOfCpu);

        // Cached Thread Pool
//        ExecutorService service = Executors.newCachedThreadPool();

        // Single Thread Pool
//        ExecutorService service = Executors.newSingleThreadExecutor();

        // Scheduled Thread Pool
//        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

//        for (int i = 0; i < 100; i++) {
//            // 1. 일반적인 쓰레드를 생성해서 Start 하는 경우
//            Thread thread = new Thread(new Task());
//            thread.start();
//
//            // 2. ExecutorService를 통해서 실행하는 경우(submit)
//            service.submit(new Task());
//        }

        // 3. Callable를 통해서 호출하는 경우
        Future<String> submit = service.submit(new Task2());

        System.out.println(Thread.currentThread() + " hello");

        submit.get();
        service.shutdown();
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread() + " world");
        }
    }

    static class Task2 implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(2000L);
            return Thread.currentThread() + " world";
        }
    }

}
