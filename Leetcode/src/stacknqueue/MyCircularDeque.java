package stacknqueue;

class MyCircularDeque
{
    private class Node
    {
        int val;
        Node next;

        public Node(int val)
        {
            this.val = val;
        }
    }
    private Node tail;
    private int size;
    private int sizeLimit;

    public MyCircularDeque(int k)
    {
        this.tail = null;
        this.sizeLimit = k;
        this.size = 0;
    }

    public boolean insertFront(int value)
    {
        if(size < sizeLimit)
        {
            if(tail == null)
            {
                tail = new Node(value);
                tail.next = tail;
            }
            else
            {
                Node temp = new Node(value);
                temp.next = tail.next;
                tail.next = temp;
            }
            size++;
            return true;
        }
        return false;
    }

    public boolean insertLast(int value)
    {
        if(size < sizeLimit)
        {
            if(tail == null)
            {
                tail = new Node(value);
                tail.next = tail;
            }
            else
            {
                Node temp = new Node(value);
                temp.next = tail.next;
                tail.next= temp;
                tail = temp;
            }
            size++;
            return true;
        }
        return false;
    }

    public boolean deleteFront()
    {
        if(size > 0)
        {
            if(size == 1)
                tail = null;
            else
                tail.next = tail.next.next;
            size--;
            return true;
        }
        return false;
    }

    public boolean deleteLast()
    {
        if(size > 0)
        {
            if(size == 1)
                tail = null;
            else
            {
                Node temp = tail;
                while (temp.next != tail)
                    temp = temp.next;
                temp.next = tail.next;
                tail = temp;
            }
            size--;
            return true;
        }
        return false;
    }

    public int getFront()
    {
        if(size == 0)
            return -1;

        return tail.next.val;
    }

    public int getRear()
    {
        if(size == 0)
            return -1;

        return tail.val;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean isFull()
    {
        return size == sizeLimit;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */