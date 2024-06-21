package stacknqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinStack
{
    private List<Integer> list;
    private int count;
    private int min;
    public MinStack()
    {
        list = new ArrayList<>();
        count = 0;
        min = Integer.MAX_VALUE;
    }

    public void push(int val)
    {
        list.add(val);
        count++;

        if(min > val)
            min = val;
    }

    public void pop()
    {
        if(count > 0)
        {
            int del = list.remove(count-- - 1);
            if(del == min)
            {
                min = Integer.MAX_VALUE;
                for (int e: list)
                {
                    if(min > e)
                        min = e;
                }
            }
        }
    }

    public int top()
    {
        if(count > 0)
            return list.get(count-1);
        return 0;
    }

    public int getMin()
    {
        return min;
    }
}
