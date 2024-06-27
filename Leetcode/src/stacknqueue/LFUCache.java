package stacknqueue;

import java.util.HashMap;

class LRUCache
{
    private class Node
    {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Node> hashMap;
    private int capacity;

    private Node head;

    private Node tail;

    public LRUCache(int capacity)
    {
        this.hashMap = new HashMap<>();
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
    }

    public int get(int key)
    {
        Node node = this.getNode(key);

        return node == null ? -1 : node.value;
    }

    private Node getNode(int key)
    {
        Node res = this.hashMap.get(key);
        if (res != null)
        {
            if (res == head)
                return res;

            if (res == tail)
            {
                tail = res.prev;
                tail.next = null;

                res.next = head;
                res.prev = null;
                head.prev = res;
                head = res;

                return res;
            }
            res.prev.next = res.next;
            res.next.prev = res.prev;

            res.next = head;
            res.prev = null;
            head.prev = res;
            head = res;

            return res;
        }
        return null;
    }

    public void put(int key, int value)
    {
        Node alreadyExist = this.getNode(key);

        if (alreadyExist != null)
        {
            alreadyExist.value = value;
            return;
        }
        Node node = new Node(key, value);
        if (hashMap.size() >= capacity)
        {
            hashMap.remove(tail.key);
            if (tail != null)
            {
                tail = tail.prev;
                if (tail != null)
                    tail.next = null;
                else
                    head = null;
            }
        }

        node.next = head;
        if (head != null)
            head.prev = node;
        head = node;
        if (tail == null)
            tail = node;
        hashMap.put(key, node);
    }
}