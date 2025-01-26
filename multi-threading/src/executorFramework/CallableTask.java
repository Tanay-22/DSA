package executorFramework;

import java.util.concurrent.Callable;

public class CallableTask implements Callable
{

    @Override
    public Object call() throws Exception
    {
        Thread.sleep(1000); // parent interface already handles the Exception
        return null;
    }
}
