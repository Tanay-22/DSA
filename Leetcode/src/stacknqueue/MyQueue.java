package stacknqueue;

import java.util.Stack;
// implement Queue using Stack
public class MyQueue
{
    Stack<Integer> stack1, stack2;

    public MyQueue()
    {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x)
    {
        stack1.push(x);
    }


    public int pop()
    {
        while (!stack1.empty())
            stack2.push(stack1.pop());
        int res = stack2.pop();
        while (!stack2.isEmpty())
            stack1.push(stack2.pop());
        return res;
    }

    public int peek()
    {
        while (stack1.size() > 1)
            stack2.push(stack1.pop());
        int res = stack1.peek();
        while (!stack2.empty())
            stack1.push(stack2.pop());
        return res;
    }

    public boolean empty()
    {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
