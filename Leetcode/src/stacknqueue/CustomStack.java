package stacknqueue;

//1381. Design a Stack With Increment Operation
public class CustomStack
{
    private int maxSize;
    private int count;
    private int arr[];
    public CustomStack(int maxSize)
    {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        count = 0;
    }

    public void push(int x)
    {
        if(count < maxSize)
            arr[count++] = x;
    }

    public int pop()
    {
        if(count == 0)
            return -1;
        else
            count--;
        return arr[count];
    }

    public void increment(int k, int val)
    {
        for (int i = 0; i < count && i < k; i++)
            arr[i] += val;
    }
}
