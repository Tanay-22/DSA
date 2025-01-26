package executorFramework;

import java.util.concurrent.*;

public class Main
{
    private static long factorial(int n)
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        long res = 1;
        for (int i = 1; i <= n; i++)
        {
            res *= i;
        }
        return res;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        long startTime = System.currentTimeMillis();
        /*for (int i = 1; i < 10; i++)
        {
            int finalI = i;
            Thread thread = new Thread(() -> System.out.println(factorial(finalI)));
            thread.start();
        }*/
//        here the main thread will get executed first and the total time will be incorrect

        /*Thread[] threads = new Thread[9];
        for (int i = 1; i < 10; i++)
        {
            int finalI = i;
            threads[i-1] = new Thread(() -> System.out.println(factorial(finalI)));
            threads[i-1].start();
        }
        for(Thread thread : threads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }*/ // same could be achieved using executors framework

        ExecutorService executor = Executors.newFixedThreadPool(9);
        /*for (int i = 1; i < 10; i++)
        {
            int finalI = i;
            executor.submit(() -> System.out.println(factorial(finalI)));
        }
        executor.shutdown();
        try
        {
            executor.awaitTermination(100, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + "ms");*/

//        Runnable runnable = () -> "Hemlo"; // we can't return anything from runnable

        // so we use callable interface
        Callable<String> callable = () -> "Hemlo";


        Future<?> future = executor.submit(callable);
        // stores the result of asynchronous computation. checks if the computation is complete, wait for its completion
        // and retrieves the result of the computation
        if(future.isDone())
            System.out.println("Task is dome !");
        System.out.println(future.get());
        executor.shutdown();
    }
}
