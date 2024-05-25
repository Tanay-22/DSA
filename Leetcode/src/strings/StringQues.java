package strings;

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

    // 224 BASIC CALCULATOR
   /* public int calculate(String s)
    {

    }

    public static String calculateHelper(String p, String up)
    {
        if(up.isEmpty())

    }*/

    public static void main(String[] args)
    {
        String s = "    0000000000000   ";
        System.out.println(myAtoi(s));
    }
}
