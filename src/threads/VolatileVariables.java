package threads;

import java.lang.Thread.State;

public class VolatileVariables {

    private static volatile int number = 0;
    private static volatile boolean prepare = false;

    private static class MeuRunnable implements Runnable {

        @Override
        public void run() {
            while (!prepare) {
                Thread.yield();
            }

            if (number != 42) {
                throw new IllegalStateException("Exception!");
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            Thread t0 = new Thread(new MeuRunnable());
            t0.start();
            Thread t1 = new Thread(new MeuRunnable());
            t1.start();
            Thread t2 = new Thread(new MeuRunnable());
            t2.start();

            number = 42;
            prepare = true;

            while (
                    t0.getState() != State.TERMINATED
                            || t1.getState() != State.TERMINATED
                            || t2.getState() != State.TERMINATED
            ) {
                // wait
            }

            number = 0;
            prepare = false;
        }
    }
}
