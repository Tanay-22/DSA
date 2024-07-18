public class Edge implements Comparable<Edge>
{
    int source, destination, weight;

    public Edge(int source, int destination, int weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int compareTo(Edge compareEdge)
    {
        return this.weight - compareEdge.weight;
    }
}
