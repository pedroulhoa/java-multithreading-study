package threads;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        // executor.schedule(new TaskExample(), 2, TimeUnit.SECONDS);
        // executor.scheduleAtFixedRate(new TaskExample(), 0, 1, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(new TaskExample(), 0, 1, TimeUnit.SECONDS);

        // executor.shutdown();
    }

    public static class TaskExample implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LocalTime.now());
            String name = Thread.currentThread().getName();
            int nextInt = new Random().nextInt(1000);
            System.out.println(name + ": Task Example! " + nextInt);
        }
    }

}
