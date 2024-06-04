package LL;
//707. Design Linked List
public class MyLinkedList
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

    public MyLinkedList()
    {
        this.size = 0;
    }

    public Node getNode(int index)
    {
        Node node = head;

        if(index < 0)
            return null;

        for (int i = 0; i < index; i++)
        {
            if(node == null)
                return null;
            node = node.next;
        }
        return node;
    }

    public int get(int index)
    {
        Node node = getNode(index);
        if(node == null)
            return -1;

        return node.value;
    }
    public void addAtHead(int value)
    {
        Node node = new Node(value);
        node.next = head;
        head = node;

        if(tail == null)
            tail = head;

        size++;
    }

    public void addAtTail(int value)
    {
        Node node = new Node(value);

        if(tail == null)
        {
            addAtHead(value);
            return;
        }
        tail.next = node;
        tail = node;

        size++;
    }

    public void addAtIndex(int index, int value)
    {
        if(index == 0)
        {
            addAtHead(value);
            return;
        }
        if(index == size)
        {
            addAtTail(value);
            return;
        }
        if(index > size)
            return;

        Node temp = head;
        for (int i = 1; i < index; i++)
            temp = temp.next;

        Node node = new Node(value, temp.next);
        temp.next = node;

        size++;
    }

    public void deleteFirst()
    {
        if(head == null)
            return;
        head = head.next;
        if(head == null)
            tail = null;
        size--;
    }

    public void deleteLast()
    {
        if(size <= 1)
        {
            deleteFirst();
            return;
        }

        Node secondLast = getNode(size - 2);

        tail = secondLast;
        tail.next = null;
        size--;
    }

    public void deleteAtIndex(int index)
    {
        if (index < 0 || index >= size || size == 0)
            return;

        if (index == 0)
        {
            deleteFirst();
            return;
        }

        if (index == size - 1)
        {
            deleteLast();
            return;
        }

        Node prev = getNode(index - 1);
        prev.next = prev.next.next;

        size--;
    }
}
