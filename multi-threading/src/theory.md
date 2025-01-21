# Thread States in Java

In Java, threads go through various states during their lifecycle. These states are as follows:

## 1. **New**
- A thread is in this state when it is created but not yet started.

## 2. **Runnable**
- After the `start` method is called, the thread enters the runnable state.
- It is ready to run and is waiting for CPU time.

## 3. **Running**
- The thread is in this state when it is actively executing.

## 4. **Blocked/Waiting**
- A thread enters this state when:
    - It is waiting for a resource.
    - It is waiting for another thread to perform an action.

## 5. **Terminated**
- A thread reaches this state when it has finished executing.

Each of these states represents a specific point in the lifecycle of a thread and helps in understanding its behavior during execution.

# Java Thread vs Runnable
- Runnable interface is used in case a class is already inheriting another class.
(Since java doesn't support direct multiple inheritance)

```java
class A extends B implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("RUNNING");
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
```