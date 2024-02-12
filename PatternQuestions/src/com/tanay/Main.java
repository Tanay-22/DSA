package com.tanay;

public class Main
{
    static void pattern1()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    
    static void pattern2()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j <= i; j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    
    static void pattern3()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 5; j > i ; j--)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void pattern4()
    {
        for (int i = 1; i <= 5; i++)
        {
            for (int j = 1; j <=i ; j++)
            {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    static void pattern5()
    {
        for (int i = 1; i < 10; i++)
        {
            int k = i <= 5 ? i : 10 - i;
            for (int j = 1; j <= k; j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void pattern6()
    {
        for (int i = 1; i <= 5; i++)
        {
            for (int j = 5; j > i ; j--)
                System.out.print(" ");
            for (int j = 1; j <= i; j++)
                System.out.print("*");
            System.out.println();
        }
    }

    static void pattern7()
    {
        for (int i = 5; i >= 1; i--)
        {
            for (int j = 5; j > i ; j--)
                System.out.print(" ");
            for (int j = 1; j <= i; j++)
                System.out.print("*");
            System.out.println();
        }
    }

    static void pattern8()
    {
        for (int i = 1; i <= 5; i++)
        {
            for (int j = 5; j > i ; j--)
                System.out.print(" ");
            for (int j = 1; j <= 2*i-1; j++)
                System.out.print("*");
            System.out.println();
        }
    }

    static void pattern9()
    {
        for (int i = 5; i >= 1; i--)
        {
            for (int j = 5; j > i ; j--)
                System.out.print(" ");
            for (int j = 1; j <= 2*i-1; j++)
                System.out.print("*");
            System.out.println();
        }
    }

    static void pattern10()
    {
        for (int i = 1; i <= 5; i++)
        {
            for (int j = 5; j > i ; j--)
                System.out.print(" ");
            for (int j = 1; j <= i; j++)
                System.out.print("* ");
            System.out.println();
        }
    }

    static void pattern11()
    {
        for (int i = 5; i >= 1; i--)
        {
            for (int j = 5; j > i ; j--)
                System.out.print(" ");
            for (int j = 1; j <= i; j++)
                System.out.print("* ");
            System.out.println();
        }
    }

    static void pattern12()
    {
        pattern11();
        pattern10();
    }

    static void pattern13()
    {
        System.out.println("    *");
        for (int i = 1; i < 4; i++)
        {
            for (int j = 1; j <= 4-i; j++)
                System.out.print(" ");
            System.out.print("*");
            for (int j = 1; j <= 2*i-1; j++)
                System.out.print(" ");
            System.out.print("*");
            System.out.println();
        }
        for (int j = 1; j < 10; j++)
            System.out.print("*");
    }

    static void pattern14()
    {
        for (int j = 1; j < 10; j++)
            System.out.print("*");
        System.out.println();
        for (int i = 1; i < 4; i++)
        {
            for (int j = 1; j <= i ; j++)
                System.out.print(" ");
            System.out.print("*");
            for (int j = 5; j >= 2*i-1 ; j--)
                System.out.print(" ");
            System.out.println("*");

        }
        System.out.println("    *");
    }

    static void pattern15()
    {
        System.out.println("    *");
        for (int i = 1; i < 4; i++)
        {
            for (int j = 1; j <= 4-i; j++)
                System.out.print(" ");
            System.out.print("*");
            for (int j = 1; j <= 2*i-1; j++)
                System.out.print(" ");
            System.out.print("*");
            System.out.println();
        }
        for (int i = 1; i < 4; i++)
        {
            for (int j = 1; j <= i ; j++)
                System.out.print(" ");
            System.out.print("*");
            for (int j = 5; j >= 2*i-1 ; j--)
                System.out.print(" ");
            System.out.println("*");

        }
        System.out.println("    *");
    }

    static void pattern16()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 5; j > i ; j--)
                System.out.print(" ");
            String num = "" + (int)Math.pow(11, i);
            for (int j = 0; j < num.length(); j++)
                System.out.print(num.charAt(j) + " ");
            System.out.println();
        }
    }

    static void pattern17(int n)
    {
        for (int i = 1; i <= 2*n-1; i++)
        {
            int k = i > n ? 2*n-i : i;
            for (int j = k; j < n; j++)
                System.out.print(" ");
            for (int j = k; j > 1 ; j--)
                System.out.print(j);
            for (int j = 1; j <= k ; j++)
                System.out.print(j);
            System.out.println();
        }
    }

    static void pattern18()
    {
        for (int i = 1; i <= 10; i++)
        {
            int k = i <= 5 ? 2 * (i - 1) : 2 * (10 - i);
            for (int j = 1; j <= 10 - k; j+=2)
                System.out.print("*");
            for (int j = 1; j <= k; j++)
                System.out.print(" ");
            for (int j = 1; j <= 10 - k; j+=2)
                System.out.print("*");
            System.out.println();
        }
    }

    static void pattern19()
    {
        for (int i = 1; i < 10; i++)
        {
            int k = i <= 5 ? 10 - 2 * i : 10 - 2 * (10 - i);
            for (int j = 1; j <= 10 - k; j+=2)
                System.out.print("*");
            for (int j = 1; j <= k; j++)
                System.out.print(" ");
            for (int j = 1; j <= 10 - k; j+=2)
                System.out.print("*");
            System.out.println();
        }
    }

    static void pattern20()
    {
        for (int i = 1; i <= 5; i++)
        {
            if (i == 1 || i == 5)
            {
                for (int j = 1; j <= 4; j++)
                    System.out.print("*");
                System.out.println();
            }
            else
            {
                System.out.println("*  *");
            }
        }
    }

    static void pattern21()
    {
        int k = 1;
        for (int i = 1; i <= 5; i++)
        {
            for (int j = 1; j <= i; j++)
            {
                System.out.print(k++ + " ");
                if(k <= 10)
                    System.out.print(" ");
            }
            System.out.println();
        }

    }

    static void pattern22()
    {
        for (int i = 1; i <= 5; i++)
        {
            for (int j = 1; j <= i; j++)
                System.out.print((i + j + 1) % 2 + " ");
            System.out.println();
        }
    }
    public static int fib(int n)
    {
        if(n == 0 )
            return 0;
        if(n > 2)
            return fib(n-1) + fib(n-2);
        else
            return 1;
    }
    public static void main(String[] args)
    {
        System.out.println(fib(8));;
    }
}
