/*
package tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SearchEngine
{
    private Trie head;

    public List<List<String>> suggestedProducts(String[] products, String searchWord)
    {
        List<List<String>> result = new ArrayList<>();
        populate(products);
        Trie current = head;
        StringBuilder prefix = new StringBuilder();

        for (char ch : searchWord.toCharArray())
        {
            prefix.append(ch);
            if (current == null || current.next[ch - 'a'] == null)
            {
                while (result.size() < searchWord.length())
                    result.add(Collections.emptyList());

                break;
            }
            current = current.next[ch - 'a'];
            result.add(current.searchPrefix(prefix.toString()));
        }
        return result;
    }

    private void populate(String[] products)
    {
        head = new Trie();
        Arrays.sort(products);
        for (String product : products)
        {
            head.addWord(product);
        }
    }
}


class Trie
{
    Trie[] next;
    boolean isEnd;

    public Trie()
    {
        next = new Trie[26];
        isEnd = false;
    }

    public void addWord(String s)
    {
        Trie current = this;
        for (int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if (current.next[ch - 'a'] == null)
                current.next[ch - 'a'] = new Trie();
            current = current.next[ch - 'a'];
        }
        current.isEnd = true;
    }

    private void collectWords(Trie node, String prefix, List<String> result)
    {
        if (result.size() == 3)
            return;

        if (node.isEnd)
            result.add(prefix);

        for (char ch = 'a'; ch <= 'z'; ch++)
        {
            if (node.next[ch - 'a'] != null)
            {
                collectWords(node.next[ch - 'a'], prefix + ch, result);
                if (result.size() == 3)
                    return;
            }
        }
    }

    public List<String> searchPrefix(String prefix)
    {
        Trie current = this;
        for (char ch : prefix.toCharArray())
        {
            if (current.next[ch - 'a'] == null)
                return Collections.emptyList();
            current = current.next[ch - 'a'];
        }
        List<String> result = new ArrayList<>();
        collectWords(current, prefix, result);
        return result;
    }
}*/
