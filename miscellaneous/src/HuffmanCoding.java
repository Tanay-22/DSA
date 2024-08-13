import java.util.*;

public class HuffmanCoding
{
    HashMap<Character, String> encoder;
    HashMap<String, Character> decoder;

    private class Node implements Comparable<Node>
    {
        Character data;
        int cost;   // frequency
        Node left;
        Node right;

        public Node(Character data, int cost)
        {
            this.data = data;
            this.cost = cost;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node o)
        {
            return this.cost - o.cost;
        }
    }

    private void initialise(String feeder)
    {
        HashMap<Character, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < feeder.length(); i++)
        {
            char ch = feeder.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        Set<Map.Entry<Character, Integer>> entrySet = freqMap.entrySet();

        for (Map.Entry<Character, Integer> entry: entrySet)
        {
            Node node = new Node(entry.getKey(), entry.getValue());
            priorityQueue.offer(node);
        }
        while (priorityQueue.size() != 1)
        {
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();

            Node newNode = new Node('\0', first.cost + second.cost);
            newNode.left = first;
            newNode.right = second;

            priorityQueue.offer(newNode);
        }
        Node fullTree = priorityQueue.poll();
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();

        this.initEncoderDecoder(fullTree, "");
    }
    private void initEncoderDecoder(Node node, String out)
    {
        if(node == null)
            return;
        if(node.left == null && node.right == null)
        {
            this.encoder.put(node.data, out);
            this.decoder.put(out, node.data);
        }
        initEncoderDecoder(node.left, out + "0");
        initEncoderDecoder(node.right, out + "1");
    }


    public String encode(String longUrl)
    {
        initialise(longUrl);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < longUrl.length(); i++)
            ans.append(encoder.get(longUrl.charAt(i)));

        return ans.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl)
    {
        StringBuilder key = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < shortUrl.length(); i++)
        {
            key.append(shortUrl.charAt(i));
            if(decoder.containsKey(key.toString()))
            {
                ans.append(decoder.get(key.toString()));
                key = new StringBuilder();
            }
        }
        return ans.toString();
    }
}
