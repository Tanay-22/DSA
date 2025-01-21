package maps;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapDemo
{
    public static void main(String[] args)
    {
        String key1 = new String("key");
        String key2 = new String("key");

        Map<String, Integer> map1 = new HashMap<>();
        map1.put(key1, 1);
        map1.put(key2, 2); // replaces key -> 1 with key -> 2

        System.out.println(map1);

        Map<String, Integer> map2 = new IdentityHashMap<>(); // IdentityHashcode and ==
        map2.put(key1, 1);
        map2.put(key2, 2);

        System.out.println(map2);

    }
}
