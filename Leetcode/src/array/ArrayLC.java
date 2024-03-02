package array;

import java.util.List;

public class ArrayLC
{
//    4. Median of Two Sorted Arrays
    public static double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        int len1 = nums1.length, len2 = nums2.length;
        int arr[] = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2)
        {
            if(nums1[i] < nums2[j])
                arr[k++] = nums1[i++];
            else
                arr[k++] = nums2[j++];
        }
        while (i < len1)
        {
            arr[k++] = nums1[i++];
        }
        while (j < len2)
        {
            arr[k++] = nums2[j++];
        }
        double median = 0;
        if(((len1 + len2) & 1) == 0)
            median = (arr[(len1+len2)/2] + arr[(len1+len2)/2-1])/2.0;
        else
            median = arr[(len1+len2)/2];
        return median;
    }

    public static void main(String[] args)
    {
        int arr1[] = {1,3}, arr2[] = {2};
        System.out.println(findMedianSortedArrays(arr1, arr2));
    }
}
