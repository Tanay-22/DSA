package array;

import java.util.*;

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

    //  167. Two Sum II - Input Array Is Sorted
    public static int[] twoSum(int[] arr, int target)
    {
        int i = 0, j = arr.length - 1;
        while (i < j)
        {
            if (arr[i] + arr[j] < target)
                i++;
            else if (arr[i] + arr[j] > target)
                j--;
            else
                return new int[]{i, j};
        }
        return new int[]{-1, -1};
    }

    //    11. Container With Most Water
    public static int maxArea(int[] height)
    {
        int i = 0, j = height.length - 1;
        int max = 0;
        while (i < j)
        {
            max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
            if (height[i] < height[j])
                i++;
            else
                j--;
        }
        return max;
    }

    //  15. 3Sum
    public static List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++)
        {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1]))
            {
                int second = i + 1, third = nums.length - 1;
                while (second < third)
                {
                    int sum = nums[i] + nums[second] + nums[third];
                    if (sum == 0)
                    {
                        list.add(Arrays.asList(nums[i], nums[second], nums[third]));
                        while (second < third && nums[second] == nums[second + 1])
                            second++;
                        while (second < third && nums[third] == nums[third - 1])
                            third--;
                        second++;
                        third--;
                    }
                    else if (sum > 0)
                        third--;
                    else
                        second++;
                }
            }
        }
        return list;
    }

    //  18. 4Sum
    public static List<List<Integer>> fourSum(int[] nums, int target)
    {
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 3; k++)
        {
            for (int i = k+1; i < nums.length - 2; i++)
            {
                int second = i + 1, third = nums.length - 1;
                while (second < third)
                {
                    long sum = (long)nums[k] + (long)nums[i] + (long)nums[second] + (long)nums[third];
                    if (sum == target)
                    {
                        set.add(Arrays.asList(nums[k], nums[i], nums[second], nums[third]));
                        second++;
                        third--;
                    }
                    else if (sum > target)
                        third--;
                    else
                        second++;
                }
            }
        }
        return new ArrayList<>(set);
    }

    //  16. 3Sum Closest
    public static int threeSumClosest(int[] nums, int target)
    {
        int minDiff = Integer.MAX_VALUE;
        int closest = Integer.MAX_VALUE/2;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++)
        {
            int second = i + 1, third = nums.length - 1;
            while (second < third)
            {
                int sum = nums[i] + nums[second] + nums[third];
                if(sum == target)
                    return sum;
                if (minDiff > Math.abs(sum - target))
                {
                    minDiff = Math.abs(sum - target);
                    closest = sum;
                }
                if (sum > target)
                    third--;
                else
                    second++;
            }
        }
        return closest;
    }

    //  923. 3Sum With Multiplicity
    public static int threeSumMulti(int[] arr, int target)
    {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0l) + 1);

        long mod = (long)1e9 + 7;
        long res = 0l;

        for (Integer i: hashMap.keySet())
        {
            for (Integer j: hashMap.keySet())
            {
                Integer k = target - i - j;
                if(k < 0 || k > 100)
                    continue;

                    // nC3
                if(i.equals(j) && j.equals(k))
                {
                    long count = hashMap.get(i);
                    res += (count * (count - 1) * (count - 2)) / 6;
                }
                //  nC2 * unique
                else if(i.equals(j) && !j.equals(k))
                {
                    long count = hashMap.get(i);
                    res += (count * (count - 1) / 2) * hashMap.getOrDefault(k, 0l);
                }

                else if(i < j && j < k)
                    res += (hashMap.get(i) * hashMap.get(j) * hashMap.getOrDefault(k, 0l));
            }
        }
        return (int)(res % mod);
    }

    //  289. Game of Life
    public static void gameOfLife(int[][] board)
    {
        int m = board.length, n = board[0].length;
        int result[][] = new int[m][n];
        int directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int livingNeighbourCount = 0;
                int deadNeighbourCount = 0;
                for (int direction[]: directions)
                {
                    int nrow = i + direction[0];
                    int ncol = j + direction[1];

                    if(nrow < 0 || nrow == m || ncol < 0 || ncol == n)
                        continue;
                    if(board[nrow][ncol] == 1)
                        livingNeighbourCount++;
                    else
                        deadNeighbourCount++;
                }
                if(board[i][j] == 1)
                {
                    if(livingNeighbourCount == 2 || livingNeighbourCount == 3)
                        result[i][j] = 1;
                }
                else
                {
                    if(livingNeighbourCount == 3)
                        result[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
                board[i][j] = result[i][j];
        }
    }


    public static int removeElement(int[] nums, int val)
    {
        int j = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if(nums[i] != val)
                nums[j++] = nums[i];
        }
        return j;
    }


    public static void main(String[] args)
    {
        int[] arr = {0,1,2,2,3,0,4,2};
        System.out.println(removeElement(arr, 2));
    }
}
