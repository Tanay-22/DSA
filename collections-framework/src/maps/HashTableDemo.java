package maps;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashTableDemo
{
    public static void main(String[] args)
    {
        Hashtable<Integer, String> hashtable = new Hashtable<>();
////        Hashtable is synchronized
////        no null kry or value
////        Legacy class, ConcurrentHashMap
////        slower than HashMap because it is thread-safe
//        hashtable.put(1, "Apple");
//        hashtable.put(2, "Banana");
//        hashtable.put(3, "Cherry");
//        System.out.println(hashtable);

        Map<Integer, String> map = new HashMap<>();
        Thread thread1 = new Thread(() ->
        {
            for (int i = 0; i < 1000; i++)
            {
                hashtable.put(i, "Thread1");
            }
        });
        Thread thread2 = new Thread(() ->
        {
            for (int i = 0; i < 1000; i++)
            {
                hashtable.put(i, "Thread2");
            }
        });
        thread1.start();
        thread2.start();
        try
        {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Final size of the HashMap: " + hashtable.size());
    }
}
