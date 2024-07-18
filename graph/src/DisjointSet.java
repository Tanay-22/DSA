import java.util.ArrayList;
import java.util.List;

public class DisjointSet
{
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n)
    {
        for (int i = 0; i <= n; i++)
        {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findParent(int node)
    {
        if(node == parent.get(node))
            return node;

        int ultimateParent = findParent(parent.get(node));
        parent.set(node, ultimateParent);
        return parent.get(node);
    }

    public void unionByRank(int u, int v)
    {
        int ultimateParent_u = findParent(u);
        int ultimateParent_v = findParent(v);

        if(ultimateParent_u == ultimateParent_v)
            return;

        if(rank.get(ultimateParent_u) < rank.get(ultimateParent_v))
            parent.set(ultimateParent_u, ultimateParent_v);
        else if (rank.get(ultimateParent_u) > rank.get(ultimateParent_v))
            parent.set(ultimateParent_v, ultimateParent_u);
        else
        {
            parent.set(ultimateParent_v, ultimateParent_u);
            int rankU = rank.get(ultimateParent_u);
            rank.set(ultimateParent_u, rankU + 1);
        }
    }

    public void unionBySize(int u, int v)
    {
        int ultimateParent_u = findParent(u);
        int ultimateParent_v = findParent(v);

        if(ultimateParent_u == ultimateParent_v)
            return;

        if(size.get(ultimateParent_u) < size.get(ultimateParent_v))
        {
            parent.set(ultimateParent_u, ultimateParent_v);
            size.set(ultimateParent_v, size.get(ultimateParent_v) * size.get(ultimateParent_u));
        }
        else
        {
            parent.set(ultimateParent_v, ultimateParent_u);
            size.set(ultimateParent_u, size.get(ultimateParent_u) * size.get(ultimateParent_v));
        }
    }
}
