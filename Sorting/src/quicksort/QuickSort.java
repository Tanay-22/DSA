package quicksort;

import java.util.Arrays;

public class QuickSort
{
    static void sort(int arr[], int low, int high)
    {
        if(low >= high)
            return;

        int start = low, end = high;
        int mid = start + (end - start)/2;
        int pivot = arr[mid];
        while (start <= end)
        {
            while (arr[start] < pivot)
                start++;
            while (arr[end] > pivot)
                end--;
            if(start <= end)
            {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }
        sort(arr, low, end);
        sort(arr, start, high);
    }
    public static void main(String[] args)
    {
        int arr[] = {5,4,3,2,1};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
