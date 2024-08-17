public class MatrixChainMultiplication
{
    public int recursiveApproach(int[] arr)
    {
        return solve(arr, 0, arr.length-1);
    }
    private int solve(int[] arr, int i, int j)
    {
        if(i >= j)
            return 0;

        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++)
        {
            int temp = solve(arr, i, k) + solve(arr, k+1, j) + arr[i-1] * arr[k] * arr[j];
            min = Math.min(min, temp);
        }
        return min;
    }

    public int memorization(int[] arr)
    {
         int[][] dp = new int[arr.length][arr.length];
         return solveMemo(arr, 0, arr.length-1, dp);
    }
    private int solveMemo(int[] arr, int i, int j, int[][] dp)
    {
        if(i >= j)
            return 0;

        if(dp[i][j] != 0)
            return dp[i][j];

        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++)
        {
            int temp = solve(arr, i, k) + solve(arr, k+1, j) + arr[i-1] * arr[k] * arr[j];
            min = Math.min(min, temp);
        }
        return dp[i][j] = min;
    }
    private boolean isPalindrome(String s, int i, int j)
    {
        for (int k = i; k <= j/2; k++)
        {
            if(s.charAt(k) != s.charAt(s.length()-1-k))
                return false;
        }
        return true;
    }
    public int palindromePartition(String s, int i, int j, int[][] dp)
    {
        if(i >= j)
            return 0;

        if(isPalindrome(s, i, j))
            return 0;

        if(dp[i][j] != 0)
            return dp[i][j];

        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++)
        {
//            int temp = 1 + palindromePartition(s, i, k, dp) + palindromePartition(s, k+1, j, dp);
            int temp = 1;
            if(dp[i][k] != 0)
                temp += dp[i][k];
            else
                temp += palindromePartition(s, i, k, dp);

            if(dp[k+1][j] != 0)
                temp += dp[k+1][j];
            else
                temp += palindromePartition(s, k+1, j, dp);

            min = Math.min(min, temp);
        }
        return dp[i][j] = min;
    }
}
