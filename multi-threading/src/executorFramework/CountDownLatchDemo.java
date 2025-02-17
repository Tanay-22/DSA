package executorFramework;

import java.util.concurrent.*;

public class CountDownLatchDemo
{
    // CountDown latch is not reusable
    // once it reached 0, it cannot be reset
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        /*ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future1 = executorService.submit(new DependentService());
        Future<String> future2 = executorService.submit(new DependentService());
        Future<String> future3 = executorService.submit(new DependentService());

        future1.get();
        future2.get();
        future3.get();*/
        // not ideal for variable number of tasks

        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        latch.await(5, TimeUnit.SECONDS);

        System.out.println("Main");
        executorService.shutdown();
    }
}

class DependentService implements Callable<String>
{
    private final CountDownLatch latch;

    public DependentService(CountDownLatch latch)
    {
        this.latch = latch;
    }

    @Override
    public String call() throws Exception
    {
        try
        {
            Thread.sleep(6000);
            System.out.println(Thread.currentThread().getName() + " service started");
        }
        finally
        {
            latch.countDown();
        }
        return "OK";
    }
}
