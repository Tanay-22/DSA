package dp;

import java.util.*;

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

    //  1092. Shortest Common Supersequence
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

    // 583. Delete Operation for Two Strings
    public static int minDistance(String word1, String word2)
    {
        int m = word1.length(), n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return m + n - 2 * dp[m][n];
    }

    //  516. Longest Palindromic Subsequence
    public int longestPalindromeSubseq(String s)
    {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (s.charAt(i - 1) == s.charAt(n - j))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n][n];
    }

    //  1312. Minimum Insertion Steps to Make a String Palindrome
    public int minInsertions(String s)
    {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (s.charAt(i - 1) == s.charAt(n - j))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return n - dp[n][n];
    }

    public static int longestRepeatingSubsequence(String str)
    {
        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (i != j && str.charAt(i - 1) == str.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n][n];
    }

    public static String printLongestRepeatingSubsequence(String str)
    {
        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (i != j && str.charAt(i - 1) == str.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = n, j = n;
        while (i > 0 && j > 0)
        {
            if (i != j && str.charAt(i - 1) == str.charAt(j - 1))
            {
                sb.append(str.charAt(i - 1));
                i--;
                j--;
            }
            else if (dp[i][j - 1] > dp[i - 1][j])
                j--;
            else
                i--;
        }
        return sb.reverse().toString();
    }

    //  132. Palindrome Partitioning II
    public static int minCut(String s)
    {
        int n = s.length();
        int[] dp = new int[n];
        boolean[][] palindrome = new boolean[n][n];

        for (int end = 0; end < n; end++)
        {
            dp[end] = end; // maximum cuts possible
            for (int start = 0; start <= end; start++)
            {
                if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || palindrome[start + 1][end - 1]))
                {
                    palindrome[start][end] = true;
                    if (start == 0)
                        dp[end] = 0; // substring is a palindrome
                    else
                        dp[end] = Math.min(dp[end], dp[start - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    private static boolean isPalindrome(String s, int i, int j)
    {
        while (i < j)
        {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    //  62. Unique Paths
    public static int uniquePaths(int m, int n)
    {
        int[][] dp = new int[m + 1][n + 1];

        return uniquePathsHelper(m, n, 1, 1, dp);
    }

    private static int uniquePathsHelper(int m, int n, int i, int j, int[][] dp)
    {
        if (m < i || n < j)
            return 0;

        if (dp[i][j] != 0)
            return dp[i][j];

        if (m == i || n == j)
            return 1;

        return dp[i][j] = uniquePathsHelper(m, n, i + 1, j, dp) + uniquePathsHelper(m, n, i, j + 1, dp);
    }


    public static int maxSubArray(int[] nums)
    {
        int max = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++)
        {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            max = Math.max(max, currentSum);
        }
        return max;
    }

    // 55. Jump Game
    public static boolean canJump(int[] nums)
    {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;

        for (int i = 0; i < n; i++)
        {
            if (dp[i])
            {
                for (int j = 1; j <= nums[i] && i + j < n; j++)
                    dp[i + j] = true;
            }
        }
//        return canJump(nums, 0);
        return dp[n - 1];
    }

    private static boolean canJump(int[] arr, int i)
    {
        if (i >= arr.length)
            return false;

        if (i == arr.length - 1)
            return true;

        boolean res = false;
        for (int j = 1; j <= arr[i]; j++)
            res = res || canJump(arr, i + j);

        return res;
    }

    //  7. Scramble String
    public static boolean isScramble(String s1, String s2)
    {
        Map<String, Boolean> map = new HashMap<>();
        if (s1.length() != s2.length())
            return false;

        int[] count = new int[26];

        for (int i = 0; i < s1.length(); i++)
        {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }

        for (int i : count)
        {
            if (i != 0)
                return false;
        }
        return isScrambleHelper(s1, s2, map);
    }

    private static boolean isScrambleHelper(String s1, String s2, Map<String, Boolean> map)
    {
        String key = s1 + " " + s2;
        if (s1.equals(s2))
        {
            map.put(key, true);
            return true;
        }
        if (s1.length() <= 1 || s2.length() <= 1)
            return false;

        if (map.containsKey(key))
            return map.get(key);

        boolean flag = false;
        for (int i = 1; i < s1.length(); i++)
        {
            boolean cond1 = isScrambleHelper(s1.substring(0, i), s2.substring(s2.length() - i), map) &&
                    isScrambleHelper(s1.substring(i), s2.substring(0, s2.length() - i), map);
            boolean cond2 = isScrambleHelper(s1.substring(0, i), s2.substring(0, i), map) &&
                    isScrambleHelper(s1.substring(i), s2.substring(i), map);

            if (cond1 || cond2)
            {
                flag = true;
                break;
            }
        }
        map.put(key, flag);
        return flag;
    }


    //  https://www.geeksforgeeks.org/problems/nth-catalan-number0817/1
    public static int findCatalan(int n)
    {
        int[] dp = new int[n + 1];
        dp[1] = dp[0] = 1;

        for (int i = 2; i <= n; i++)
        {
            for (int j = 0; j < i; j++)
            {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    //  https://www.geeksforgeeks.org/problems/find-optimum-operation4504/1
    public static int minOperation(int n)
    {
        /*
        if(n == 1)
            return 1;

        if (n == 0)
            return 0;

        if((n & 1) == 1)
            return 1 + minOperation(n - 1);
        else
            return 1 + minOperation(n / 2);
            */

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++)
        {
            if ((i & 1) == 1)
                dp[i] = 1 + dp[i - 1];
            else
                dp[i] = 1 + dp[i / 2];
        }
        return dp[n];
    }


    public static int minStepToDeleteString(String s)
    {
        int N = s.length();
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= N; j++)
                dp[i][j] = 0;

        for (int len = 1; len <= N; len++)
        {
            for (int start = 0, end = len - 1; end < N; start++, end++)
            {
                if (len == 1)
                    dp[start][end] = 1;

                else
                {
                    dp[start][end] = 1 + dp[start + 1][end];

                    if (s.charAt(start) == s.charAt(start + 1))
                        dp[start][end] = Math.min(1 + dp[start + 2][end], dp[start][end]);

                    for (int K = start + 2; K <= end; K++)
                    {
                        if (s.charAt(start) == s.charAt(K))
                            dp[start][end] = Math.min(dp[start + 1][K - 1] + dp[K + 1][end], dp[start][end]);
                    }
                }
            }
        }
        return dp[0][N - 1];
    }

    //  https://www.geeksforgeeks.org/problems/max-rope-cutting1312/1
    static long  maxProduct(int n)
    {
        /*if(n == 1)
            return 1;

        long res = 0L;
        for (int i = 1; i <= n; i++)
        {
            long furthurpeicies = maxProduct(n-i);
            res = Math.max(res, Math.max((long) i * (n-i), maxProduct(n-i) * i));
        }
        return res;*/

        long[] dp = new long[n+1];
        dp[0] = dp[1] = 0;

        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= i; j++)
                dp[i] = Math.max(dp[i], Math.max((long) (i - j) * j, dp[i-j] * j));
        }
        return dp[n];
    }
    static List<Integer> minPartition(int n)
    {
        List<Integer> list = new ArrayList<>();
        int[] value = { 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000 };

        for (int i = value.length-1; i >= 0; i--)
        {
            int num = n / value[i];
            n -= num * value[i];
            for (int j = 0; j < num; j++)
                list.add(value[i]);
        }
        return list;
    }

    static long countWays(int n)
    {
        long[] dp = new long[n+1];
        int MOD = 1000000007;
        return countWays(n, dp, MOD);
    }
    static long countWays(int n, long[] dp, int MOD)
    {
        if(n == 0)
            return 1;

        if(n <= 0)
            return 0;

        if(dp[n] != 0)
            return dp[n];

        return dp[n] = (countWays(n-1, dp, MOD) + countWays(n-2, dp, MOD) + countWays(n-3, dp, MOD)) % MOD;
    }


    public static int minOperations(String text1, String text2)
    {
//        return text1.length() + text2.length() - 2 * minOperations(text1, text2, 0, 0);

        int l1 = text1.length(), l2 = text2.length();
        int[][] dp = new int[l1+1][l2+1];

        for (int i = 1; i <= l1; i++)
        {
            for (int j = 1; j <= l2; j++)
            {
                if(text1.charAt(i-1) == text2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return l1 + l2 - 2 * dp[l1][l2];
    }
    /*public static int minOperations(String text1, String text2, int i, int j)
    {
        if(i == text1.length() || j == text2.length())
            return 0;

        if(text1.charAt(i) == text2.charAt(j))
            return 1 + minOperations(text1, text2, i+1, j+1);

        return Math.max(minOperations(text1, text2, i+1, j), minOperations(text1, text2, i, j+1));
    }*/

    public static void main(String[] args)
    {
        System.out.println(minOperations("heap", "pea"));
    }
}
