package strings;

import java.util.*;

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
            for (int j = 0; j < strs.length - 1; j++)
            {
                if (!f || strs[j].charAt(i) != strs[j + 1].charAt(i))
                    f = false;
            }
            if (f)
                prefix.append(strs[0].charAt(i));
        }
        return prefix.toString();
    }

    public static int myAtoi(String s)
    {
        s = s.trim();

        if (s.isEmpty())
            return 0;

        boolean isNegative = s.charAt(0) == '-';
        boolean isPositive = s.charAt(0) == '+';
        int i = (isNegative || isPositive) ? 1 : 0;

        for (int j = i; j < s.length(); j++)
        {
            if (s.charAt(j) == '0')
            {
                if (j == s.length() - 1)
                    return 0;
                continue;
            } else
            {
                i = j;
                break;
            }
        }

        StringBuilder result = new StringBuilder();

        for (; i < s.length(); i++)
        {
            char ch = s.charAt(i);

            if (ch >= '0' && ch <= '9')
                result.append(ch);
            else
                break;
        }

        if (result.isEmpty())
            return 0;

        if (isNegative)
        {
            result.insert(0, '-');
            if ((result.length() > ("" + Integer.MIN_VALUE).length()) ||
                    (result.length() == ("" + Integer.MIN_VALUE).length() &&
                            result.toString().compareTo("" + Integer.MIN_VALUE) >= 0))
            {
                return Integer.MIN_VALUE;
            } else
                return Integer.parseInt(result.toString());
        }

        if ((result.length() > ("" + Integer.MAX_VALUE).length()) ||
                (result.length() == ("" + Integer.MAX_VALUE).length() &&
                        result.toString().compareTo("" + Integer.MAX_VALUE) >= 0))
        {
            return Integer.MAX_VALUE;
        } else
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
            for (int j = shifts.length - 1; j >= i; j--)
                sum += shifts[j];
            sum = sum % 26;
            char ch = (char) (s.charAt(i) + sum);
            if (ch > 'z')
                ch = (char) (ch - 26);

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

        int min = Math.abs(time[0] - time[time.length - 1]);
        if (min > 12 * 60)
            min = Math.abs(24 * 60 - min);
        for (int i = 0; i < time.length - 1; i++)
        {
            int diff = Math.abs(time[i] - time[i + 1]);
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
            if (ch >= 'A' && ch <= 'Z')
                c1++;
        }
        for (int i = 0; i < queries.length; i++)
        {
            String s = queries[i];
            int c2 = 0;
            for (int j = 0; j < s.length(); j++)
            {
                char ch = s.charAt(j);
                if (ch >= 'A' && ch <= 'Z')
                    c2++;
            }
            if (c1 != c2)
            {
                match.add(false);
                continue;
            }
            int patternIndex = 0;
            for (int j = 0; j < s.length(); j++)
            {
                if (patternIndex == pattern.length())
                    break;

                if (pattern.charAt(patternIndex) >= 'A' && pattern.charAt(patternIndex) <= 'Z' &&
                        pattern.charAt(patternIndex) == s.charAt(j))
                {
                    patternIndex++;
                } else if (pattern.charAt(patternIndex) >= 'a' && pattern.charAt(patternIndex) <= 'z' &&
                        pattern.charAt(patternIndex) == s.charAt(j))
                {
                    patternIndex++;
                }
            }
            if (patternIndex >= pattern.length())
                match.add(true);
            else
                match.add(false);
        }
        return match;
    }

    //1750. Minimum Length of String After Deleting Similar Ends
    public static int minimumLength(String s)
    {
        int start = 0, end = s.length() - 1;

        while (start < end)
        {
            if (s.charAt(start) == s.charAt(end))
            {
                if (s.charAt(start + 1) == s.charAt(end) && start + 1 != end)
                    start++;
                else if (s.charAt(start) == s.charAt(end - 1) && start != end - 1)
                    end--;
                else
                {
                    start++;
                    end--;
                }
            } else
                break;
        }
        return end - start + 1;
    }

    //1324. Print Words Vertically
    public static List<String> printVertically(String s)
    {
        StringTokenizer st = new StringTokenizer(s, " ");
        List<String> words = new ArrayList<>();

        while (st.hasMoreTokens())
            words.add(st.nextToken());

        int maxLen = 0;
        for (String w : words)
        {
            if (w.length() > maxLen)
                maxLen = w.length();
        }
        int index = 0;
        List<String> newWords = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++)
        {
            if (index == maxLen)
                break;
            if (index < words.get(i).length())
                sb.append(words.get(i).charAt(index));
            else
                sb.append(" ");
            if (i == words.size() - 1)
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

        if (i < word1.length())
            sb.append(word1.substring(i));

        if (i < word2.length())
            sb.append(word2.substring(i));

        return sb.toString();
    }

    //2000. Reverse Prefix of Word
    public static String reversePrefix(String s, char ch)
    {
        StringBuilder sb = new StringBuilder();
        int end = s.indexOf(ch);
        int e = end;

        while (end >= 0)
            sb.append("" + s.charAt(end--));

        sb.append(s.substring(e + 1));

        return sb.toString();
    }

    public static String gcdOfStrings(String str1, String str2)
    {
        int len1 = str1.length(), len2 = str2.length();

        for (int i = 0; i < str2.length(); i++)
        {
            if (str1.indexOf(str2.charAt(i)) == -1)
                return "";
        }
        for (int i = 0; i < str1.length(); i++)
        {
            if (str2.indexOf(str1.charAt(i)) == -1)
                return "";
        }
        StringBuilder sb1 = new StringBuilder(str1 + " ");
        StringBuilder sb2 = new StringBuilder(str2 + " ");
        int hcf = hcf(len1, len2);

        int i = 0, j = 0;
        while (true)
        {
            if (!sb1.substring(i, i + hcf).equals(sb2.substring(j, j + hcf)))
                return "";
            i = i + hcf;
            j = j + hcf;
            if (i >= sb1.length() - hcf && j >= sb2.length() - hcf)
                break;
            if (i >= sb1.length() - hcf)
                i = 0;
            if (j >= sb2.length() - hcf)
                j = 0;
        }
        return sb2.substring(0, hcf);
    }

    private static int hcf(int a, int b)
    {
        if (b == 0)
            return a;
        if (a == 0)
            return b;

        return hcf(b, a % b);
    }

    public static int compress(char[] chars)
    {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < chars.length - 1; i++)
        {
            if (chars[i] == chars[i + 1])
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

    //71. Simplify Path
    public static String simplifyPath(String path)
    {
        StringTokenizer st = new StringTokenizer(path, "/");
        Stack<String> stack = new Stack<>();

        while (st.hasMoreTokens())
        {
            String s = st.nextToken();
            if (s.equals("."))
                continue;
            if (s.equals(".."))
            {
                if (stack.size() > 0)
                    stack.pop();
            } else
                stack.push(s);
        }
        if (stack.isEmpty())
            return "/";

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
        {
            sb.insert(0, "/" + stack.pop());
        }
        return sb.toString();
    }

    //151. Reverse Words in a String
    public static String reverseWords(String s)
    {
        StringBuilder sb = new StringBuilder();
        StringBuilder word = new StringBuilder();

        s = " " + s;
        for (int i = s.length() - 1; i >= 0; i--)
        {
            if (s.charAt(i) == ' ')
            {
                sb.append(word + " ");
                word = new StringBuilder();
                while (i >= 0 && s.charAt(i) == ' ')
                    i--;
                i++;
            } else
                word.insert(0, s.charAt(i));
        }
        return sb.toString().trim();
    }

    //49. Group Anagrams
    public static List<List<String>> groupAnagrams(String[] strs)
    {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();

        for (String s : strs)
        {
            char charArr[] = s.toCharArray();
            Arrays.sort(charArr);

            String sortedStr = new String(charArr);
            List<String> list = hashMap.get(sortedStr);

            if (list == null)
                hashMap.put(sortedStr, new ArrayList<>(Arrays.asList(s)));
            else
                list.add(s);
        }
        for (Map.Entry<String, List<String>> set : hashMap.entrySet())
            ans.add(set.getValue());

        return ans;
    }

    //273. Integer to English Words
    public static String numberToWords(int num)
    {
        if(num == 0)
            return "Zero";

        String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
                "Nineteen"};
        String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        StringBuilder sb = new StringBuilder();
        if(num >= 1e9)
        {
            int billion = num / (int)1e9;
            numberToWordsHelper(sb, belowTwenty, tens, billion);
            sb.append("Billion ");
            num %= (int)1e9;
        }
        if (num >= 1e6)
        {
            int million = num / (int)1e6;
            numberToWordsHelper(sb, belowTwenty, tens, million);
            sb.append("Million ");
            num %= (int)1e6;
        }
        if(num >= 1e3)
        {
            int thousand = num / (int)1e3;
            numberToWordsHelper(sb, belowTwenty, tens, thousand);
            sb.append("Thousand ");
            num %= (int)1e3;
        }
        numberToWordsHelper(sb, belowTwenty, tens, num);

        return sb.toString().trim();
    }
    private static void numberToWordsHelper(StringBuilder sb, String[] belowTwenty, String[] tens, int num)
    {
        if(num >= 100)
        {
            sb.append(belowTwenty[num/100] + " Hundred ");
            num %= 100;
        }
        if(num >= 20)
        {
            sb.append(tens[num/10] + " ");
            num %= 10;
        }
        if(num > 0)
            sb.append(belowTwenty[num] + " ");
    }

    //  125. Valid Palindrome
    public static boolean isPalindrome(String s)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if(ch >= 'A' && ch <= 'Z')
                sb.append((char)(ch + 32));
            else if((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9'))
                sb.append(ch);
        }
        for(int i = 0; i < sb.length(); i++)
        {
            if(sb.charAt(i) != sb.charAt(sb.length()-i-1))
                return false;
        }
        return true;
    }


    //  392. Is Subsequence
    public static boolean isSubsequence(String s, String t)
    {
        int i = 0;
        for (int j = 0; j < t.length(); j++)
        {
            if(i == s.length())
                return true;

            if(s.charAt(i) == t.charAt(j))
                i++;
        }
        return i == s.length();
    }

    //  383. Ransom Note
    public static boolean canConstruct(String ransomNote, String magazine)
    {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < magazine.length(); i++)
        {
            char ch = magazine.charAt(i);
            hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < ransomNote.length(); i++)
        {
            char ch = ransomNote.charAt(i);
            if(!hashMap.containsKey(ch) || hashMap.get(ch) == 0)
                return false;
            else
                hashMap.put(ch, hashMap.get(ch)-1);
        }
        return true;
    }

    //  205. Isomorphic Strings
    public static boolean isIsomorphic(String s, String t)
    {
        HashMap<Character, Character> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++)
        {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            
            if(!hashMap.containsKey(ch1) && !hashMap.containsValue(ch2))
                hashMap.put(ch1, ch2);
            else
                return false;
        }
        return true;
    }


    public static String kthDistinct(String[] arr, int k)
    {
        List<String> list = new ArrayList<>();
        Set<String> duplicates = new HashSet<>();
        for (String s: arr)
        {
            if(list.contains(s))
                duplicates.add(s);
            else
                list.add(s);
        }
        for (String s: duplicates)
            list.remove(s);

        return k > list.size() ? "" : list.toArray()[k-1].toString();
    }

    public static List<String> removeSubfolders(String[] folder)
    {
        Arrays.sort(folder);

        // return Arrays.asList(folder);
        Set<String> set = new HashSet<>();

        for(String s: folder)
        {
            String[] path = s.split("/");
            String p = "";
            boolean f = true;
            for(int i = 0; i < path.length; i++)
            {
                if(path[i].isEmpty())
                    continue;

                p += "/" + path[i];
                if(set.contains(p))
                {
                    f = false;
                    break;
                }
            }
            if(f)
                set.add(s);
        }
        return new ArrayList<>(set);
    }

    //  3016. Minimum Number of Pushes to Type Word II
    public static int minimumPushes(String word)
    {
        HashMap<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < word.length(); i++)
        {
            char ch = word.charAt(i);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(freq.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>()
        {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        int ans = 0, j = 0;
        for(Map.Entry<Character, Integer> i : list)
        {
            int multiplier = j / 8 + 1;
            ans += (multiplier * i.getValue());
            j++;
        }
//        System.out.println(list);

        return ans;
    }


    public static boolean canMakeSubsequence(String str1, String str2)
    {
        int len1 = str1.length();
        int len2 = str2.length();

        if(len1 < len2)
            return false;

        return helper(str1, str2, 0, 0, 1);
    }

    private static boolean helper(String s1, String s2, int i, int j, int operationsLeft)
    {
        if(j == s2.length())
            return true;

        if(i == s1.length())
            return false;

        if(operationsLeft == 0 && s1.charAt(i) != s2.charAt(j))
            return false;

        if(operationsLeft > 0 &&
                (s1.charAt(i) == s2.charAt(j) ||
                        Math.abs(s1.charAt(i) - s2.charAt(j)) == 1 ||
                        (s1.charAt(i) == 'z' && s2.charAt(j) == 'a')))
            return helper(s1, s2, i + 1, j + 1,
                    s1.charAt(i) == s2.charAt(j) ? operationsLeft : 0);

        else
            return helper(s1, s2, i + 1, j, operationsLeft);
    }

    public static void main(String[] args)
    {
        System.out.println(canMakeSubsequence("zc", "ad"));
    }
}
