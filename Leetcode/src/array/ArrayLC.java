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

    //  1823. Find the Winner of the Circular Game
    public static int findTheWinner(int n, int k)
    {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(i);

        int start = 0;
        while (list.size() != 1)
        {
            int knockout = (start + k - 1) % list.size();
            list.remove(knockout);
            start = knockout;
            if(start >= list.size())
                start -= list.size();
        }
        return list.get(0);
    }
    private static boolean isOdd(int num)
    {
        return (num & 1) == 1;
    }

    //  1605. Find Valid Matrix Given Row and Column Sums
    public static int[][] restoreMatrix(int[] rowSum, int[] colSum)
    {
        int m = rowSum.length, n = colSum.length;
        int[][] arr = new int[m][n];

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(rowSum[i] == 0 || colSum[j] == 0)
                    continue;
                else
                {
                    int min = Math.min(rowSum[i], colSum[j]);
                    arr[i][j] = min;
                    rowSum[i] -= min;
                    colSum[j] -= min;
                }
            }
        }
        return arr;
    }

    //  1380. Lucky Numbers in a Matrix
    public static List<Integer> luckyNumbers (int[][] matrix)
    {
        int m = matrix.length, n = matrix[0].length;

        int[] rowMin = new int[m];
        int[] colMax = new int[n];

        for(int i = 0; i < m; i++)
        {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++)
            {
                if(min > matrix[i][j])
                {
                    min = matrix[i][j];
                    rowMin[i] = j;
                }
            }
        }
        for(int j = 0; j < n; j++)
        {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++)
            {
                if(max < matrix[i][j])
                {
                    max = matrix[i][j];
                    colMax[j] = i;
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++)
        {
            if(i == colMax[rowMin[i]])
                list.add(matrix[i][rowMin[i]]);
        }
        return list;
    }


    public static int rangeSum(int[] nums, int n, int left, int right)
    {
        int mod = (int)1e9 + 7;
        int sum[] = new int[n*(n+1)/2];
        int c = 0;
        for (int i = 0; i < n; i++)
        {
            int s = nums[i];
            for (int j = i+1; j < n; j++)
            {
                sum[c++] = s;
                s += nums[j];
            }
            sum[c++] = s;
        }
        long res = 0l;
        Arrays.sort(sum);
        for (int i = left-1; i < right; i++)
            res += sum[i];

        return (int)(res % mod);
    }


    public static int minSubArrayLen(int target, int[] nums)
    {
        int n = nums.length;
        int left = 0, right = 0;
        long sum = 0L;
        int minLength = Integer.MAX_VALUE;

        while(right < n)
        {
            sum += nums[right++];
            while (sum >= target)
            {
                minLength = Math.min(minLength, right - left);
                sum -= nums[left++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    //  885. Spiral Matrix III
    public static int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart)
    {
        List<int[]> list = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int limit = rows * cols;
        int r = rStart, c = cStart;
        int d = 0, steps = 1;
        while (list.size() < limit)
        {
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < steps; j++)
                {
                    if(r >= 0 && r < rows && c >= 0 && c < cols)
                        list.add(new int[]{r, c});
                    if(list.size() == limit)
                        return convertIndexToArray(list, rows, cols);
                    r += directions[d][0];
                    c += directions[d][1];
                }
                d = (d + 1) % 4;
            }
            steps++;
        }
        return convertIndexToArray(list, rows, cols);
    }
    private static int[][] convertIndexToArray(List<int[]> list, int rows, int cols)
    {
        int[][] arr = new int[rows * cols][2];
        int c = 0;
        for (int i = 0; i < rows * cols; i++)
            arr[i] = list.get(i);

        return arr;
    }


    public static void main(String[] args)
    {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));   // return 4
        System.out.println(kthLargest.add(5));   // return 5
        System.out.println(kthLargest.add(10));  // return 5
        System.out.println(kthLargest.add(9));   // return 8
        System.out.println(kthLargest.add(4));   // return 8
    }
}
