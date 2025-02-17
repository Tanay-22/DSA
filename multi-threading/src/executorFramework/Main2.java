package executorFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main2
{
    public static void main(String[] args)
    {
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(() -> System.out.println("Hello"));
        future.get();
        executorService.shutdown();
        Future<String> submit = executorService.submit(() -> System.out.println("Hemlo"), "success");
        System.out.println(submit);*/

//        ExecutorService executorService = Executors.newFixedThreadPool(2);

        /*Future<Integer> submit = executorService.submit(() -> 1 + 2);
        Integer i = submit.get();
        System.out.println("Sum is: " + i);
        executorService.shutdown();
        Thread.sleep(1);
        System.out.println(executorService.isTerminated());*/

        /*Callable<Integer> callable1 = () ->
        {
            Thread.sleep(1000);
            System.out.println("Task 1");
            return 1;
        };
        Callable<Integer> callable2 = () ->
        {
            Thread.sleep(1000);
            System.out.println("Task 2");
            return 2;
        };
        Callable<Integer> callable3 = () ->
        {
            Thread.sleep(1000);
            System.out.println("Task 3");
            return 3;
        };
        List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3);
        // multiple executes == invokeAll
        List<Future<Integer>> futures = null;
        try
        {
            futures = executorService.invokeAll(list, 1, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        for(Future<Integer> future: futures)
        {
            try
            {
                System.out.println(future.get());
            }
            catch (CancellationException e)
            {

            }
            catch (InterruptedException | ExecutionException e)
            {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
        System.out.println("Shutdown Complete");*/

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() ->
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            System.out.println("Hello");
            return 10;
        });
        /*Integer i = null;
        try
        {
            i = future.get(1, TimeUnit.SECONDS);
            System.out.println(future.isDone());
            System.out.println(i);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e)
        {
            System.out.println("Exception occurred: " + e);
        }*/
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }
        future.cancel(false); // true --> cancel; false --> cancel if not running
        System.out.println(future.isCancelled());
        System.out.println(future.isDone());
        executorService.shutdown();
    }
}
