package threadMehtods;

public class ThreadMethods extends Thread
{
    public ThreadMethods(String name)
    {
        super(name);
    }

    @Override
    public void run()
    {
        /*for (int i = 0; i < 10; i++)
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
        }*/

        /*// for setPriority
        for (int i = 0; i < 5; i++)
        {
            System.out.println(Thread.currentThread().getName() + " Priority: " +
                    Thread.currentThread().getPriority() + " - count: " + i);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }*/

        /*try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }*/

        /*for (int i = 0; i < 5; i++)
        {
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.yield();
        }*/

        while (true)
            System.out.println("Hemlo Daemon");

    }

    public static void main(String[] args) throws InterruptedException
    {
        /*ThreadMethods t1 = new ThreadMethods("Low Priority");
        ThreadMethods t2 = new ThreadMethods("Medium Priority");
        ThreadMethods t3 = new ThreadMethods("High Priority");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
//        t1.join();
        System.out.println("Hemlo");*/

        ThreadMethods myThread = new ThreadMethods("Daemon Thread");
        myThread.setDaemon(true);
        myThread.start();
        System.out.println("Main done");
    }
}
