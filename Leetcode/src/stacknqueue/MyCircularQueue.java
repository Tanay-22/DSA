package stacknqueue;

import java.util.ArrayList;
import java.util.List;

public class MyCircularQueue
{
    private List<Integer> list;
    private int front;
    private int rear;
    private int size;
    private int count;
    public MyCircularQueue(int k)
    {
        list = new ArrayList<>();
        for (int i = 0; i < k; i++)
            list.add(0);
        size = k;
        front = rear = -1;
        count = 0;
    }

    public boolean enQueue(int value)
    {
        if(isFull())
            return false;
        if(isEmpty())
        {
            front = 0;
            rear = 0;
        }
        else
            rear = (rear + 1) % size;
        list.set(rear, value);
        count++;
        return true;
    }

    public boolean deQueue()
    {
        if(isEmpty())
            return false;

        if(front == rear)
        {
            front = -1;
            rear = -1;
        }
        else
            front = (front + 1) % size;
        count--;
        return true;
    }

    public int Front()
    {
        if(isEmpty())
            return -1;
        return list.get(front);
    }

    public int Rear()
    {
        if(isEmpty())
            return -1;
        return list.get(rear);
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public boolean isFull()
    {
        return count == size;

    }
}
