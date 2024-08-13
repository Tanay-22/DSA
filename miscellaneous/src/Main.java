public class Main
{
    public static void main(String[] args) throws Exception
    {
        String str = "https://leetcode.com/problems/design-tinyurl";
        HuffmanCoding huffmanCoding = new HuffmanCoding();

        String str2 = huffmanCoding.encode(str);
        System.out.println(str2);
        System.out.println(huffmanCoding.decode(str2));
    }
}
