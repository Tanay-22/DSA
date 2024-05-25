package easy;

public class Easy
{
    static void nTo1(int n)
    {
        if(n == 0)
            return;
        nTo1(n-1);
        System.out.println(n);
    }

    static int factorial(int n)
    {
        if(n == 0)
            return 1;
        else
            return n * factorial(n-1);
    }

    static int sumOfDigits(int n)
    {
        if(n == 0)
            return 0;
        else
            return (n % 10) + sumOfDigits(n / 10);
    }

    static int productOfDigits(int n)
    {
        if(n == 0)
            return 1;
        else
            return (n % 10) * productOfDigits(n / 10);
    }

    static int rev = 0;
    static void reverse(int n)
    {
        if (n == 0)
            return;
        else
        {
            rev = rev * 10 + n % 10;
            reverse(n/10);
        }
    }

    static int zero = 0;
    static void countZero(int n)
    {
        if(n == 0)
            return;
        else
        {
            zero += n % 10 == 0 ? 1 : 0;
            countZero(n/10);
        }
    }

    static String removeA(String s, int i)
     {
        if(i >= s.length())
            return "";

        if(s.charAt(i) != 'a')
            return s.charAt(i) + removeA(s, i+1);
        else
            return removeA(s, i+1);
    }

    static String removeString(String s, String r)
    {
        if(s.isEmpty())
            return "";

        if(s.startsWith(r))
            return removeString(s.substring(r.length()), r);
        else
            return s.charAt(0) + removeString(s.substring(1), r);
    }

    public static void main(String[] args)
    {
        System.out.println(removeString("reappletree",  "apple"));
    }
}
