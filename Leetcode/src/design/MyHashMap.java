package design;

import java.util.ArrayList;
import java.util.List;

class MyHashMap
{

    private List<Integer> keys;
    private List<Integer> values;
    public MyHashMap()
    {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public void put(int key, int value)
    {
        int alreadyPresent = this.keys.indexOf(key);

        if(alreadyPresent < 0)
        {
            this.keys.add(key);
            this.values.add(value);
            return;
        }
        this.values.set(alreadyPresent, value);
    }

    public int get(int key)
    {
        int index = this.keys.indexOf(key);

        if(index < 0)
            return -1;
        return this.values.get(index);
    }

    public void remove(int key)
    {
        int index = this.keys.indexOf(key);

        if(index < 0)
            return;
        this.values.remove(index);
        this.keys.remove(index);
    }
}