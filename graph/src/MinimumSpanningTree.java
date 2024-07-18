import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree
{
    private class Pair
    {
        int node;
        int distance;

        public Pair(int node, int distance)
        {
            this.node = node;
            this.distance = distance;
        }
    }

    public int Prims(int v, List<List<List<Integer>>> list)
    {
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((x, y) -> x.distance - y.distance);
        boolean visited[] = new boolean[v];

        priorityQueue.offer(new Pair(0, 0));
        int sum = 0;

        while (!priorityQueue.isEmpty())
        {
            Pair current = priorityQueue.poll();

            if(visited[current.node])
                continue;

            visited[current.node] = true;
            sum += current.distance;

            for(List<Integer> e: list.get(current.node))
            {
                if(!visited[e.get(0)])
                    priorityQueue.offer(new Pair(e.get(0), e.get(1)));
            }
        }
        return sum;
    }

    public int Kruskal(int v, List<List<List<Integer>>> list)
    {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < v; i++)
        {
            for (List<Integer> e: list.get(i))
            {
                Edge temp = new Edge(i, e.get(0), e.get(1));
                edges.add(temp);
            }
        }
        DisjointSet disjointSet = new DisjointSet(v);
        Collections.sort(edges);
        int mstWeight = 0;

        for (Edge e: edges)
        {
            if(disjointSet.findParent(e.source) != disjointSet.findParent(e.destination))
            {
                mstWeight += e.weight;
                disjointSet.unionBySize(e.source, e.weight);
            }
        }
        return mstWeight;
    }
}
