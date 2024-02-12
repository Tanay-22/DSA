import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    //  SEARCH IN 2D ARRAY
    public static int findRow(int arr[][], int target)
    {
        int start = 0, end = arr.length - 1;
        while(start <= end)
        {
            int mid = start + (end - start)/2;
            if(arr[mid][0] < target)
                start = mid + 1;
            else if(arr[mid][0] > target)
                end = mid - 1;
            else
                return mid;
        }
        System.out.println(start + " " + end);
        return end;
    }
    public static boolean searchMatrix(int[][] arr, int target)
    {
        int rowNo = findRow(arr, target);
        System.out.println(rowNo);
        if(rowNo < 0 || rowNo >= arr.length)
            return false;
        int start = 0, end = arr[rowNo].length - 1;
        while(start <= end)
        {
            int mid = start + (end - start) / 2;
            if(arr[rowNo][mid] < target)
                start = mid + 1;
            else if(arr[rowNo][mid] > target)
                end = mid - 1;
            else
                return true;
        }
        return false;
    }

    // 81. Search in Rotated Sorted Array II
    static int res = -1;
    public static void rotatedSortedArrayGreatestIndex(int arr[], int start, int end)
    {
        if(start < end)
        {
            int mid = start + (end - start)/2;
            if(arr[mid] > arr[mid+1])
                res = mid;
            else
            {
                rotatedSortedArrayGreatestIndex(arr,start, mid);
                rotatedSortedArrayGreatestIndex(arr, mid+1, end);
            }
        }
    }
    public static int binSearch(int arr[], int start, int end, int target)
    {
        while( start < end)
        {
            int mid = start + (end - start)/2;
            if(arr[mid] == target)
                return mid;
            else if ( arr[mid] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }
    public static boolean search(int[] arr, int target)
    {
        rotatedSortedArrayGreatestIndex(arr, 0, arr.length-1);
        int result = binSearch(arr, 0, res, target);
        if(result == -1)
            result = binSearch(arr,res+1,arr.length-1,target);
        if(result == -1)
            return false;
        return true;
    }

    //167. Two Sum II - Input Array Is Sorted
    public static int[] twoSum(int[] arr, int target)
    {
        int res[] = {-1, -1};
        int start = 0, end = arr.length - 1;
        while (start < end)
        {
            int mid = start + (end - start)/2;
            System.out.println(mid);
            if(arr[mid] + arr[mid+1] == target)
            {
                res[0] = mid + 1;
                res[1] = mid + 2;
                return res;
            }
            else if(arr[mid] + arr[mid+1] < target)
                start = mid + 1;
            else
                end = mid;
        }
        return res;
    }

   /* public int minEatingSpeed(int[] piles, int h)
    {
        Arrays.sort(piles);
        int k = h - piles.length;
        if(k == 0)
            return piles[piles.length - 1];
        else
        {
            int res = piles[piles.length - k];
            if(piles[res+1] > 2 * res)
                res =
        }
    }*/

    public static int[] findRightInterval(int[][] arr)
    {
        int len = arr.length;
        if(len == 1)
            return new int[]{-1};
        else
        {
            int res[] = new int[len];
            for (int i = 0; i < len; i++)
            {
                res[i] = -1;
                if(arr[i][1] == arr[i][0])
                {
                    res[i] = i;
                    continue;
                }
                int end = arr[i][1];
                int min = 100000;
                for (int j = 0; j < len; j++)
                {
                    if (i == j)
                        continue;
                    int start = arr[j][0];
                    if (end <= start && min >= start)
                    {
                        res[i] = j;
                        min = start;
                    }
                }
            }
            return res;
        }
    }
    public static String restoreString(String s, int[] indices)
    {
        char ch[] = new char[indices.length];
        for (int i = 0; i < indices.length; i++)
        {
            ch[indices[i]] = s.charAt(i);
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < indices.length; i++)
        {
            str.append(ch[i]);
        }
        return str.toString();

    }

    public static int singleNonDuplicate(int[] arr)
    {
        int start = 0, end = arr.length - 1;
        while (start < end)
        {
            int mid = start + (end - start) / 2;
            if(mid % 2 == 1)
                mid--;
            if(arr[mid] != arr[mid+1])
                end = mid;
            else
                start = mid + 2;
        }
        return arr[start];
    }

    public static void main(String[] args)
    {
        int arr[] ={1,1,2,3,3,4,4,8,8};
        System.out.println(singleNonDuplicate(arr)); ;
    }
}