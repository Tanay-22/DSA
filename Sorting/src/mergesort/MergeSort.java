package mergesort;

import java.util.Arrays;

// Time - O(N log N)
public class MergeSort
{
    static int[] mergeSort(int arr[])
    {
        if(arr.length <= 1)
            return arr;

        int mid = arr.length / 2;

        int left[] = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int right[] = mergeSort(Arrays.copyOfRange(arr, mid+1, arr.length));

        return merge(left, right);
    }

    private static int[] merge(int first[], int second[])
    {
        int mix[] = new int[first.length + second.length];

        int i = 0, j = 0, k = 0;
        while(i < first.length && j < second.length)
        {
            if(first[i] < second[j])
                mix[k++] = first[i++];
            else
                mix[k++] = second[j++];
        }
        while(i < first.length)
        {
            mix[k++] = first[i++];
        }
        while(j < second.length)
        {
            mix[k++] = second[j++];
        }

        return mix;
    }
    public static void main(String[] args)
    {
        int arr[] = {5,4,3,2,1};
        arr = mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
