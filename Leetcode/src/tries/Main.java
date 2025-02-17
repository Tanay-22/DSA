package tries;

import java.util.Arrays;
import java.util.List;

public class Main
{

    public String longestCommonPrefix(String[] strs)
    {
        Trie trie = new Trie();
        for(String s: strs)
            insert(s, trie);

        StringBuilder sb = new StringBuilder();
        Trie temp = trie;
        while (true)
        {
            boolean found = false;
            for (int i = 0; i < 26; i++)
            {
                if (temp.children[i] != null && temp.children[i].count == strs.length)
                {
                    sb.append((char) ('a' + i));
                    temp = temp.children[i];
                    found = true;
                    break;
                }
            }
            if (!found)
                break;
        }
        return sb.toString();
    }

    private void insert(String s, Trie trie)
    {
        Trie temp = trie;
        for(char ch: s.toCharArray())
        {
            temp.count++;
            if(temp.children[ch - 'a'] == null)
                temp.children[ch - 'a'] = new Trie();
            temp = temp.children[ch - 'a'];
        }
    }
    public static void main(String[] args)
    {
        Main main = new Main();
        String[] arr = {"ab", "a"};
        System.out.println(main.longestCommonPrefix(arr));
        
    }
}

class Trie
{
    Trie[] children;
    int count;

    public Trie()
    {
        children = new Trie[26];
        count = 0;
    }
}