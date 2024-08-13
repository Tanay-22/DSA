import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountSort
{
    public static void countSort(int[] arr)
    {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++)
            max = Math.max(max, arr[i]);

        int[] count = new int[max + 1];
        for (int n: arr)
            count[n]++;

        int index = 0;
        for (int i = 0; i < count.length; i++)
        {
            while (count[i] > 0)
            {
                arr[index++] = i;
                count[i]--;
            }
        }
    }
    public static void countSortWithHashMap(int[] arr)
    {
        int max = Arrays.stream(arr).max().getAsInt();
        int min  = Arrays.stream(arr).min().getAsInt();

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n: arr)
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);

        int index = 0;
        for (int i = min; i <= max; i++)
        {
            int count = countMap.getOrDefault(i, 0);
            for (int j = 0; j < count; j++)
                arr[index++] = i;
        }
    }

    public static void main(String[] args)
    {
        int[] arr ={3, 4, 1, 3, 2, 5, 2 ,8};
        countSortWithHashMap(arr);
        Arrays.stream(arr).forEach(num -> System.out.print(num + " "));
    }
}
