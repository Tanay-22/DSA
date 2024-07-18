import java.util.Arrays;

public class KnapSack
{
    public int knapSack01Memorization(int W, int[] wt, int[] val, int n)
    {
        int[][] dp = new int[n+1][W+1];
        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= W; j++)
                dp[i][j] = -1;
        }
        return knapSack01Memorization(W, wt, val, n, dp);
    }
    private int knapSack01Memorization(int W, int[] wt, int[] val, int n, int[][] dp)
    {
        if(W == 0 || n == 0)
            return 0;

        if(dp[n][W] != -1)
            return dp[n][W];

        if(wt[n-1] <= W)
        {
            dp[n][W] = Math.max(val[n-1] + knapSack01Memorization(W - wt[n-1], wt, val, n-1, dp),
                    knapSack01Memorization(W, wt, val, n-1, dp));
            return dp[n][W];
        }

        else if (wt[n-1] > W)
        {
            dp[n][W] = knapSack01Memorization(W, wt, val, n-1, dp);
            return dp[n][W];
        }
        else
            return 0;
    }


    // Top-Down Approach
    public int knapSackTopDown(int W, int[] wt, int[] val, int n)
    {
        int[][] dp = new int[n+1][W+1];

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= W; j++)
            {
                if(wt[i-1] <= j)
                    dp[i][j] = Math.max(val[i-1] + dp[i-1][j-wt[i-1]], dp[i-1][j]);
                else
                    dp[i][j] = dp[i-1][j];
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
                if(arr[i-1] <= j)
                    dp[i][j] = dp[i-1][j - arr[i - 1]] || dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[N][sum];
    }

    public static void main(String[] args)
    {
        KnapSack knapSack = new KnapSack();
        int[] arr = {3, 34, 4, 12, 5, 2};
        System.out.println(knapSack.isSubsetSum(6, arr, 30));
    }
}
