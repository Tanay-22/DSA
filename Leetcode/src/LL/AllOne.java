package LL;

public class AllOne
{
    private class Key
    {
        private int count;
        private String str;
        private Key next;

        public Key(String str)
        {
            this.str = str;
            this.count = 1;
            this.next = null;
        }
    }
    private Key head;

    public AllOne()
    {
        head = new Key(null);
        head.count = Integer.MIN_VALUE;
    }

    public void inc(String key)
    {
        Key temp = head.next;
        while (temp != null)
        {
            if(temp.str == key)
            {
                temp.count++;
                return;
            }
            temp = temp.next;
        }
        temp = head;
        while (temp.next != null)
            temp = temp.next;
        Key newKey = new Key(key);
        temp.next = newKey;
    }

    public void dec(String key)
    {
        Key temp = head.next;
        while (temp != null)
        {
            if(temp.str == key)
            {
                temp.count--;

                if(temp.count < 0)
                    temp.next = temp.next.next;
            }
            temp = temp.next;
        }
    }

    public String getMaxKey()
    {
        int max = Integer.MIN_VALUE;
        String s = "";
        Key temp = head.next;

        while (temp != null)
        {
            if(temp.count > max)
            {
                max = temp.count;
                s = temp.str;
            }
            temp = temp.next;
        }
        return s;
    }

    public String getMinKey()
    {
        int min = Integer.MAX_VALUE;
        String s = "";
        Key temp = head.next;

        while (temp != null)
        {
            if(temp.count < min)
            {
                min = temp.count;
                s = temp.str;
            }
            temp = temp.next;
        }
        return s;
    }
}
