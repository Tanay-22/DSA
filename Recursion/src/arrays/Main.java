package arrays;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main
{
    static boolean isSorted(int arr[], int i)
    {
        if(i == arr.length - 1)
            return true;
        else
        {
            return (arr[i] < arr[i+1]) && isSorted(arr, i+1);
        }
    }

    static int linearSearch(int arr[], int i, int target)
    {
        if(i == arr.length)
            return -1;
        else
        {
            if(arr[i] == target)
                return i;
            else
                return linearSearch(arr, i+1, target);
        }
    }

    static ArrayList<Integer> findAllIndex2(int arr[], int target, int index)
    {
        ArrayList<Integer> list = new ArrayList<>();

        if(index == arr.length)
            return list;

        if(arr[index] == target)
            list.add(index);

        ArrayList<Integer> ansFromBelowCalls = findAllIndex2(arr, target, index+1);
        list.addAll(ansFromBelowCalls);
        return list;
    }

    static int rotatedBS(int arr[], int target, int start, int end)
    {
        if(start > end)
            return -1;

        int mid = start + (end - start)/2;
        if(arr[mid] == target)
            return mid;

        if(arr[start] <= arr[mid])
        {
            if(target >= arr[start] && target <= arr[mid])
                return rotatedBS(arr, target, start, mid-1);
            else
                return rotatedBS(arr, target, mid+1, end);
        }

        if(target >= arr[mid] && target <= arr[end])
            return rotatedBS(arr, target, mid+1, end);

        return rotatedBS(arr, target, start, mid-1);

    }

    public static List<Integer> spiralOrder(int[][] arr)
    {
        List<Integer> list = new ArrayList<>();
        int m = arr.length, n = arr[0].length;
        int top = 0, bottom = m-1, left = 0, right = n-1;
        while(top <= bottom && left <= right)
        {
            for (int i = left; i <= right; i++)
                list.add(arr[top][i]);
            top++;
            for (int i = top; i <= bottom; i++)
                list.add(arr[i][right]);
            right--;
            if(top <= bottom)
            {
                for (int i = right; i >= left; i--)
                    list.add(arr[bottom][i]);
                bottom--;
            }
            if(left <= right)
            {
                for (int i = bottom; i >= top; i--)
                    list.add(arr[i][left]);
                left++;
            }
        }
        return list;
    }

    public static int[][] generateMatrix(int n)
    {
        int arr[][] =new int[n][n];
        int top = 0, bottom = n-1, left = 0, right = n-1, count = 1;
        while(top <= bottom && left <= right)
        {
            for (int i = left; i <= right; i++)
                arr[top][i] = count++;
            top++;
            for (int i = top; i <= bottom; i++)
                arr[i][right] = count++;
            right--;
            if(top <= bottom)
            {
                for (int i = right; i >= left; i--)
                    arr[bottom][i] = count++;
                bottom--;
            }
            if(left <= right)
            {
                for (int i = bottom; i >= top; i--)
                    arr[i][left] = count++;
                left++;
            }
        }
        return arr;
    }

    public static void rotate(int[] arr, int k)
    {
        int n = arr.length;
        k = k % n;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
             list.add(arr[(n + i - k) % n]);
        for (int i = 0; i < n; i++)
            arr[i] = list.get(i);
    }

    public static void setZeroes(int[][] arr)
    {
        List<int[]> list = new ArrayList<>();
        int m = arr.length, n = arr[0].length;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(arr[i][j] == 0)
                    list.add(new int[]{i, j});
            }
        }
        for (int zero[] : list)
        {
            for (int i = 0; i < n; i++)
                arr[zero[0]][i] = 0;
            for (int i = 0; i < m; i++)
                arr[i][zero[1]] = 0;
        }
    }

    public static int[] productExceptSelf(int[] arr)
    {
        int product = 1;
        int zeroCount = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if(arr[i] == 0)
                zeroCount++;
            product *= (arr[i] != 0) ? arr[i] : 1;
        }

        for (int i = 0; i < arr.length; i++)
        {
            int a = (zeroCount > 0) ? 0 : product / arr[i];
            if(zeroCount == arr.length)
                arr[i] = 0;
            else
                arr[i] = (arr[i] == 0) ? product : a;
        }
        return arr;
    }

    /*public static int rob(int[] arr)
    {
        int sum = 0;
        for (int i = 0; i < arr.length; i+=2)
        {
            if(i == 0)
            {
                if(arr[0] > arr[1])
                    sum += arr[0];
                else
                    sum += arr[1];
            }
            if(sum == arr[1] && i < 2)
                i = 1;
        }
    }*/

    public static int[] twoSum(int[] arr, int target)
    {
        int indexs[] = new int[2];
        for (int i = 0; i < arr.length-1; i++)
        {
            int sum = arr[i];
            for (int j = i+1; j < arr.length; j++)
            {
                sum += arr[j];
                if(sum == target)
                {
                    indexs[0] = i;
                    indexs[1] = j;
                    break;
                }
                else
                    sum = arr[i];
            }
            if (sum == target)
                break;
        }
        return indexs;
    }

    public static int search(int[] arr, int target)
    {
        int start = 0, end = arr.length-1;

        while (start <= end)
        {
            int mid = start + (end - start) / 2;
            if(arr[mid] == target)
                return mid;

            if (arr[start] < arr[mid])
            {
                if (arr[start] <= target && target < arr[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            else if (arr[mid] < arr[end])
            {
                if (arr[mid] < target && target <= arr[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
            else
                start++;
        }
        return -1;
    }

    public static void rotate(int[][] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n; i++)
        {
            for (int j = i; j < n; j++)
            {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n/2; j++)
            {
                int temp = arr[i][j];
                arr[i][j] = arr[i][n - 1 - j];
                arr[i][n - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args)
    {
        int arr[][] = {{5, 1, 9, 11}, {2, 4, 8, 10},  {13, 3, 6, 7},  {15, 14, 12, 16}};
        rotate(arr);
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }
}
