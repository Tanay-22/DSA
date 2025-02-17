package executorFramework;

import java.util.concurrent.*;

public class CyclicBarrierDemo
{
    // CountDown latch is not reusable
    // once it reached 0, it cannot be reset

    // CyclicBarrier is used in scenarios where we want all the threads to reach a certain checkpoint before any of the
    // thread proceed further
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        /*int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CyclicBarrier barrier = new CyclicBarrier(numberOfServices);
        executorService.submit(new DependentServiceC(barrier));
        executorService.submit(new DependentServiceC(barrier));
        executorService.submit(new DependentServiceC(barrier));

        System.out.println("Main");
        executorService.shutdown();*/

        int numberOfSubsystems = 4;
        CyclicBarrier barrier = new CyclicBarrier(numberOfSubsystems, new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("All Subsystems are up and running. System startup complete.");
            }
        });

        Thread webServerThread = new Thread(new Subsystem("Web Server", 2000, barrier));
        Thread databasseThread = new Thread(new Subsystem("Database", 4000, barrier));
        Thread cacheThread = new Thread(new Subsystem("Cache", 3000, barrier));
        Thread messagingServiceThread = new Thread(new Subsystem("Messaging Service", 3500, barrier));

        webServerThread.start();
        databasseThread.start();
        cacheThread.start();
        messagingServiceThread.start();
    }
}

/*class DependentServiceC implements Callable<String>
{
    private final CyclicBarrier barrier;

    public DependentServiceC(CyclicBarrier barrier)
    {
        this.barrier = barrier;
    }

    @Override
    public String call() throws Exception
    {
        System.out.println(Thread.currentThread().getName() + " service started.");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " is waiting at the barrier.");
        barrier.await();

        return "OK";
    }
}*/

class Subsystem implements Runnable
{
    private final String name;
    private final int initializationTime;
    private final CyclicBarrier barrier;

    public Subsystem(String name, int initializationTime, CyclicBarrier barrier)
    {
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println(name + " initialization started.");
            Thread.sleep(initializationTime); // to simulate time taken to initialise
            System.out.println(name + " initialization completed.");
            barrier.await();
        }
        catch (InterruptedException | BrokenBarrierException e)
        {
            e.printStackTrace();
        }
    }
}
