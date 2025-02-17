package R800;

import java.util.Scanner;

public class CoverInWater
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0)
        {
            int n = sc.nextInt();
            String s = sc.next();
            int ans = 0;
            for (int i = 0; i < n; i++)
            {
                if(s.charAt(i) == '.')
                {
                    int count = 0;
                    while(i < n && s.charAt(i) == '.')
                    {
                        count++;
                        i++;
                    }
                    if(count > 2)
                    {
                        ans = 2;
                        break;
                    }
                    else
                    {
                        ans += count;
                    }
                    i--;
                }
            }
            System.out.println(ans);
        }
    }
}
