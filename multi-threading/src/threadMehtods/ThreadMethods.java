package threadMehtods;

public class ThreadMethods extends Thread
{
    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep(1000);
                System.out.println(i);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        ThreadMethods threadMethods = new ThreadMethods();
        threadMethods.start();
        threadMethods.join();
        System.out.println("Hemlo");
    }
}
