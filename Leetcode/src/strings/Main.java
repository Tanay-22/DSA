package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public int countPalindromicSubsequence(String s)
    {
        int res = 0;
        for(int i = 0; i < s.length(); i++)
        {
            res += count(s, i, "");
        }
        return res;
    }

    private int count(String s, int i, String ss)
    {
        if(i >= s.length() || ss.length() > 3)
            return 0;

        if(ss.length() == 3)
        {
            if(palindrome(ss))
                return 1;
            else
                return 0;
        }

        int res = 0;
        for(int j = i+1; j < s.length(); j++)
            res += count(s, j, ss + s.charAt(i));

        return res;
    }
    private boolean palindrome(String s)
    {
        if(s.charAt(0) == s.charAt(2))
            return true;

        return false;
    }

    public List<List<String>> findDuplicate(String[] paths)
    {
        Map<String, List<String>> map = new HashMap<>();
        for(String path: paths)
        {
            String[] arr = path.split(" ");
            for(int i = 1; i < arr.length; i++)
            {
                String content = arr[i].substring(arr[i].indexOf('(') + 1, arr[i].indexOf(')'));
                map.putIfAbsent(content, new ArrayList<>());
                map.get(content).add(arr[0] + "/" + arr[i].substring(0, arr[i].indexOf('(')));
            }
        }
        List<List<String>> list = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: map.entrySet())
        {
            if(entry.getValue().size() != 1)
                list.add(entry.getValue());
        }
        return list;
    }

    public static void main(String[] args)
    {
//        TextEditor textEditor = new TextEditor(); // The current text is "|". (The '|' character represents the cursor)
//        textEditor.addText("leetcode"); // The current text is "leetcode|".
//        System.out.println(textEditor.deleteText(4)); // return 4
//        // The current text is "leet|".
//        // 4 characters were deleted.
//        textEditor.addText("practice"); // The current text is "leetpractice|".
//        System.out.println(textEditor.cursorRight(3)); // return "etpractice"
//        // The current text is "leetpractice|".
//        // The cursor cannot be moved beyond the actual text and thus did not move.
//        // "etpractice" is the last 10 characters to the left of the cursor.
//        System.out.println(textEditor.cursorLeft(8)); // return "leet"
//        // The current text is "leet|practice".
//        // "leet" is the last min(10, 4) = 4 characters to the left of the cursor.
//        System.out.println(textEditor.deleteText(10)); // return 4
//        // The current text is "|practice".
//        // Only 4 characters were deleted.
//        System.out.println(textEditor.cursorLeft(2)); // return ""
//        // The current text is "|practice".
//        // The cursor cannot be moved beyond the actual text and thus did not move.
//        // "" is the last min(10, 0) = 0 characters to the left of the cursor.
//        System.out.println(textEditor.cursorRight(6)); // return "practi"
//        // The current text is "practi|ce".
//        // "practi" is the last min(10, 6) = 6 characters to the left of the cursor.
        Main main = new Main();
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"};
        System.out.println(main.findDuplicate(paths));
    }
}
