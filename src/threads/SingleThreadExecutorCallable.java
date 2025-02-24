package threads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutorCallable {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();

            Future<String> future = executor.submit(new taskExample());

            System.out.println(future.isDone());
            System.out.println(future.get(1, TimeUnit.SECONDS));
            System.out.println(future.isDone());
        } catch (Exception e) {
            throw e;
        } finally {
            if (executor != null) {
                executor.shutdownNow();
            }
        }
    }

    public static class taskExample implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(500);
            String name = Thread.currentThread().getName();
            int nextInt = new Random().nextInt(1000);
            return name + ": Task example! " + nextInt;
        }
    }

}
