import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class WeightedGraph
{

    private class Vertex
    {
        int value;
        int weight;

        public Vertex(int value, int weight)
        {
            this.value = value;
            this.weight = weight;
        }
    }

    private HashMap<Integer, List<Vertex>> hashMap;

    public WeightedGraph()
    {
        this.hashMap = new HashMap<>();
    }

    public void populate(int edges, BufferedReader br) throws IOException
    {
        for (int i = 0; i < edges; i++)
        {
            String inputs = br.readLine();
            int x = Integer.parseInt(inputs.substring(0, inputs.indexOf(' ')));
            int y = Integer.parseInt(inputs.substring(inputs.indexOf(' ')+1, inputs.lastIndexOf(" ")));
            int weight = Integer.parseInt(inputs.substring(inputs.lastIndexOf(' ')+1));

            // for x
            List<Vertex> neighbours = this.hashMap.get(x);
            if(neighbours == null)
                neighbours = new ArrayList<>();
            neighbours.add(new Vertex(y, weight));
            hashMap.put(x, neighbours);


            // for y
            neighbours = this.hashMap.get(y);

            if(neighbours == null)
                neighbours = new ArrayList<>();
            neighbours.add(new Vertex(x, weight));
            hashMap.put(y, neighbours);
        }
    }

    public int[] dijkstraWithPQ()
    {
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<Vertex>((x, y) -> x.weight - y.weight);
        int v = this.hashMap.size();
        int distance[] = new int[v];

        Arrays.fill(distance, (int)1e9);
        distance[0] = 0;
        priorityQueue.offer(new Vertex(0, 0));

        while(!priorityQueue.isEmpty())
        {
            Vertex current = priorityQueue.poll();
            for(Vertex neighbour: this.hashMap.get(current.value))
            {
                int newDist = current.weight + neighbour.weight;
                if(newDist < distance[current.value])
                {
                    distance[current.value] = newDist;
                    priorityQueue.offer(new Vertex(neighbour.value, newDist));
                }
            }
        }
        return distance;
    }

    public int[] dijkstraWithSet()
    {
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<Vertex>((x, y) -> x.weight - y.weight);
        int v = this.hashMap.size();
        int distance[] = new int[v];

        Arrays.fill(distance, (int)1e9);
        distance[0] = 0;
        priorityQueue.offer(new Vertex(0, 0));

        while(!priorityQueue.isEmpty())
        {
            Vertex current = priorityQueue.poll();
            for(Vertex neighbour: this.hashMap.get(current.value))
            {
                int newDist = current.weight + neighbour.weight;
                if(newDist < distance[current.value])
                {
                    distance[current.value] = newDist;
                    priorityQueue.offer(new Vertex(neighbour.value, newDist));
                }
            }
        }
        return distance;
    }
}
