## Item7. 다 쓴 객체 참조를 해제하라
### 21. Executor
### Callable
- Runnable과 같이 다른 쓰레드로 작업을 하지만 쓰레드 작업의 결과물을 받아서 처리하고 싶을때 사용
  - Future<String> submit을 리턴타입으로 받아서 이를 통해서 처리할 수 있다
```java
public class ExecutorExample {
    public static void main(String[] args) throws Exception {
        int numberOfCpu = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(numberOfCpu);
        Future<String> submit = service.submit(new Task());
        System.out.println(Thread.currentThread() + " hello");

        System.out.println(submit.get());
        service.shutdown();
    }

    static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(2000L);
            return Thread.currentThread() + " world";
        }
    }

}
```
- Callable로 실행하게 되면 2초의 Sleep 없이 거의 바로 출력이 된다. 즉 submit을 호출했을 경우는 Blocking Call이다
- 하지만 submit.get을 호출하는 순간 NonBlocking 콜이된다.

## Item8. finalizer와 cleaner 사용을 피하라
### AUtoClosable

## Item9. try-finally 보다 try-with-resources를 사용하라
