package graph;

import java.util.LinkedList;
import java.util.Queue;

public class Main
{
    //  547. Number of Provinces
    public static int findCircleNum(int[][] isConnected)
    {
        int n = isConnected.length;
        boolean visited[] = new boolean[n];
        int count[] = new int[1];

        for (int i = 0; i < isConnected.length; i++)
        {
            if (!visited[i])
            {
                count[0]++;
                dfs(isConnected, visited, i, count);
            }
        }
        return count[0];
    }

    private static void dfs(int isConnected[][], boolean visited[], int node, int count[])
    {
        visited[node] = true;

        for (int i = 0; i < isConnected.length; i++)
        {
            if (i != node && !visited[i] && isConnected[node][i] == 1)
                dfs(isConnected, visited, i, count);
        }
    }

    //  200. Number of Islands
    private class RCPair
    {
        int row;
        int col;

        public RCPair(int row, int col)
        {
            this.row = row;
            this.col = col;
        }
    }
    public int numIslands(char[][] grid)
    {
        int n = grid.length, m = grid[0].length;
        int count = 0;
        boolean visited[][] = new boolean[n][m];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if(!visited[i][j] && grid[i][j] == '1')
                {
                    count++;
                    this.numIslandBfs(grid, i, j, visited);
                }
            }
        }
        return count;
    }
    private void numIslandBfs(char grid[][], int row, int col, boolean visited[][])
    {
        int n = grid.length, m = grid[0].length;
        Queue<RCPair> queue = new LinkedList<>();
        visited[row][col] = true;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new RCPair(row, col));

        while (!queue.isEmpty())
        {
            RCPair rcPair = queue.poll();
            for (int[] direction : directions)
            {
                int nrow = rcPair.row + direction[0];
                int ncol = rcPair.col + direction[1];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && !visited[nrow][ncol] &&
                        grid[nrow][ncol] == '1')
                {
                    visited[nrow][ncol] = true;
                    queue.add(new RCPair(nrow, ncol));
                }
            }
        }
    }

    //733. Flood Fill
    public int[][] floodFill(int[][] image, int sr, int sc, int color)
    {
        boolean visited[][] = new boolean[image.length][image[0].length];

        floodFillHelper(image, sr, sc, color, visited);


    }
    private void floodFillHelper(int image[][], int sr, int sc, int color, boolean visited[][])
    {
        visited[sr][sc] = true;
        image[sr][sc] = color;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int direction[]: directions)
        {
            
        }
    }

    public static void main(String[] args)
    {
        char arr[][] = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };



        Main main = new Main();

        System.out.println(main.numIslands(arr));

    }
}
