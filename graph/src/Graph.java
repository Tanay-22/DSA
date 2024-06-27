import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Graph
{

    private HashMap<Integer, List<Integer>> hashMap;

    public Graph()
    {
        this.hashMap = new HashMap<>();
    }

    public void populate(int edges, BufferedReader br) throws IOException
    {
        for (int i = 0; i < edges; i++)
        {
            String inputs = br.readLine();
            int x = Integer.parseInt(inputs.substring(0, inputs.indexOf(' ')));
            int y = Integer.parseInt(inputs.substring(inputs.indexOf(' ')+1));

            // for x
            List<Integer> neighbours = this.hashMap.get(x);
            if(neighbours == null)
                neighbours = new ArrayList<>();
            neighbours.add(y);
            hashMap.put(x, neighbours);


            // for y
            neighbours = this.hashMap.get(y);

            if(neighbours == null)
                neighbours = new ArrayList<>();
            neighbours.add(x);
            hashMap.put(y, neighbours);
        }
    }

    public List<Integer> BFSTraversal(int startNode)
    {
        List<Integer> bfs = new ArrayList<>();
        int v = this.hashMap.size();
        boolean visited[] = new boolean[v];
        Queue<Integer> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.offer(startNode);

        while (!queue.isEmpty())
        {
            int currentNode = queue.poll();
            bfs.add(currentNode);

            for(Integer neighbour: hashMap.get(currentNode))
            {
                if(!visited[neighbour])
                {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
        return bfs;
    }

    public List<Integer> DFSTraversal(int startNode)
    {
        List<Integer> dfs = new ArrayList<>();
        boolean visited[] = new boolean[this.hashMap.size()];

        this.dfs(startNode, visited, dfs);
        return dfs;
    }
    private void dfs(int node, boolean visited[], List<Integer> dfs)
    {
        visited[node] = true;
        dfs.add(node);

        for(Integer e: this.hashMap.get(node))
        {
            if(!visited[e])
                dfs(e, visited, dfs);
        }
    }
}
