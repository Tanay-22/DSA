package maps;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListDemo
{
    public static void main(String[] args)
    {
//        Thread safe + sorted
        Map<Integer, String> map = new ConcurrentSkipListMap<>();
    }
}
