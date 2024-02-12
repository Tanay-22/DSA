package com.tanay.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringQuestions
{
    public static String defangIPaddr(String address)
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < address.length(); i++)
        {
            if(address.charAt(i) == '.')
            {
                str.append("[.]");
                continue;
            }
            str.append(address.charAt(i));
        }
        return str.toString();
    }

    public static String interpret(String command)
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < command.length(); i++)
        {
            if (command.charAt(i) == '(')
            {
                if(command.charAt(i+1) == ')')
                {
                    str.append("o");
                    i++;
                    continue;
                }
                else
                {
                    str.append("al");
                    i += 3;
                }
            }
            else
                str.append("G");
        }
        return str.toString();
    }

    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue)
    {
        int count = 0;
        int category = -1;
        if(ruleKey.equals("type"))
            category = 0;
        else if (ruleKey.equals("color"))
            category = 1;
        else
            category = 2;
        for (List<String> list: items)
        {
            if(list.get(category).equals(ruleValue))
                count++;
        }
        return count;
    }

    public static String sortSentence(String s)
    {
        StringTokenizer st = new StringTokenizer(s, " ");
        StringBuilder result = new StringBuilder();
        List<String> list = new ArrayList<>();
        int count = st.countTokens();
        while (st.hasMoreTokens())
        {
            list.add(st.nextToken());
        }
        int j = 1;
        for (char i = '1'; j <= count; j++)
        {
            String n = list.get(j - 1);
            if(n.charAt(n.length() - 1) == i)
            {
                result.append(n.substring(0,n.length() - 1) + " ");
                i++;
                j = 0;
            }
        }
        return result.toString().trim();
    }

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2)
    {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        for (int i = 0; i < Math.max(word1.length, word2.length); i++)
        {
            if(i < word1.length)
                str1.append(word1[i]);
            if(i < word2.length)
                str2.append(word2[i]);
        }
        if(str1.toString().equals(str2.toString()))
            return true;
        else
            return false;
    }

    public String toLowerCase(String s)
    {
        return s.toLowerCase();
    }

    public static boolean halvesAreAlike(String s)
    {


        String part1 = s.substring(0,s.length()/2);
        String part2 = s.substring(s.length()/2);

        String vowels = "aeiouAEIOU";
        int count1 = 0, count2 = 0;

        for (int i = 0; i < Math.max(part1.length(), part2.length()); i++)
        {
            if(i < part1.length() && vowels.indexOf(part1.charAt(i)) > -1)
                count1++;
            if(i < part2.length() && vowels.indexOf(part2.charAt(i)) > -1)
                count2++;
        }
        return count1 == count2;
    }

    public static boolean canReach(String s, int minJump, int maxJump)
    {
        if(s.indexOf('1') == -1)
            return true;
        int i = s.indexOf('0');
        if(s.indexOf('0') == s.lastIndexOf('0'))
            return false;
        while(s.indexOf('0') >= 0)
        {
            s = s.substring(i + 1);
            int j = s.indexOf('0');
            if(j < 0)
                break;
            if(j + 1 > maxJump)
                return false;
            else
                i = j;
        }
        return true;
    }

    public static boolean checkPalindrome(String s)
    {
        for (int i = 0; i < s.length()/2; i++)
        {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }
        return true;
    }
    public static boolean checkPalindromeFormation(String a, String b)
    {
        if(checkPalindrome(a) || checkPalindrome(b) || checkPalindrome(a+b) || checkPalindrome(b+a))
            return true;
        int index1 = -1, index2 = -1;
        boolean f1= true, f2 = true;
        for (int i = 0; i <a.length(); i++)
        {
            if( !f1 && !f2)
                break;
            if(a.charAt(i) != b.charAt(b.length() - 1 - i) && f1)
            {
                index1 = i;
                f1 = false;
            }
            if(b.charAt(i) != a.charAt(b.length() - 1 - i) && f2)
            {
                index2 = i;
                f2 = false;
            }
        }
        String str1 = index1 > -1 ? a.substring(0,index1) + b.substring(index1) : "qw";
        String str2 = index2 > -1 ? b.substring(0,index2) + a.substring(index2) : "qw";
        String str3 = index2 > -1 ? a.substring(0,index2) + b.substring(index2) : "qw";
        String str4 = index1 > -1 ? b.substring(0,index1) + a.substring(index1) : "qw";
            return(checkPalindrome(str1) || checkPalindrome(str2) || checkPalindrome(str3) || checkPalindrome(str4));
    }

    public static void main(String[] args)
    {
        System.out.println(checkPalindromeFormation("aejbaalflrmkswrydwdkdwdyrwskmrlfqizjezd", "uvebspqckawkhbrtlqwblfwzfptanhiglaabjea"));
    }
}
