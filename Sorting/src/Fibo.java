import java.math.BigDecimal;

public class Fibo
{
    static long fiboNum(int n)
    {
        /*  f(n) = f(n-1) + f(n-2)
        *   x^n = x^(n-1) + x^(n-2)
        *   x^2 = x + 1
        *   find x1,x2
        *   f(n) = c1(x1^n) + c2(x2^n)  --> eq1
        *   find c1,c2 with initial values of n
        *   put the values of c1 and c2 in eq1 and form the formula
        * */
        return (long)((Math.pow((Math.sqrt(5) + 1)/2, n) - Math.pow((Math.sqrt(5) - 1)/2, n)) / Math.sqrt(5));
    }

    static long findNum(int n)
    {
        // f(n) = 2f(n-1) + f(n-2)
        // f(0) = 0 , f(1) = 1
        return (long) n;
    }

    public static boolean isPowerOfTwo(int n)
    {
        if(n < 1)
            return false;
        if(n > 1)
        {
            if(n % 2 == 0)
                return isPowerOfTwo(n/2);
            else
                return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        System.out.println(isPowerOfTwo(536870914));
    }
}
