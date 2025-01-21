package strings;

public class TextEditor
{
    private final StringBuilder sb;
    private Integer cursor;

    public TextEditor() 
    {
        this.sb = new StringBuilder();
        this.cursor = 0;
    }
    
    public void addText(String text) 
    {
        sb.insert(cursor, text);
        cursor += text.length();
    }
    
    public int deleteText(int k) 
    {
        int min = Math.min(cursor, k);
        sb.delete(cursor - min, cursor);
        cursor -= min;

        return min;
    }
    
    public String cursorLeft(int k) 
    {
        cursor = Math.max(cursor - k, 0);

        return getLast10Chars();
    }
    
    public String cursorRight(int k) 
    {
        cursor = Math.min(cursor + k, sb.length());

        return getLast10Chars();
    }

    private String getLast10Chars()
    {
        int start = Math.max(0, cursor - 10);

        return sb.substring(start, cursor);
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */