/*
package tries;

class WordDictionary
{
    boolean isEnd;
    WordDictionary[] children;

    public WordDictionary()
    {
        this.isEnd = false;
        this.children = new WordDictionary[26];
    }

    public void addWord(String word)
    {
        WordDictionary current = this;
        for (int i = 0; i < word.length(); i++)
        {
            char ch = word.charAt(i);
            if(current.children[ch - 'a'] == null)
                current.children[ch - 'a'] = new WordDictionary();

            current = current.children[ch - 'a'];
        }
        current.isEnd = true;
    }

    public boolean search(String word)
    {
        WordDictionary current = this;
        for (int i = 0; i < word.length(); i++)
        {
            char ch = word.charAt(i);
            if(ch == '.')
                
        }
    }
}*/
