import java.util.Arrays;

public class EggDropping
{
    public static int recursive(int e, int f)
    {
        if (f <= 1 || e == 1)
            return f;

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= f; i++)
        {
            // worst case between breaking and no breaking
            int temp = 1 + Math.max(recursive(e - 1, i - 1), recursive(e, f - i));
            min = Math.min(min, temp);
        }
        return min;
    }


    public static int memo(int e, int f, int[][] dp)    // dp = int[e+1][f+1]
    {
        if (f <= 1 || e == 1)
            return f;

        if (dp[e][f] != -1)
            return dp[e][f];

        int min = Integer.MAX_VALUE;
        int start = 1, end = f;

        while (start <= end)
        {
            int mid = start + (end - start) / 2;

            int low, high;

            if (dp[e - 1][mid - 1] != -1)
                low = dp[e - 1][mid - 1];
            else
            {
                low = memo(e - 1, mid - 1, dp);
                dp[e - 1][mid - 1] = low;
            }

            if (dp[e][f - mid] != -1)
                high = dp[e][f - mid];
            else
            {
                high = memo(e, f - mid, dp);
                dp[e][f - mid] = high;
            }
            int temp = 1 + Math.max(low, high);

            if(low < high)
                start = mid + 1;
            else
                end = mid - 1;

            min = Math.min(min, temp);
        }
        return dp[e][f] = min;
    }

    public static void main(String[] args)
    {
        int  e = 1, f = 2;
        int[][] dp = new int[e+1][f+1];
        for(int[] arr: dp)
            Arrays.fill(arr, -1);

        System.out.println(memo(e, f, dp));
    }
}
