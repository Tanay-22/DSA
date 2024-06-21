package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class StringQues
{
//    14. Longest Common Prefix
    public static String longestCommonPrefix(String[] strs)
    {
        StringBuilder prefix = new StringBuilder();
        int minLen = strs[0].length();
        boolean f = true;
        for (String s : strs)
            minLen = Math.min(minLen, s.length());
        for (int i = 0; i < minLen; i++)
        {
            for (int j = 0; j < strs.length-1; j++)
            {
                if(!f || strs[j].charAt(i) != strs[j+1].charAt(i))
                    f = false;
            }
            if(f)
                prefix.append(strs[0].charAt(i));
        }
        return prefix.toString();
    }

    public static int myAtoi(String s)
    {
        s = s.trim();

        if(s.isEmpty())
            return 0;

        boolean isNegative = s.charAt(0) == '-';
        boolean isPositive = s.charAt(0) == '+';
        int i = (isNegative || isPositive ) ? 1 : 0;

        for (int j = i; j < s.length(); j++)
        {
            if(s.charAt(j) == '0')
            {
                if(j == s.length()-1)
                    return 0;
                continue;
            }
            else
            {
                i = j;
                break;
            }
        }

        StringBuilder result = new StringBuilder();

        for (; i < s.length(); i++)
        {
            char ch = s.charAt(i);

            if(ch >= '0' && ch <= '9')
                result.append(ch);
            else
                break;
        }

        if(result.isEmpty())
            return 0;

        if(isNegative)
        {
            result.insert(0, '-');
            if((result.length() > (""+Integer.MIN_VALUE).length()) ||
                    (result.length() == (""+Integer.MIN_VALUE).length() &&
                    result.toString().compareTo(""+Integer.MIN_VALUE) >= 0))
            {
                return Integer.MIN_VALUE;
            }
            else
                return Integer.parseInt(result.toString());
        }

        if((result.length() > (""+Integer.MAX_VALUE).length()) ||
            (result.length() == (""+Integer.MAX_VALUE).length() &&
                    result.toString().compareTo(""+Integer.MAX_VALUE) >= 0))
        {
            return Integer.MAX_VALUE;
        }
        else
            return Integer.parseInt(result.toString());
    }

    public static String shiftingLetters(String s, int[] shifts)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shifts.length; i++)
            shifts[i] %= 26;

        for (int i = 0; i < shifts.length; i++)
        {
            Long sum = 0l;
            for (int j = shifts.length-1; j >= i; j--)
                sum += shifts[j];
            sum = sum % 26;
            char ch = (char)(s.charAt(i) + sum);
            if(ch > 'z')
                ch = (char)(ch - 26);

            sb.append(ch);
        }
        return sb.toString();
    }

    public static int findMinDifference(List<String> timePoints)
    {
        int time[] = new int[timePoints.size()];
        for (int i = 0; i < time.length; i++)
        {
            StringTokenizer st = new StringTokenizer(timePoints.get(i), ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            time[i] = hour * 60 + min;
        }
        Arrays.sort(time);

        int min = Math.abs(time[0] - time[time.length-1]);
        if(min > 12 * 60)
            min = Math.abs(24 * 60 - min);
        for (int i = 0; i < time.length - 1; i++)
        {
            int diff = Math.abs(time[i] - time[i+1]);
            diff = diff > 12 * 60 ? Math.abs(24 * 60 - diff) : diff;

            min = Math.min(min, diff);
        }
        return min;
    }

    //1023. Camelcase Matching
    public static List<Boolean> camelMatch(String[] queries, String pattern)
    {
        List<Boolean> match = new ArrayList<>();
        int c1 = 0;
        for (int i = 0; i < pattern.length(); i++)
        {
            char ch = pattern.charAt(i);
            if(ch >= 'A' && ch <= 'Z')
                c1++;
        }
        for (int i = 0; i < queries.length; i++)
        {
            String s = queries[i];
            int c2 = 0;
            for (int j = 0; j < s.length(); j++)
            {
                char ch = s.charAt(j);
                if(ch >= 'A' && ch <= 'Z')
                    c2++;
            }
            if(c1 != c2)
            {
                match.add(false);
                continue;
            }
            int patternIndex = 0;
            for (int j = 0; j < s.length(); j++)
            {
                if(patternIndex == pattern.length())
                    break;

                if(pattern.charAt(patternIndex) >= 'A' && pattern.charAt(patternIndex) <= 'Z' &&
                        pattern.charAt(patternIndex) == s.charAt(j))
                {
                    patternIndex++;
                }
                else if(pattern.charAt(patternIndex) >= 'a' && pattern.charAt(patternIndex) <= 'z' &&
                        pattern.charAt(patternIndex) == s.charAt(j))
                {
                    patternIndex++;
                }
            }
            if(patternIndex >= pattern.length())
                match.add(true);
            else
                match.add(false);
        }
        return match;
    }

    //1750. Minimum Length of String After Deleting Similar Ends
    public static int minimumLength(String s)
    {
        int start = 0, end = s.length()-1;

        while (start < end)
        {
            if(s.charAt(start) == s.charAt(end))
            {
                if(s.charAt(start+1) == s.charAt(end) && start+1 != end)
                    start++;
                else if(s.charAt(start) == s.charAt(end-1) && start != end-1)
                    end--;
                else
                {
                    start++;
                    end--;
                }
            }
            else
                break;
        }
        return end -  start + 1;
    }

    //1324. Print Words Vertically
    public static List<String> printVertically(String s)
    {
         StringTokenizer st = new StringTokenizer(s, " ");
         List<String> words = new ArrayList<>();

         while (st.hasMoreTokens())
             words.add(st.nextToken());

         int maxLen = 0;
        for (String w: words)
        {
            if(w.length() > maxLen)
                maxLen = w.length();
        }
        int index = 0;
        List<String> newWords = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++)
        {
            if(index == maxLen)
                break;
            if(index < words.get(i).length())
                sb.append(words.get(i).charAt(index));
            else
                sb.append(" ");
            if(i == words.size()-1)
            {
                newWords.add(sb.toString().stripTrailing());
                i = -1;
                index++;
                sb = new StringBuilder();
            }
        }
        return newWords;
    }

    //1768. Merge Strings Alternately
    public static String mergeAlternately(String word1, String word2)
    {
        int i = -1;
        StringBuilder sb = new StringBuilder();
        while (++i < word1.length() && i < word2.length())
            sb.append("" + word1.charAt(i) + word2.charAt(i));

        if(i < word1.length())
            sb.append(word1.substring(i));

        if(i < word2.length())
            sb.append(word2.substring(i));

        return sb.toString();
    }

    //2000. Reverse Prefix of Word
    public static String reversePrefix(String s, char ch)
    {
        StringBuilder sb = new StringBuilder();
        int end = s.indexOf(ch);
        int e = end;

        while(end >= 0)
            sb.append("" + s.charAt(end--));

        sb.append(s.substring(e+1));

        return sb.toString();
    }

    public static String gcdOfStrings(String str1, String str2)
    {
        int len1 = str1.length(), len2 = str2.length();

        for (int i = 0; i < str2.length(); i++)
        {
            if(str1.indexOf(str2.charAt(i)) == -1)
                return "";
        }
        for (int i = 0; i < str1.length(); i++)
        {
            if(str2.indexOf(str1.charAt(i)) == -1)
                return "";
        }
        StringBuilder sb1 = new StringBuilder(str1 + " ");
        StringBuilder sb2 = new StringBuilder(str2 + " ");
        int hcf = hcf(len1, len2);

        int i = 0, j = 0;
        while (true)
        {
            if(!sb1.substring(i, i + hcf).equals(sb2.substring(j, j + hcf)))
                return "";
            i = i + hcf;
            j = j + hcf;
            if(i >= sb1.length() - hcf && j >= sb2.length() - hcf)
                break;
            if(i >= sb1.length() - hcf)
                i = 0;
            if(j >= sb2.length() - hcf)
                j = 0;
        }
        return sb2.substring(0, hcf);
    }
    private static int hcf(int a, int b)
    {
        if(b == 0)
            return a;
        if(a == 0)
            return b;

        return hcf(b, a % b);
    }

    public static int compress(char[] chars)
    {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < chars.length - 1; i++)
        {
            if(chars[i] == chars[i + 1])
                count++;
            else
            {
                sb.append("" + chars[i] + (count > 1 ? count : ""));
                count = 1;
            }
        }
        sb.append("" + chars[chars.length - 1] + count);
        chars = new char[sb.length()];
        for (int i = 0; i < sb.length(); i++)
            chars[i] = sb.charAt(i);
        return chars.length;
    }

    public static void main(String[] args)
    {
        char chars[] = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.println(compress(chars));
    }
}
