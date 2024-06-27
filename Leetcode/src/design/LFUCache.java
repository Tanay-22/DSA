package design;


import java.util.HashMap;

// 460. LFU Cache
class LFUCache
{
    private class Node
    {
        int key;
        int value;
        int counter;
        Node prev;
        Node next;

        public Node(int key, int value)
        {
            this.key = key;
            this.value = value;
            this.counter = 1;
        }
    }

    HashMap<Integer, Node> hashMap;
    private int capacity;

    private Node head;

    private Node tail;

    public LFUCache(int capacity)
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
            res.counter++;
            this.removeNode(res);
            this.addNode(res);

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
        if (hashMap.size() >= capacity)
        {
            Node remove = this.findMin();
            hashMap.remove(remove.key);

            this.removeNode(remove);
        }
        Node node = new Node(key, value);
        this.addNode(node);
        hashMap.put(key, node);
    }

    private Node findMin()
    {
        Node node = null;
        Node temp = head;
        int min = Integer.MAX_VALUE;

        while (temp != null)
        {
            if(temp.counter <= min)
            {
                min = temp.counter;
                node = temp;
            }
            temp = temp.next;
        }
        return node;
    }

    private void addNode(Node node)
    {
        node.next = head;
        node.prev = null;

        if(head != null)
            head.prev = node;
        head = node;

        if(tail == null)
            tail = node;
    }

    private void removeNode(Node node)
    {
        if(node.prev != null)
            node.prev.next = node.next;
        else
            head = node.next;   // remove head

        if(node.next != null)
            node.next.prev = node.prev;
        else
            tail = node.prev;   // remove tail
    }
}