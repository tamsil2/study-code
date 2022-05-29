package me.tamsil.chapter01.item08.finalizer;

import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        int i = 0;
        while (true) {
            i++;
            new FinalizerIsBad();

            if ((i % 1_000_000) == 0) {
                Class<?> finalizerClass = Class.forName("java.lang.ref.Finalizer");
                Field queueStaticField = finalizerClass.getDeclaredField("queue");
                queueStaticField.setAccessible(true);
                ReferenceQueue<Object> referenceQueue = (ReferenceQueue<Object>) queueStaticField.get(null);

                Field queueLengthField = ReferenceQueue.class.getDeclaredField("queueLength");
                queueLengthField.setAccessible(true);
                long queueLength = (long) queueLengthField.get(referenceQueue);
                System.out.format("There are %d references in the queue", queueLength);
            }
        }
    }
}
