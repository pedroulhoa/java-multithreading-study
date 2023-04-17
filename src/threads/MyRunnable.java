package threads;

public class MyRunnable implements Runnable
{
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
    }
}
