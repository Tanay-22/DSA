public class World extends Thread
{
    @Override
    public void run()
    {
        for (; ;)
        {
            System.out.println("Hemlo from Thread - " + Thread.currentThread().getName());
        }
    }
}
