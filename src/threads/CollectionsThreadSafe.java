package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CollectionsThreadSafe {

    // private static List<String> lista = new CopyOnWriteArrayList<>();
    // private static Map<Integer, String> mapa = new ConcurrentHashMap<>();
    private static BlockingQueue<String> fila = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        // Coleções que são Thread-safe
        MeuRunnable runnable = new MeuRunnable();
        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(500);
        // System.out.println(lista);
        // System.out.println(mapa);
        System.out.println(fila);
    }

    public static class MeuRunnable implements Runnable {
        @Override
        public void run() {
            // lista.add("Coleções que são Thread-safe!");
            // mapa.put(new Random().nextInt(), "Test Thread-safe!");
            fila.add("Test Thread-safe!");
            String name = Thread.currentThread().getName();
            System.out.println(name + " insert!");
        }
    }
}
