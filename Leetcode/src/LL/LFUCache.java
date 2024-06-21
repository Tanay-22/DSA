package LL;

class LFUCache
{
    private class Node
    {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int capacity;
    private int count;

    public LFUCache(int capacity)
    {
        this.capacity = capacity;
        this.head = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.tail = head;
        this.count = 0;
    }

    public int get(int key)
    {
        Node temp = head;
        while (temp != null)
        {
            if(temp.key == key)
                break;
            else
                temp = temp.next;
        }

        if(temp == null)
            return -1;

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;

        temp.next = head.next;
        head.next.prev = temp;
        temp.prev = head;

        return temp.key;
    }

    public void put(int key, int value)
    {
        
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */