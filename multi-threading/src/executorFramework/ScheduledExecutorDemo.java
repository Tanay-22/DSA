package executorFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo
{
    public static void main(String[] args)
    {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
//        scheduler.schedule(() -> System.out.println("Task executed after 5 sec delay!"), 5, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(() -> System.out.println("Task executed after every 5 sec delay!"),
                5, 5, TimeUnit.SECONDS);

        ScheduledFuture<?> scheduledFuture = scheduler.scheduleWithFixedDelay(() -> System.out.println("Task executed after every 5 sec delay!"),
                5, 5, TimeUnit.SECONDS);

        scheduler.schedule(() ->
        {
            System.out.println("Initiating shutdown...");
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);
    }
}
