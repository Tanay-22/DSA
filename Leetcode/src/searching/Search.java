package searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Search
{
//  875. Koko Eating Bananas
    public static int minEatingSpeedHelper(int piles[], int mid)
    {
        int hours = 0;
        if(mid > 0)
        {
            for (int e: piles)
            {
                if(e % mid != 0)
                    hours += e/mid + 1;
                else
                    hours += e/mid;
            }
            return hours;
        }
        else
            return 0;
    }
    public static int minEatingSpeed(int[] piles, int h)
    {
        int len = piles.length;
        int start = 1, end = piles[0];
        for (int e: piles)
        {
            if(e > end)
                end = e;
        }

        while (start < end)
        {
            int mid = start +(end - start)/2;
            int hours = minEatingSpeedHelper(piles, mid);
            if(hours == h)
            {
                while (true)
                {
                    if(minEatingSpeedHelper(piles, mid-1) == h)
                        mid = mid - 1;
                    else
                        break;
                }
                return mid;
            }
            else if (hours > h)
                start = mid + 1;
            else
                end = mid;
        }
        return end;
    }

    //754. Reach a Number
    public static int reachNumber(int target)
    {
        int count = 1;
        int start = 1, end = Math.abs(target);

        while(start < end)
        {
            count++;

            if(start + count == end)
                return count;

            if(start + count > end)
                start -= count;
            else
                start += count;
        }
        return count;
    }

    //315. Count of Smaller Numbers After Self
    public static List<Integer> countSmaller(int[] arr)
    {
        List<Integer> list = new ArrayList<>();
        List<Integer> compared = new ArrayList<>();

        for(int e: arr)
            compared.add(e);

        Collections.sort(compared);
        int n = arr.length;
        for (int i = 0; i < n; i++)
        {
            int pos = findIndex(compared, 0, compared.size()-1, arr[i]);
            compared.remove(pos);
            list.add(pos);
        }
        return list;
    }
    private static int findIndex(List<Integer> list, int start, int end, int val)
    {
        if(start > end)
            return start;

        int mid = start + (end-  start) / 2;

        if(list.get(mid) >= val)
            return findIndex(list, start, mid-1, val);
        else
            return findIndex(list, mid+1, end, val);
    }


    public static void main(String[] args)
    {
        int arr[] = {1,0,-1,0,-2,2};
    }
}
