import java.util.Stack;

public class MathForDSA
{
    public static void allPrime(int n)
    {
        boolean primes[] = new boolean[n+1];
        for (int i = 2; i*i <= n; i++)
        {
            if(!primes[i])
            {
                for (int j = i*2; j <= n; j+=i)     // marking all number which is divisible by prime number
                    primes[j] = true;
            }
        }
        for (int i = 2; i <= n; i++)
        {
            if(!primes[i])
                System.out.print(i + " ");
        }
    }

    public static double findSqrt(int n,  int decimalPlace)
    {
        int start = 0, end = n / 2;
        double result = 0.0;
        while (start < end)
        {
            int mid = start + (end - start) / 2;
            if (mid * mid == n)
            {
                start = mid;
                break;
            } else if (mid * mid > (int) n)
                end = mid - 1;
            else
                start = mid + 1;
        }
        result = start;
        double precision = 0.1;
        for (int i = 0; decimalPlace > 0; i++)
        {
            double dec = i * precision;
            if ((result + dec) * (result + dec) > n)
            {
                result += (i - 1) * precision;
                precision /= 10;
                decimalPlace--;
                 i = -1;
            }
        }
        return result;
    }

    public static double netwonRaphsonSqrt(int n)
    {
        double root = n/3, x = n;
        int i = 10;
        while(true)
        {
            root = 0.5 * (x + (n/x));
            if(Math.abs(root - x) < 1)
                break;
            x = root;
        }
        return root;
    }

    public static void findFactors(int n)
    {
        Stack<Integer> list = new Stack<Integer>();
        for (int i = 1; i < Math.sqrt(n); i++)
        {
            if(n % i == 0)
            {
                System.out.print(i + " ");
                if( n/i != i)
                    list.push(n/i);
            }
        }
        while (!list.empty())
        {
            System.out.print(list.pop() + " ");
        }
    }

    public static void main(String[] args)
    {
        findFactors(56);
    }
}
