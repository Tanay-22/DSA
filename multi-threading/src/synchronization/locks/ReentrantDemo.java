package synchronization.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantDemo
{
    private final Lock lock = new ReentrantLock();

    public void outerMethod()
    {
        lock.lock();
        // lock.lockInterruptibly()
        // Acquires the lock unless the current thread is interrupted.
        // Acquires the lock if it is available and returns immediately.
        try
        {
            System.out.println("Outer Method");
            innerMethod();
        }
        finally
        {
            lock.unlock();
        }
    }
    // outerMethod() and innerMethod() demonstrate deadlock scenario
    // but the Reentrant implementation prevents the deadlock situation

    public void innerMethod()
    {
        lock.lock();
        try
        {
            System.out.println("Inner Method");
        }
        finally
        {
            lock.unlock();
        }
    }

    public static void main(String[] args)
    {
        ReentrantDemo demo = new ReentrantDemo();
        demo.outerMethod();
    }
}
