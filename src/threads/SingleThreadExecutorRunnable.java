package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutorRunnable {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();

            executor.execute(new TaskExample());
            executor.execute(new TaskExample());
            executor.execute(new TaskExample());
            Future<?> future = executor.submit(new TaskExample());

            System.out.println(future.isDone());
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(future.isDone());
        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null) {
                executor.shutdownNow();
            }
        }
    }

    public static class TaskExample implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": Task example!");
        }
    }

}
