public class World2 implements Runnable
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
