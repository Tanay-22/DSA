package synchronization.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockDemo
{
//    private final Lock lock = new ReentrantLock(); // unfair - the order of thread priority is arbitrary
    private final Lock lock = new ReentrantLock(true); // fair - the order of thread priority is order of request

    public void accessResources()
    {
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        finally
        {
            System.out.println(Thread.currentThread().getName() + " release the lock");
            lock.unlock();
        }
    }

    public static void main(String[] args)
    {
        FairnessLockDemo demo = new FairnessLockDemo();
        Runnable task = new Runnable()
        {
            @Override
            public void run()
            {
                demo.accessResources();
            }
        };
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
