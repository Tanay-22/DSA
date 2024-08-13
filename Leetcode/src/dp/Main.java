package dp;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    //  70. Climbing Stairs
    public static int climbStairs(int n)
    {
        if (n == 0 || n == 1)
            return 1;

        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

    public static List<List<Integer>> generate(int numRows)
    {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(1);

        for (int i = 1; i < numRows; i++)
        {
            List<Integer> l = new ArrayList<>();
            l.add(1);
            for (int j = 1; j < i; j++)
            {
                if (j != i)
                    l.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
            }
            l.add(1);
            list.add(l);
        }
        return list;
    }

    //  5. Longest Palindromic Substring
    public String longestPalindrome(String s)
    {
        return null;
    }

    //    322. Coin Change
    public static int coinChange(int[] coins, int amount)
    {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= amount; i++)
            dp[0][i] = Integer.MAX_VALUE - 1;

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= amount; j++)
            {
                if (coins[i - 1] <= j)
                    dp[i][j] = Math.min(dp[i][j - coins[i - 1]] + 1, dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[n][amount];
    }

    //  518. Coin Change II
    public static int change(int amount, int[] coins)
    {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= amount; j++)
            {
                if (coins[i - 1] <= j)
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][amount];
    }

    //  1143. Longest Common Subsequence
    public static int longestCommonSubsequence(String text1, String text2)
    {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }

    public static int longestCommonSubstring(String text1, String text2)
    {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;

        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
                else
                    dp[i][j] = 0;
            }
        }
        return maxLength;
    }

    public static String printingLongestCommonSubsequence(String text1, String text2)
    {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0)
        {
            if (text1.charAt(i - 1) == text2.charAt(j - 1))
            {
                sb.append(text1.charAt(i - 1));
                i--;
                j--;
            }
            else
            {
                if (dp[i][j - 1] > dp[i - 1][j])
                    j--;
                else
                    i--;
            }
        }
        return sb.reverse().toString();
    }

    private static int longestCommonSubsequence(String text1, String text2, int m, int n, int[][] memo)
    {
        if (m == 0 || n == 0)
            return 0;

        if (memo[m][n] != -1)
            return memo[m][n];

        if (text1.charAt(m - 1) == text2.charAt(n - 1))
            return memo[m][n] = 1 + longestCommonSubsequence(text1, text2, m - 1, n - 1, memo);

        return memo[m][n] = Math.max(longestCommonSubsequence(text1, text2, m, n - 1, memo),
                longestCommonSubsequence(text1, text2, m - 1, n, memo));
    }

    public static String shortestCommonSupersequence(String str1, String str2)
    {
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0)
        {
            if (str1.charAt(i - 1) == str2.charAt(j - 1))
            {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            }
            else
            {
                if (dp[i][j - 1] > dp[i - 1][j])
                    sb.append(str2.charAt(j-- - 1));
                else
                    sb.append(str1.charAt(i-- - 1));
            }

            if (j == 0 && i != 0)
            {
                while (i != 0)
                    sb.append(str1.charAt(i-- - 1));
            }

            if (i == 0 && j != 0)
            {
                while (j != 0)
                    sb.append(str2.charAt(j-- - 1));
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args)
    {
        int coins[] = {1, 2, 5}, amount = 5;
        System.out.println(shortestCommonSupersequence("abac", "cab"));
    }
}
