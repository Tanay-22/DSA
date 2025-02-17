package executorFramework;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        // daemon thread
        // to handles asynchronous programming
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() ->
        {
            try
            {
                Thread.sleep(5000);
                System.out.println("Worker 1");
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            return "OK";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() ->
        {
            try
            {
                Thread.sleep(5000);
                System.out.println("Worker 2");
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            return "OK";
        });
//        System.out.println(completableFuture.get());
//        System.out.println(completableFuture.getNow("NO0"));

        CompletableFuture<Void> f = CompletableFuture.allOf(f1, f2);
        /*
            returns a new CompletableFuture that is completed when all the given
            CompletableFutures complete
        */
        f.join(); // --> same work as f.get() with Exception handling already done
        System.out.println("Main");
    }
}
