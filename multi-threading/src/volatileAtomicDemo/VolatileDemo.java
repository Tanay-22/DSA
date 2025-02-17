package volatileAtomicDemo;

public class VolatileDemo
{
    public static void main(String[] args)
    {
        SharedObject object = new SharedObject();
        Thread writerThread = new Thread(() ->
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
            object.setFlagTrue();
        });
        Thread readerThread = new Thread(() -> object.printIfFlagTrue());

        writerThread.start();
        readerThread.start();
    }
}


class SharedObject
{
    private volatile boolean flag = false;

    public void setFlagTrue()
    {
        System.out.println("Writer thread made the flag true");
        flag = true;
    }

    public void printIfFlagTrue()
    {
        while (!flag)
        {

        }
        System.out.println("Flag is true !");
    }
}