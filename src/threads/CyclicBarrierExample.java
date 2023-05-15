package threads;

import java.util.concurrent.*;

public class CyclicBarrierExample {

    private static final BlockingQueue<Double> resultados = new LinkedBlockingQueue<>();

    private static ExecutorService executor;
    private static Runnable r1;
    private static Runnable r2;
    private static Runnable r3;
    private static double resultadoFinal = 0;

    public static void main(String[] args) {
        Runnable sumarizacao = () -> {
            System.out.println("Somando tudo.");
            resultadoFinal += resultados.poll();
            resultadoFinal += resultados.poll();
            resultadoFinal += resultados.poll();
            System.out.println("Processamento finalizado. "
                    + "Resultado final: " + resultadoFinal
                    + ". Final exemplo!");
            System.out.println("------------");
        // restart();
        };
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, sumarizacao);

        executor = Executors.newFixedThreadPool(3);
        r1 = () -> {
            while (true) {
                resultados.add(432d*3d);
                await(cyclicBarrier);
                sleep();
            }
        };
        r2 = () -> {
            while (true) {
                resultados.add(Math.pow(3d, 14d));
                await(cyclicBarrier);
                sleep();
            }
        };
        r3 = () -> {
            while (true) {
                resultados.add(45d*127d/12d);
                await(cyclicBarrier);
                sleep();
            }
        };

        restart();
        // executor.shutdown();
    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static void restart() {
        sleep();
        executor.submit(r1);
        executor.submit(r2);
        executor.submit(r3);
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
