import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class DirectedGraph
{
    private HashMap<Integer, List<Integer>> hashMap;

    public DirectedGraph()
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
        }
    }

    public boolean isCycle()
    {
        int v = this.hashMap.size();
        boolean visited[] = new boolean[v];
        boolean pathVisited[] = new boolean[v];

        for (int i = 0; i < v; i++)
        {
            if(!visited[i])
            {
                if(this.dfsCheck(i, visited, pathVisited))
                    return true;
            }
        }
        return false;
    }
    private boolean dfsCheck(int node, boolean visited[], boolean pathVisited[])
    {
        visited[node] = true;
        pathVisited[node] = true;

        for(Integer e: this.hashMap.get(node))
        {
            if(!visited[e])
            {
                if(this.dfsCheck(e, visited, pathVisited))
                    return true;
            }
            else if (pathVisited[e])
                return true;
        }
        pathVisited[node] = false;

        return false;
    }

    // for directed acyclic graph
    public List<Integer> topologicalSort()
    {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[this.hashMap.size()];

        this.topoDFS(0, visited, stack);

        while (!stack.isEmpty())
            res.add(stack.pop());

        return res;
    }
    private void topoDFS(int node, boolean visited[], Stack<Integer> stack)
    {
        visited[node] = true;

        for (Integer neighbour: this.hashMap.get(node))
            topoDFS(neighbour, visited, stack);

        stack.push(node);
    }
    // linear ordering of vertices such that for every directed edge u -> v,
    // where vertex u comes before v in the ordering.
    public int[] kahnsAlgo()
    {
        //bfs
        int v = this.hashMap.size();
        int indegree[] = new int[v];
        for (int i = 0; i < v; i++)
        {
            for(Integer it: this.hashMap.get(i))
                indegree[it]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < v; i++)
        {
            if(indegree[i] == 0)
                queue.offer(i);
        }
        int topo[] = new int[v];
        int i = 0;
        while (!queue.isEmpty())
        {
            int node = queue.poll();
            topo[i++] = node;
            count++;
            for (int it: this.hashMap.get(node))
            {
                indegree[it]--;
                if(indegree[it] == 0)
                    queue.offer(it);
            }
        }
        if(count == v)
            return topo;
        else
            return null;
    }
    
}
