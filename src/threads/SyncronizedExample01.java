package threads;

public class SyncronizedExample01 {

    private static int i = 0;

    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            int j;
            synchronized (this) { // Utilizando synchronized apenas no trecho que utiliza recursos que tem concorrência entre threads;
                i++;
                j = i * 2;
            }

            double jElevadoA10 = Math.pow(j, 10);
            double sqrt = Math.sqrt(jElevadoA10);
            System.out.println(sqrt);
        }
    }

}
