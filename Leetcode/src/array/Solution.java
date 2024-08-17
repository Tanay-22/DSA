package array;

import java.util.HashMap;
import java.util.Map;

class Solution
{
    int m;
    int n;
    int total;
    Map<Integer, Integer> map;

    public Solution(int m, int n)
    {
        this.m = m;
        this.n = n;
        this.total = m * n;
        this.map = new HashMap<>();
        reset();
    }

    private int indexToNum(int row, int col)
    {
        return row * n + col;
    }

    private int[] numToIndex(int num)
    {
        int row = num / n;
        int col = num % n;
        return new int[]{row, col};
    }

    public int[] flip()
    {
        int random = (int) (Math.random() * total);
        total--;
        int num = map.getOrDefault(random, random);
        map.put(random, map.getOrDefault(total, total));
        return numToIndex(num);
    }

    public void reset()
    {
        total = m * n;
        map.clear();
    }
}