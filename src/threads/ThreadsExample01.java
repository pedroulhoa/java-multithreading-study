package threads;

public class ThreadsExample01 {

    public static void main(String[] args) {
        // Thread atual
        Thread t = Thread.currentThread();
        System.out.println(t.getName());

        // Runnable personalizado
        MyRunnable runnable = new MyRunnable();

        // Criando nova Thread
        Thread t1 = new Thread(runnable);
        //t2.run(); // Com a função run(), a implementação é executada na mesma thread;

        // Criando nova Thread com função lambda
        Thread t2 = new Thread(() ->
                System.out.println(Thread.currentThread().getName()));

        Thread t3 = new Thread(runnable);

        t1.start(); // Com a função start(), nós executados a implementação em uma nova Thread;
        t2.start();
        t3.start();
    }
}