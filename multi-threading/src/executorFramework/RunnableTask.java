package executorFramework;

public class RunnableTask implements Runnable
{
    @Override
    public void run()
    {
        try
        {
            Thread.sleep(1000);
            // need try/catch block for exception handling because parent interface doesn't throw Exception
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
