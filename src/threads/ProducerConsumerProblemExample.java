package threads;

import java.util.Random;
import java.util.concurrent.*;

public class ProducerConsumerProblemExample {

    private static final BlockingQueue<Integer> QUEUE = new LinkedBlockingDeque<>(5);

    public static void main(String[] args) {

        Runnable producer = () -> {
            // simulaProcessamento();
            simulaProcessamentoLento();
            System.out.println("Produzindo");
            int numero = new Random().nextInt(10000);
            try {
                QUEUE.put(numero);
                System.out.println(numero);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            simulaProcessamento();
            // simulaProcessamentoLento();
            System.out.println("Consumindo");
            try {
                Integer take = QUEUE.take();
                System.out.println(take);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleWithFixedDelay(producer, 0, 10, TimeUnit.MILLISECONDS);
        executor.scheduleWithFixedDelay(consumer, 0, 10, TimeUnit.MILLISECONDS);
    }

    private static void simulaProcessamento() {
        int tempo = new Random().nextInt(40);
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static void simulaProcessamentoLento() {
        int tempo = new Random().nextInt(400);
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}