package searching;

import javax.xml.stream.events.EndDocument;
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
    public static void main(String[] args)
    {
        int arr[] = {2,2};
        System.out.println(minEatingSpeed(arr, 4));
    }
}
