import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapSack
{
    public int knapSack01Memorization(int W, int[] wt, int[] val, int n)
    {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= W; j++)
                dp[i][j] = -1;
        }
        return knapSack01Memorization(W, wt, val, n, dp);
    }

    private int knapSack01Memorization(int W, int[] wt, int[] val, int n, int[][] dp)
    {
        if (W == 0 || n == 0)
            return 0;

        if (dp[n][W] != -1)
            return dp[n][W];

        if (wt[n - 1] <= W)
        {
            dp[n][W] = Math.max(val[n - 1] + knapSack01Memorization(W - wt[n - 1], wt, val, n - 1, dp),
                    knapSack01Memorization(W, wt, val, n - 1, dp));
            return dp[n][W];
        }

        else if (wt[n - 1] > W)
        {
            dp[n][W] = knapSack01Memorization(W, wt, val, n - 1, dp);
            return dp[n][W];
        }
        else
            return 0;
    }


    // Top-Down Approach
    public int knapSackTopDown(int W, int[] wt, int[] val, int n)
    {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= W; j++)
            {
                if (wt[i - 1] <= j)
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][W];
    }

    public static Boolean isSubsetSum(int N, int arr[], int sum)
    {
        boolean[][] dp = new boolean[N + 1][sum + 1];
        for (int i = 0; i <= N; i++)
            dp[i][0] = true;

        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= sum; j++)
            {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[N][sum];
    }

    public static boolean equalSumPartition(int[] arr)
    {
        // sum of array should be even
        int sum = Arrays.stream(arr).sum();
        if ((sum & 1) == 1)
            return false;

        return isSubsetSum(arr.length, arr, sum / 2);

    }

    //  Count of Subset of a given Sum
    public static int countSubsetSum(int N, int arr[], int sum)
    {
        int[][] dp = new int[N + 1][sum + 1];

        for (int i = 0; i <= N; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= sum; j++)
            {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[N][sum];
    }

    // Minimum Subset Sum Difference
    public static int minSubsetSumDiff(int N, int arr[])
    {
        int sum = Arrays.stream(arr).sum();
        boolean[][] dp = new boolean[N + 1][sum + 1];
        for (int i = 0; i <= N; i++)
            dp[i][0] = true;

        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= sum; j++)
            {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= sum / 2; i++)
        {
            if (dp[N][i])
                min = Math.min(min, sum - 2 * i);
        }
        return min;
    }

    // Count the number of subset with a given difference
    public static int countSubsetSumDiff(int N, int arr[], int diff)
    {
        int sum = Arrays.stream(arr).sum();
        if ((sum - diff) % 2 != 0)
            return 0; // If sum - diff is not even, return 0
        int target = (sum - diff) / 2;

        int[] dp = new int[target + 1];
        dp[0] = 1; // There's one way to have a subset with sum 0: the empty subset

        for (int i = 0; i < N; i++)
        {
            for (int j = target; j >= arr[i]; j--)
                dp[j] += dp[j - arr[i]];
        }
        return dp[target];
    }

    public static int targetSum(int arr[], int sum)
    {
        int n = arr.length;
        return countSubsetSumDiff(n, arr, sum);
    }

    //  -------------------------------------------
    // UNBOUNDED KNAPSACK
    public int unboundedKnapSack(int W, int[] wt, int[] val, int n)
    {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= W; j++)
            {
                if (wt[i - 1] <= j)
                    dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][W];
    }


    public static void main(String[] args)
    {
        int arr[] = {3, 1, 4, 2, 2, 1};
        int n = arr.length;
        System.out.print("The minimum difference"
                + " between two sets is "
                + minSubsetSumDiff(n, arr));
    }
}
