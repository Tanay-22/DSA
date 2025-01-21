package maps;

import java.util.*;

public class SortedMapDemo
{
    public static void main(String[] args)
    {
//        https://docs.oracle.com/javase/8/docs/api/java/util/SortedMap.html
        Map<Integer, String> sortedMap = new SortedMap<Integer, String>()
        {
            @Override
            public Comparator<? super Integer> comparator()
            {
                return null;
            }

            @Override
            public SortedMap<Integer, String> subMap(Integer fromKey, Integer toKey)
            {
                return null;
            }

            @Override
            public SortedMap<Integer, String> headMap(Integer toKey)
            {
                return null;
            }

            @Override
            public SortedMap<Integer, String> tailMap(Integer fromKey)
            {
                return null;
            }

            @Override
            public Integer firstKey()
            {
                return 0;
            }

            @Override
            public Integer lastKey()
            {
                return 0;
            }

            @Override
            public Set<Integer> keySet()
            {
                return Set.of();
            }

            @Override
            public Collection<String> values()
            {
                return List.of();
            }

            @Override
            public Set<Entry<Integer, String>> entrySet()
            {
                return Set.of();
            }

            @Override
            public int size()
            {
                return 0;
            }

            @Override
            public boolean isEmpty()
            {
                return false;
            }

            @Override
            public boolean containsKey(Object key)
            {
                return false;
            }

            @Override
            public boolean containsValue(Object value)
            {
                return false;
            }

            @Override
            public String get(Object key)
            {
                return "";
            }

            @Override
            public String put(Integer key, String value)
            {
                return "";
            }

            @Override
            public String remove(Object key)
            {
                return "";
            }

            @Override
            public void putAll(Map<? extends Integer, ? extends String> m)
            {

            }

            @Override
            public void clear()
            {

            }
        };

        SortedMap<Integer, String> map = new TreeMap<>(); // Red Black tree
        map.put(10, "Messi");
        map.put(9, "Suarez");
        map.put(11, "Neymar");

//        System.out.println(map);
//        System.out.println(map.firstKey());
//        System.out.println(map.lastKey());
//        System.out.println(map.headMap(10)); // exclusive
//        System.out.println(map.tailMap(10)); // inclusive

//        https://docs.oracle.com/javase/8/docs/api/java/util/NavigableMap.html
        NavigableMap<Integer, String> navigableMap = new TreeMap<>();
        navigableMap.put(1, "One");
        navigableMap.put(5, "Five");
        navigableMap.put(3, "Three");
        System.out.println(navigableMap);
        System.out.println(navigableMap.lowerKey(2));
    }
}
