package synchronization;

public class Counter
{
    private int count = 0;

    // 2 ways to synchronize
    // 1. make the entire method synchronized

    /*public synchronized void increment()
    {
        count++;
    }*/

    // 2. make a block synchronized

    public synchronized void increment()
    {
        synchronized (this) // referring to the particular instance
        {
            count++;
        }
    }

    public int getCount()
    {
        return count;
    }
}
