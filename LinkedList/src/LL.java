public class LL
{
    private class Node
    {
        private int value;
        private Node next;

        public Node(int value)
        {
            this.value = value;
        }

        public Node(int value, Node next)
        {
            this.value = value;
            this.next = next;
        }
    }

    private Node head, tail;
    private int size;

    public LL()
    {
        this.size = 0;
    }

    public void insertAtFirst(int value)
    {
        Node node = new Node(value);
        node.next = head;
        head = node;

        if(tail == null)
            tail = head;

        size++;
    }

    public void insertAtLast(int value)
    {
        Node node = new Node(value);

        if(tail == null)
        {
            insertAtFirst(value);
            return;
        }
        tail.next = node;
        tail = node;

        size++;
    }

    public void insertAtIndex(int value, int index)
    {
        if(index == 0)
        {
            insertAtFirst(value);
            return;
        }
        if(index == size)
        {
            insertAtLast(value);
            return;
        }

        Node temp = head;
        for (int i = 1; i < index; i++)
            temp = temp.next;

        Node node = new Node(value, temp.next);
        temp.next = node;

        size++;
    }

    public Node get(int index)
    {
        Node node = head;
        for (int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    public int deleteFirst()
    {
        int value = head.value;
        head = head.next;
        if(head == null)
            tail = null;
        size--;
        return value;
    }

    public int deleteLast()
    {
        if(size <= 1)
            return deleteFirst();

        Node secondLast = get(size - 2);
        int val = tail.value;

        tail = secondLast;
        tail.next = null;

        return val;
    }

    public int delete(int index)
    {
        if (index == 0)
            return deleteFirst();
        if (index == size - 1)
            return deleteLast();

        Node prev = get(index - 1);
        int value = prev.next.value;
        prev.next = prev.next.next;

        return value;
    }

    public void display()
    {
        Node temp = head;
        while(temp != null)
        {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }
        System.out.print("END");
    }
}