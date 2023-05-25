package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {

    private static final SynchronousQueue<String> QUEUE = new SynchronousQueue<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            put();
            System.out.println("Write queue!");
        };
        Runnable r2 = () -> {
            String msg = take();
            System.out.println("Read queue! " + msg);
        };

        executor.execute(r1);
        executor.execute(r2);

        executor.shutdown();
    }

    private static String take() {
        try {
            return QUEUE.take();
            // return QUEUE.poll(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return "Exception";
        }
    }

    private static void put() {
        try {
            QUEUE.put("Write");
            // QUEUE.offer(e, timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}