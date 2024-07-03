import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Graph graph = new Graph();
        System.out.print("Enter the number of edges : ");
        int edges = Integer.parseInt(br.readLine());
        graph.populate(edges, br);

        System.out.println(graph.BFSTraversal(0));
        System.out.println(graph.DFSTraversal(0));
        System.out.println("Cycle - " + graph.isCycleUsingBSF());
        System.out.println("Cycle - " + graph.isCycleUsingDSF());
    }
}
