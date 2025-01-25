package synchronization.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount
{
    private int balance = 100;

    /*public synchronized void withdraw(int amount)
    {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        if(balance >= amount)
        {
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
            try
            {
                Thread.sleep(10000); // to simulate transactions
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. " +
                    "Remaining balance - " + balance);
        }
        else
        {
            System.out.println(Thread.currentThread().getName() + " insufficient balance");
        }
    }*/
    // this will make the other threads to wait for the active one to complete its task and the others will wait for 10s
    // to overcome this problem we use "Lock" interface

    private final Lock lock = new ReentrantLock();

    public  void withdraw(int amount)
    {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try
        {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS))
            {
                if(balance >= amount)
                {
                    try
                    {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                        Thread.sleep(5000); // to simulate transactions
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed withdrawal. " +
                                "Remaining balance - " + balance);
                    }
                    catch (Exception e)
                    {
                        Thread.currentThread().interrupt();
                    }
                    finally
                    {
                        lock.unlock();
                    }
                }
                else
                {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance");
                }
            }
            else
            {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock, will try again " +
                        "later");
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
