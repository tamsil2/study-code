package chapter02.item8;

import java.lang.ref.Cleaner;

// 코드 8-1 cleaner를 안전망으로 활용하는 AutoCloseable 클래스
public class Room implements AutoCloseable{
    private static final Cleaner cleaner = Cleaner.create();

    private static class State implements Runnable {
        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("방 청소");
            numJunkPiles = 0;
        }
    }

    // 방의 상태, cleanable과 공유한다.
    private final State state;

    // cleanable 객체, 수거 대상이 되면 방을 청소한다.
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}
