package math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MathQues
{
    //  7. Reverse Integer
    public static int reverse(int x)
    {

        boolean isNegative = x < 0;
        x = Math.abs(x);
        int rev = reverseHelper(x);
        int original = reverseHelper(rev);
        int diff = (""+x).length() - (""+original).length();
        if(original != x && original != x/Math.pow(10,diff))
            return 0;
        else
        {
            if(isNegative)
                return -rev;
            return rev;
        }
    }

    public static int reverseHelper(int x)
    {
        int rev = 0;
        while(x > 0)
        {
            rev = (rev * 10) + (x % 10);
            x /= 10;
        }
        return rev;
    }

    //  9. Palindrome Number
    public static boolean isPalindrome(int x)
    {
        int original = x;
        if(x < 0)
            return false;
        int rev = 0;
        while(x > 0)
        {
            rev = (rev * 10) + (x % 10);
            x /= 10;
        }
        return rev == original;
    }

    //  13. Roman to Integer
    public static int romanToInt(String s)
    {
        int num = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            switch (ch)
            {
                case 'I':
                    num += 1;
                    break;
                case 'V':
                    num += 5;
                    if(i > 0 && s.charAt(i-1) == 'I')
                        num -= 2;
                    break;
                case 'X':
                    num += 10;
                    if(i > 0 && s.charAt(i-1) == 'I')
                        num -= 2;
                    break;
                case 'L':
                    num += 50;
                    if(i > 0 && s.charAt(i-1) == 'X')
                        num -= 20;
                    break;
                case 'C':
                    num += 100;
                    if(i > 0 && s.charAt(i-1) == 'X')
                        num -= 20;
                    break;
                case 'D':
                    num += 500;
                    if(i > 0 && s.charAt(i-1) == 'C')
                        num -= 200;
                    break;
                case 'M':
                    num += 1000;
                    if(i > 0 && s.charAt(i-1) == 'C')
                        num -= 200;
                    break;
            }
        }
        return num;
    }

    // 204. Count Primes
    public static int countPrimes(int n)
    {
        boolean primes[] = new boolean[n];
        int count = 0;
        for (int i = 2; i*i < n; i++)
        {
            if(!primes[i])
            {
                for (int j = i*2; j < n; j+=i)     // marking all number which is divisible by prime number
                    primes[j] = true;
            }
        }
        for (int i = 2; i < n; i++)
        {
            if(!primes[i])
                count++;
        }
        return count;
    }

//    43. Multiply Strings
    public static String multiply(String num1, String num2)
    {
        int maxDigits = 0;
        int l1 = num1.length(), l2 = num2.length();
        String arr[] = new String[l1];
        int  carry = 0;
        for (int i = l1-1; i >= 0; i--)
        {
            StringBuilder product = new StringBuilder();
            carry = 0;
            int d1 = num1.charAt(i) - '0';
            for (int j = l2-1; j >= 0; j--)
            {
                int d2 = num2.charAt(j) - '0';
                product.append(""+(d1*d2+carry)%10);
                carry = (d1 * d2 + carry) / 10;
            }
            if(carry > 0)
                product.append(""+carry);
            for (int j = 0; j < l1-i-1; j++)
            {
                product.insert(0, "0");
            }
            arr[l1-i-1] = product.toString();
            maxDigits = product.toString().length() > maxDigits ? product.toString().length() : maxDigits;
        }
        StringBuilder ans = new StringBuilder();
        carry = 0;
        for (int i = 0; i < maxDigits; i++)
        {
            int sum = 0;
            for (int j = 0; j < arr.length; j++)
            {
                int ch = 0;
                if(i < arr[j].length())
                    ch = arr[j].charAt(i) - '0';
                sum += ch;
            }
            sum += carry;
            ans.append( ""+(sum%10));
            carry = sum/10;
        }
        if (carry > 0)
            ans.append( ""+carry);
        ans.reverse();
        if(ans.charAt(0) == '0')
            return "0";
        return ans.toString();
    }

//    202. Happy Number
    public static boolean isHappy(int n)
    {
        while (n > 9)
        {
            int sum = 0;
            while (n > 0)
            {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
        }
        return n == 1 || n == 7;
    }

//  168. Excel Sheet Column Title
    public static String convertToTitle(int columnNumber)
    {
        StringBuilder str = new StringBuilder();
        while (columnNumber > 0)
        {
            int rem = (columnNumber-1) % 26;
            char digit = (char) (rem + 'A');
            str.insert(0, digit);
            columnNumber = (columnNumber-1) / 26;
        }
        return str.toString();
    }

//    171. Excel Sheet Column Number
    public static int titleToNumber(String columnTitle)
    {
        int l = columnTitle.length();
        int ans = 0;
        while (--l >= 0)
        {
            ans += Math.pow(26,columnTitle.length() - l - 1) * (columnTitle.charAt(l)-'A'+1);
        }
        return ans;
    }

//    66. Plus One
    public static int[] plusOne(int[] digits)
    {
        int len = digits.length;
        int carry = ++digits[len-1] / 10;
        digits[len -1] %= 10;
        if(carry > 0)
        {
            for (int i = len-2; i >= 0; i--)
            {
                digits[i] += carry;
                carry = digits[i] / 10;
                digits[i] %= 10;
                if(carry == 0)
                    break;
            }
            if(carry != 0)
            {
                int arr[] = new int[len+1];
                arr[0] = carry;
                for (int i = 1; i < len+1; i++)
                {
                    arr[i] = digits[i-1];
                }
                return arr;
            }
        }
        return digits;
    }

    //1344. Angle Between Hands of a Clock
    public static double angleClock(int hour, int minutes)
    {
        if(hour == 12)
            hour = 0;
        double hourAngle = (hour + minutes / 60.0) * 30;
        double minuteAngle = minutes * 6;

        double result = Math.abs(hourAngle - minuteAngle);
        return Math.min(result, 360 - result);
    }



    public static void main(String[] args)
    {
        System.out.println((char) ('a' + 3 + 5 + 9));
    }
}
