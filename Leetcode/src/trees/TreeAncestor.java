package trees;

import java.util.HashMap;
import java.util.Map;

class TreeAncestor
{
    Map<Integer, Integer> map;
    public TreeAncestor(int n, int[] parent) 
    {
        this.map = new HashMap<>();
        this.populate(parent);
    }

    private void populate(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            this.map.put(i, arr[i]);
        }
    }
    
    public int getKthAncestor(int node, int k) 
    {
        while (k-- > 0)
        {
            if(map.containsKey(node))
            {
                node = map.get(node);
            }
            else
            {
                return -1;
            }
        }
        return node;
    }
}