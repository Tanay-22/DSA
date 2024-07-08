package array;

import java.util.ArrayList;
import java.util.Arrays;
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
            if (nums1[i] < nums2[j])
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
        if (((len1 + len2) & 1) == 0)
            median = (arr[(len1 + len2) / 2] + arr[(len1 + len2) / 2 - 1]) / 2.0;
        else
            median = arr[(len1 + len2) / 2];
        return median;
    }


    public static int numberOfSubarrays(int[] arr, int k)
    {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            count += arr[i] & 1;
            arr[i] = count;
        }
        count = 0;

        for (int i = k; i <= n; i++)
        {
            for (int j = 0; j <= n - i; j++)
            {
                int prevCount = j > 0 ? arr[j - 1] : 0;
                int lastCount = arr[j + i - 1];

                if (lastCount - prevCount == k)
                    count++;
            }
        }
        return count;
    }

    //215. Kth Largest Element in an Array
    public static int findKthLargest(int[] arr, int k)
    {
        List<Integer> maxHeap = new ArrayList<>();

        for (int e : arr)
            insertInHeap(e, maxHeap);

        System.out.println(maxHeap);
        return 0;
    }

    private static void upheap(List<Integer> list, int i)
    {
        if (i == 0)
            return;
        int parent = (i - 1) / 2;

        if (list.get(i) >= list.get(parent))
        {
            int temp = list.get(i);
            list.set(i, list.get(parent));
            list.set(parent, temp);

            upheap(list, parent);
        }
    }

    private static void insertInHeap(int x, List<Integer> list)
    {
        list.add(x);
        upheap(list, list.size() - 1);
    }


    public static int triangleNumber(int[] nums)
    {
        int n = nums.length;
        if (n < 3) return 0;

        Arrays.sort(nums);
        int count = 0;

        for (int i = n - 1; i >= 2; i--)
        {
            int left = 0, right = i - 1;
            while (left < right)
            {
                if (nums[left] + nums[right] > nums[i])
                {
                    count += right - left;
                    right--;
                } else
                    left++;
            }
        }
        return count;
    }



    public static void main(String[] args)
    {
        int arr[] = {1,2,3,0,0,0};
        int arr2[] = {2,5,6};

        Arrays.stream(arr).forEach(System.out::println);
    }
}
