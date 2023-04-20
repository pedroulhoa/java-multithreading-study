package threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollections {
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        list = Collections.synchronizedList(list);
        // Utilize a versão do synchronizedXXX de acordo com seu tipo de coleção.
        // list = Collections.synchronizedCollection(list);
        // list = Collections.synchronizedMap(list);
        // list = Collections.synchronizedSet(list);

        MeuRunnable runnable = new MeuRunnable();
        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(500);
        System.out.println(list);
    }

    public static class MeuRunnable implements Runnable {
        @Override
        public void run() {
            list.add("Test sincronized collections!");
            String name = Thread.currentThread().getName();
            System.out.println(name + " insert successfully!");
        }
    }
}
