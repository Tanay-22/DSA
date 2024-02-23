import jdk.jshell.VarSnippet;

import java.util.Arrays;

public class Main
{
    public static boolean isPowerOfTwo(int n)
    {
        return ((n-1) & n) == 0;
    }


    public static int power(int a, int b)   // a^b
    {
        int ans = 1;
        while(b > 0)
        {
            if((b & 1) == 1)
                ans *= a;
            a *= a;
            b = b >> 1;
        }
        return ans;
    }

    //67. Add Binary --> Given two binary strings a and b, return their sum as a binary string.
    public static String addBinary(String a, String b)
    {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;

        boolean A = false, B = false, carry = false;
        while (i > -1 && j > -1)
        {
            A = a.charAt(i) == '1';
            B = b.charAt(j) == '1';
            if(A ^ B ^ carry)
                res.append(1);
            else
                res.append(0);
            carry = (A & B) || ((A ^ B) & carry);
            i--; j--;
        }
        while (i > -1)
        {
            A = a.charAt(i) == '1';
            if(A ^ carry)
                res.append(1);
            else
                res.append(0);
            carry = A & carry;
            i--;
        }
        while (j > -1)
        {
            B = b.charAt(j) == '1';
            if(B ^ carry)
                res.append(1);
            else
                res.append(0);
            carry = B & carry;
            j--;
        }
        if(carry)
            res.append("1");
        return res.reverse().toString();


    }

    public static int[][] flipAndInvertImage(int[][] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int l = arr[0].length;
            for (int j = 0; j < l/2; j++)
            {
                int temp = arr[i][j];
                arr[i][j] = arr[i][l - j - 1];
                arr[i][l - j - 1] = temp;

            }
        }
        for (int i = 0; i < arr.length; i++)
        {
            int l = arr[0].length;
            for (int j = 0; j < l; j++)
            {
                arr[i][j] ^= 1;
            }
        }
        return arr;
    }

    public static int reverseBits(int n)
    {
        int ans = 0;
        for (int i = 0; i < 32; i++)
        {
            ans <<= 1;
            ans |= (n & 1);
            n >>= 1;
        }
        return ans;
    }

    public static int hammingWeight(int n)
    {
        int count = 0;
        for (int i = 0; i < 32; i++)
        {
            if( (n & 1) == 1)
                count++;
            n >>= 1;
        }
        return count;
    }

    public static int[] countBits(int n)
    {
        int arr[] = new int[n + 1];
        for (int i = 0; i <= n; i++)
        {
            arr[i] = hammingWeight(i);
        }
        return arr;
    }

    public static int hammingDistance(int x, int y)
    {
        int count = 0;
        while (x > 0 || y > 0)
        {
            if (((x & 1) ^ (y & 1)) == 1)
                count++;
            x >>= 1;
            y >>= 1;
        }
        return count;
    }

    public static int findComplement(int num)
    {
        String str = Integer.toBinaryString(num);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == '0')
                res.append(1);
            else
                res.append(0);
        }
        return Integer.parseInt(res.toString(), 2);

    }

    public static String firstPalindrome(String[] words)
    {
        for (int i = 0; i < words.length; i++)
        {
            if(checkPalindrome(words[i]))
                return words[i];

        }
        return "";
    }

    private static boolean checkPalindrome(String word)
    {
        for (int i = 0; i < word.length()/2; i++)
        {
            if(word.charAt(i) != word.charAt(word.length() - i - 1))
                return false;
        }
        return true;
    }

    public static int rangeBitwiseAnd(int left, int right)
    {
        int and = right;
        for (int i = and - 1; i >= left; i = and - 1)
        {
            and &= i;
        }
        return and;
    }

    public static int singleNumber2(int arr[])
    {
        int ans = 0;
        for (int i = 0; i < 32; i++)
        {
            int count = 0;
            for (int j = 0; j < arr.length; j++)
            {
                int digit = (arr[j] >> i) & 1;
                if(digit == 1)
                    count++;

            }
            if (count > 0)
                ans |= (count % 3) << i;
        }
        return ans;
    }

    public static int[] rearrangeArray(int[] arr)
    {
        int positiveIndex = 0, negativeIndex = 1;
        int arr2[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            if(arr[i] > 0)
            {
                arr2[positiveIndex] = arr[i];
                positiveIndex += 2;
            }
            else
            {
                arr2[negativeIndex] = arr[i];
                negativeIndex += 2;
            }
        }
        return arr2;
    }

    public static int getSum(int a, int b)
    {
        int sum = 0, carry = 0;
        System.out.println(Integer.toBinaryString(a));
        int nBits = (int)Math.max(Math.log(a)/Math.log(2),Math.log(b)/Math.log(2)) + 1;
        for (int i = 0; i < 32; i++)
        {
            int digitA = a & 1, digitB = b & 1;
            sum = sum | (digitA ^ digitB ^ carry) << i;
            carry = (digitA & digitB) | (digitB & carry) | ((digitA) & carry);
            a >>= 1;
            b >>= 1;
        }
        return sum;
    }

    public static char findTheDifference(String s, String t)
    {
        String str= s + t;
        int ch = 0;
        for (int i = 0; i < str.length(); i++)
        {
            ch ^= str.charAt(i);
        }
        return (char)ch;
    }

    public static int integerReplacement(int n)
    {
        int count = 0;
        while(n != 1)
        {
            if(n % 2 == 0)
                n >>= 1;
            else
            {
                if((n - 1) % 2 == 0)
                    n -= 1;
                else if((n + 1) % 4 == 0)
                    n += 1;
                else
                    n -= 1;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args)
    {
        System.out.println(integerReplacement(8));
    }
}
