package threads;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerExample {

    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " take");
            String msg = "take!";
            String response = exchange(msg);
            System.out.println(name + " - " + response);
        };
        Runnable r2 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " thank you");
            String msg = "thank you!";
            String response = exchange(msg);
            System.out.println(name + " - " + response);
        };

        executor.execute(r1);
        executor.execute(r2);

        executor.shutdown();
    }

    private static String exchange(String msg) {
        try {
            return EXCHANGER.exchange(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return "Exception";
        }
    }

}

