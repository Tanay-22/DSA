package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class KthLargest
{
    private List<Integer> list = new ArrayList<>();
    private int k;
    public KthLargest(int k, int[] nums)
    {
        this.k = k;
        for (int n: nums)
            list.add(n);
        Collections.sort(list);
    }

    public int add(int val)
    {
        int i = 0;
        while (i < list.size() && val > list.get(i))
            i++;

        if(i == list.size())
            list.add(val);
        else
            list.add(i, val);

        return list.get(list.size()-k);
    }
}