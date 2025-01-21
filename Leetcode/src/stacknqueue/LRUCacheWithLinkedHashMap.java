package stacknqueue;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCacheWithLinkedHashMap
{
    private final Map<Integer, Integer> map;
    private final int capacity;

    public LRUCacheWithLinkedHashMap(int capacity)
    {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public int get(int key)
    {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value)
    {
        if(map.containsKey(key) || map.size() < capacity)
            map.put(key, value);
        else
        {
            Integer eldest = map.keySet().iterator().next();
            map.remove(eldest);
            map.put(key, value);
        }
    }
}