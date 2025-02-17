package R800;

import java.util.Scanner;

public class JaggedSwaps
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextLong();
        while (t-- > 0)
        {
            long n = sc.nextLong();
            long x = sc.nextLong();
            boolean ans = x == 1;

            for (int i = 1; i < n; i++)
            {
                x = sc.nextLong();
            }
            System.out.println(ans ? "YES" : "NO");
        }
    }
}
