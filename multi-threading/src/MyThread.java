public class MyThread extends Thread
{
//    New: A thread is in this state when it is created but not
//    yet started.

//    Runnable: After the start method is called, the thread
//    becomes runnable. It's ready to run and is waiting for
//    CPU time.

//    Running: The thread is in this state when it is executing.

//    Blocked/Waiting: A thread is in this state when it is
//    waiting for a resource or for another thread to perform
//    an action.

//    Terminated: A thread is in this state when it has
//    finished executing.

    @Override
    public void run()
    {
        System.out.println("RUNNING");
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        MyThread t1 = new MyThread();
        System.out.println(t1.getState()); // -> NEW
        t1.start();
        System.out.println(t1.getState()); // -> RUNNABLE
        Thread.sleep(100);
        System.out.println(t1.getState()); // -> TIME_WAITING
        t1.join();
        System.out.println(t1.getState()); // -> TERMINATED
    }
}
