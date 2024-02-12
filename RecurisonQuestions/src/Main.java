import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main
{
    //12. Integer to Roman

    public static String intToRoman(int n)
    {
        StringBuilder str = new StringBuilder();
        if(n >= 1000)
        {
            for (int i = 0; i < n / 1000; i++)
                str.append("M");
            n %= 1000;
        }
        if(n >= 900)
        {
            str.append("CM");
            n -= 900;
        }
        if (n >= 500)
        {
            str.append("D");
            n -= 500;
        }
        if(n >= 400)
        {
            str.append("CD");
            n -= 400;
        }
        if (n >= 100)
        {
            for (int i = 0; i < n/100; i++)
            {
                str.append("C");
            }
            n %= 100;
        }
        if(n >= 90)
        {
            str.append("XC");
            n -= 90;
        }
        if(n >= 50)
        {
            str.append("L");
            /*for (int i = 0; i < (n-50)/10; i++)
            {
                str.append("X");
                n -= 10;
            }*/
            n -= 50;
        }
        if(n >= 40)
        {
            str.append("XL");
            n -= 40;
        }
        if(n >= 10)
        {
            for (int i = 0; i < n/10; i++)
            {
                str.append("X");
            }
            n %= 10;
        }
        if(n == 9)
        {
            str.append("IX");
            n -= 9;
        }
        else if( n >= 5)
        {
            str.append("V");
            for (int i = 0; i < n-5; i++)
            {
                str.append("I");
            }
        }
        else if (n == 4)
        {
            str.append("IV");
            n -= 4;
        }
        else
            for (int i = 0; i < n; i++)
            {
                str.append("I");
            }
        return str.toString();
    }

    public static double myPow(double x, int n)
    {
        return Math.pow(x, n);
    }



    public static void main(String[] args)
    {
        System.out.println(2^2^3^5^3);
    }
}
