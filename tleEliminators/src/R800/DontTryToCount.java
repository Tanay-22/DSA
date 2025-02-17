//package R800;

import java.util.Scanner;

public class DontTryToCount
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            String x = sc.next();
            String s = sc.next();
            int count = -1;
            for (int i = 0; i <= 6; i++)
            {
                if(x.contains(s))
                {
                    count = i;
                    break;
                }
                x += x;
            }
            System.out.println(count);
        }
    }
}
