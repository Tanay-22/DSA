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

            List<Integer> neigbours = hashMap.get(currentNode);

            for(Integer neighbour: neigbours)
            {
                if(!visited[neighbour])
                {
                    visited[neighbour] = true;
                    queue.offer(neighbour);
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

    private class Pair
    {
        int value;
        int parent;

        public Pair(int value, int parent)
        {
            this.value = value;
            this.parent = parent;
        }
    }

    public boolean isCycleUsingBSF()
    {
        int v = this.hashMap.size();
        boolean visited[] = new boolean[v];

        for (int i = 0; i < v; i++)
        {
            if(!visited[i])
            {
                if(this.checkForCycleBFS(i, visited))
                    return true;
            }
        }
        return false;
    }
    private boolean checkForCycleBFS(int startNode, boolean visited[])
    {
        //BFS
        Queue<Pair> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.offer(new Pair(startNode, -1));
        while (!queue.isEmpty())
        {
            Pair currentNode = queue.poll();
            List<Integer> neighbours = hashMap.get(currentNode.value);
            for(Integer neighbour: neighbours)
            {
                if(!visited[neighbour])
                {
                    visited[neighbour] = true;
                    queue.offer(new Pair(neighbour, currentNode.value));
                }
                else if (currentNode.parent != neighbour)
                    return true;
            }
        }
        return false;
    }

    public boolean isCycleUsingDSF()
    {
        int v = this.hashMap.size();
        boolean visited[] = new boolean[v];

        for (int i = 0; i < v; i++)
        {
            if(!visited[i])
            {
                if(this.checkForCycleDFS(i, visited))
                    return true;
            }
        }
        return false;
    }
    private boolean checkForCycleDFS(int startNode, boolean visited[])
    {
        visited[startNode] = true;

        for(Integer e: this.hashMap.get(startNode))
        {
            if(!visited[e])
            {
                if(this.checkForCycleDFS(e, visited))
                    return true;
            }
            else if (e != startNode)
                return true;
        }
        return false;
    }

    //A bipartite graph can be colored with two colors, such that no adjacent vertices have the same color.
    public boolean isBipartiteBFS(int startNode)
    {
        int color[] = new int[hashMap.size()];

        for (int i = 0; i < hashMap.size(); i++)
        {
            if(color[i] == 0)
            {
                if(!this.isBipartiteBFSHelper(i, color, 1))
                    return false;
            }
        }
        return true;
    }
    private boolean isBipartiteBFSHelper(int startNode, int color[], int colorValue)
    {
        // 1 for let say RED color
        // -1 for let say BLUE color
        int v = this.hashMap.size();
        Queue<Integer> queue = new LinkedList<>();
        color[startNode] = colorValue;
        queue.offer(startNode);

        while (!queue.isEmpty())
        {
            int currentNode = queue.poll();
            List<Integer> neigbours = hashMap.get(currentNode);

            for(Integer neighbour: neigbours)
            {
                if(color[neighbour] == 0)
                {
                    color[neighbour] = -color[currentNode];
                    queue.offer(neighbour);
                }
                else if (color[neighbour] == color[currentNode])
                    return false;
            }
        }
        return true;
    }

    public boolean isBipartiteDFS(int startNode)
    {
        int color[] = new int[hashMap.size()];

        for (int i = 0; i < hashMap.size(); i++)
        {
            if(color[i] == 0)
            {
                if(!this.isBipartiteDFSHelper(color, i, 1))
                    return false;
            }
        }
        return true;
    }
    private boolean isBipartiteDFSHelper(int color[], int node, int colorValue)
    {
        color[node] = colorValue;

        for(Integer e: this.hashMap.get(node))
        {
            if(color[e] == 0)
            {
                if(!isBipartiteDFSHelper(color, e, -color[node]))
                    return false;
                else if (color[e] == color[node])
                    return false;
            }
        }
        return true;
    }
}
