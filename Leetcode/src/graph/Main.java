package graph;


import java.util.*;

public class Main
{
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

    private class Node
    {
        public int val;
        public List<Node> neighbors;

        public Node()
        {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val)
        {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors)
        {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private class Steps extends RCPair
    {
        int steps;

        public Steps(int row, int col, int steps)
        {
            super(row, col);
            this.steps = steps;
        }
    }


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


    public int numIslands(char[][] grid)
    {
        int n = grid.length, m = grid[0].length;
        int count = 0;
        boolean visited[][] = new boolean[n][m];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (!visited[i][j] && grid[i][j] == '1')
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

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && !visited[nrow][ncol] &&
                        grid[nrow][ncol] == '1')
                {
                    visited[nrow][ncol] = true;
                    queue.add(new RCPair(nrow, ncol));
                }
            }
        }
    }

    // 733. Flood Fill
    public static int[][] floodFill(int[][] image, int sr, int sc, int color)
    {
        boolean visited[][] = new boolean[image.length][image[0].length];
        int directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int initialValue = image[sr][sc];

        floodFillHelper(image, sr, sc, color, visited, directions, initialValue);

        return image;
    }

    private static void floodFillHelper(int image[][], int sr, int sc, int color, boolean visited[][],
                                        int directions[][], int initialValue)
    {
        visited[sr][sc] = true;
        image[sr][sc] = color;

        for (int direction[] : directions)
        {
            int nrow = sr + direction[0];
            int ncol = sc + direction[1];

            if (nrow >= 0 && nrow < image.length && ncol >= 0 && ncol < image[0].length &&
                    !visited[nrow][ncol] && image[nrow][ncol] == initialValue)
                floodFillHelper(image, nrow, ncol, color, visited, directions, initialValue);
        }
    }

    // 934. Shortest Bridge
    public int shortestBridge(int[][] grid)
    {
        int directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int v = grid.length;
        boolean visited[][] = new boolean[v][v];
        Queue<RCPair> queue = new LinkedList<>();

        boolean found = false;
        for (int i = 0; i < v; i++)
        {
            if (found)
                break;
            for (int j = 0; j < v; j++)
            {
                if (grid[i][j] == 1)
                {
                    shortestBridgeDFS(grid, i, j, visited, directions, queue);
                    found = true;
                    break;
                }
            }
        }
        int steps = 0;
        while (!queue.isEmpty())
        {
            int size = queue.size();
            while (size-- > 0)
            {
                RCPair current = queue.poll();
                for (int direction[] : directions)
                {
                    int nrow = current.row + direction[0];
                    int ncol = current.col + direction[1];

                    if (nrow >= 0 && nrow < v && ncol >= 0 && ncol < v && !visited[nrow][ncol])
                    {
                        if (grid[nrow][ncol] == 1)
                            return steps;
                        visited[nrow][ncol] = true;
                        queue.offer(new RCPair(nrow, ncol));
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    private void shortestBridgeDFS(int grid[][], int row, int col, boolean visited[][], int directions[][],
                                   Queue<RCPair> queue)
    {
        int v = grid.length;
        if (row < 0 || row >= v || col < 0 || col >= v || visited[row][col] || grid[row][col] == 0)
            return;


        visited[row][col] = true;
        queue.offer(new RCPair(row, col));

        for (int direction[] : directions)
            shortestBridgeDFS(grid, row + direction[0], col + direction[1], visited, directions, queue);

    }

    // 2285. Maximum Total Importance of Roads
    public long maximumImportance(int n, int[][] roads)
    {
        int arr[] = new int[n];

        for (int road[] : roads)
        {
            arr[road[0]]++;
            arr[road[1]]++;
        }
        Arrays.sort(arr);

        long sum = 0l;
        for (int i = 0; i < n; i++)
            sum += arr[i] * (i + 1l);
        return sum;
    }

    // 994. Rotting Oranges
    public int orangesRotting(int[][] grid)
    {
        // bsf
        int m = grid.length, n = grid[0].length;
        boolean visited[][] = new boolean[m][n];
        int directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<RCPair> queue = new LinkedList<>();
        int ahahTamatar = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == 2)
                    queue.offer(new RCPair(i, j));
                else if (grid[i][j] == 1)
                    ahahTamatar++;
            }
        }
        if (ahahTamatar == 0)
            return 0;
        if (queue.isEmpty())
            return -1;
        int minutes = -1;
        while (!queue.isEmpty())
        {
            int size = queue.size();
            while (size-- > 0)
            {
                RCPair current = queue.poll();
                for (int direction[] : directions)
                {
                    int nrow = current.row + direction[0];
                    int ncol = current.col + direction[1];

                    if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && !visited[nrow][ncol] &&
                            grid[nrow][ncol] == 1)
                    {
                        visited[nrow][ncol] = true;
                        grid[nrow][ncol] = 2;
                        ahahTamatar--;
                        queue.offer(new RCPair(nrow, ncol));
                    }
                }
            }
            minutes++;
        }
        if (ahahTamatar == 0)
            return minutes;
        return -1;
    }

    //133. Clone Graph
    HashMap<Integer, Node> hashMap = new HashMap<>();

    public Node cloneGraph(Node node)
    {
        if (node == null)
            return null;

        if (hashMap.containsKey(node.val))
            return hashMap.get(node.val);

        Node clone = new Node(node.val);
        hashMap.put(node.val, clone);

        for (Node neighbour : node.neighbors)
            clone.neighbors.add(this.cloneGraph(neighbour));

        return clone;
    }

    public int[][] updateMatrix(int[][] mat)
    {
        int m = mat.length, n = mat[0].length;
        int result[][] = new int[m][n];
        boolean visited[][] = new boolean[m][n];

        //bsf
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Steps> queue = new LinkedList<>();
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (mat[i][j] == 0)
                {
                    queue.offer(new Steps(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty())
        {
            Steps current = queue.poll();
            result[current.row][current.col] = current.steps;
            for (int direction[] : directions)
            {
                int nrow = current.row + direction[0];
                int ncol = current.col + direction[1];

                if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && !visited[nrow][ncol])
                {
                    visited[nrow][ncol] = true;
                    queue.offer(new Steps(nrow, ncol, current.steps + 1));
                }
            }
        }
        return result;
    }

    // 130. Surrounded Regions
    public void solve(char[][] board)
    {
        int m = board.length, n = board[0].length;
        boolean visited[][] = new boolean[m][n];
        Queue<RCPair> queue = new LinkedList<>();

        for (int i = 0; i < m; i++)
        {
            if (board[i][0] == 'O')
                this.solveBfs(board, i, 0, visited, queue);

            if (board[i][n - 1] == 'O')
                this.solveBfs(board, i, n - 1, visited, queue);
        }
        for (int i = 0; i < n; i++)
        {
            if (board[0][i] == 'O')
                this.solveBfs(board, 0, i, visited, queue);

            if (board[m - 1][i] == 'O')
                this.solveBfs(board, m - 1, i, visited, queue);
        }
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (!visited[i][j] && board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

    }

    private void solveBfs(char board[][], int row, int col, boolean visited[][], Queue<RCPair> queue)
    {
        int m = board.length, n = board[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new RCPair(row, col));
        visited[row][col] = true;

        while (!queue.isEmpty())
        {
            RCPair current = queue.poll();
            for (int direction[] : directions)
            {
                int nrow = current.row + direction[0];
                int ncol = current.col + direction[1];

                if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && !visited[nrow][ncol] && board[nrow][ncol] == 'O')
                {
                    visited[nrow][ncol] = true;
                    queue.offer(new RCPair(nrow, ncol));
                }
            }
        }
    }

    // 1020. Number of Enclaves
    public int numEnclaves(int[][] grid)
    {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        boolean visited[][] = new boolean[m][n];
        Queue<RCPair> queue = new LinkedList<>();

        for (int i = 0; i < m; i++)
        {
            if (grid[i][0] == 1)
                this.numEnclaves(grid, i, 0, visited, queue);

            if (grid[i][n - 1] == 1)
                this.numEnclaves(grid, i, n - 1, visited, queue);
        }
        for (int i = 0; i < n; i++)
        {
            if (grid[0][i] == 1)
                this.numEnclaves(grid, 0, i, visited, queue);

            if (grid[m - 1][i] == 1)
                this.numEnclaves(grid, m - 1, i, visited, queue);
        }
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (!visited[i][j] && grid[i][j] == 1)
                    count++;
            }
        }
        return count;
    }

    private void numEnclaves(int grid[][], int row, int col, boolean visited[][], Queue<RCPair> queue)
    {
        int m = grid.length, n = grid[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new RCPair(row, col));
        visited[row][col] = true;

        while (!queue.isEmpty())
        {
            RCPair current = queue.poll();
            for (int direction[] : directions)
            {
                int nrow = current.row + direction[0];
                int ncol = current.col + direction[1];

                if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && !visited[nrow][ncol] && grid[nrow][ncol] == 1)
                {
                    visited[nrow][ncol] = true;
                    queue.offer(new RCPair(nrow, ncol));
                }
            }
        }
    }

    //  https://www.geeksforgeeks.org/problems/number-of-distinct-islands/0
    public int countDistinctIslands(int[][] grid)
    {
        int n = grid.length, m = grid[0].length;
        boolean visited[][] = new boolean[n][m];
        List<List<RCPair>> islands = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (!visited[i][j] && grid[i][j] == 1)
                {
                    List<RCPair> island = this.countDistinctIslandBFS(grid, i, j, visited);
                    int baseRow = island.get(0).row;
                    int baseCol = island.get(0).col;
                    island.get(0).row = 0;
                    island.get(0).col = 0;
                    for (int k = 1; k < island.size(); k++)
                    {
                        island.get(k).row -= baseRow;
                        island.get(k).col -= baseCol;
                    }
                    if (this.checkDistinctIsland(islands, island))
                        islands.add(island);
                }
            }
        }
        return islands.size();
    }

    private List<RCPair> countDistinctIslandBFS(int grid[][], int row, int col, boolean visited[][])
    {
        int n = grid.length, m = grid[0].length;
        List<RCPair> island = new ArrayList<>();
        Queue<RCPair> queue = new LinkedList<>();
        visited[row][col] = true;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new RCPair(row, col));

        while (!queue.isEmpty())
        {
            RCPair current = queue.poll();
            island.add(current);
            for (int[] direction : directions)
            {
                int nrow = current.row + direction[0];
                int ncol = current.col + direction[1];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && !visited[nrow][ncol] &&
                        grid[nrow][ncol] == 1)
                {
                    visited[nrow][ncol] = true;
                    queue.add(new RCPair(nrow, ncol));
                }
            }
        }
        return island;
    }

    private boolean checkDistinctIsland(List<List<RCPair>> islands, List<RCPair> island)
    {
        if (islands.size() == 0)
            return true;

        for (List<RCPair> i : islands)
        {
            if (i.size() == island.size())
            {
                boolean found = true;
                for (int j = 0; j < i.size(); j++)
                {
                    found = found && (i.get(j).row == island.get(j).row) &&
                            (i.get(j).col == island.get(j).col);
                }
                if (found)
                    return false;
            }
        }
        return true;
    }

    /*//https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
    public static ArrayList<String> findPath(int[][] m, int n)
    {
        boolean visited[][] = new boolean[n][n];
        int directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<String> list = new ArrayList<>();


    }*/

    private static void findPathHelper(int arr[][], int row, int col, boolean visited[][], int directions[][],
                                       List<String> list)
    {
        if (row < 0 || row >= arr.length || col < 0 || col >= arr.length || arr[row][col] == 0)
            return;

        if (row == arr.length - 1 && col == arr.length - 1)
            return;


    }

    //https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj)
    {
        boolean visited[] = new boolean[V];
        boolean pathVisited[] = new boolean[V];

        for (int i = 0; i < V; i++)
        {
            if (!visited[i])
            {
                if (this.isCyclicHelper(i, visited, pathVisited, adj))
                    return true;
            }
        }
        return false;
    }

    private boolean isCyclicHelper(int node, boolean visited[], boolean pathVisited[],
                                   ArrayList<ArrayList<Integer>> adj)
    {
        visited[node] = true;
        pathVisited[node] = true;

        for (Integer e : adj.get(node))
        {
            if (!visited[e])
            {
                if (isCyclicHelper(e, visited, pathVisited, adj))
                    return true;
            } else if (pathVisited[e])
                return true;
        }
        pathVisited[node] = false;
        return false;
    }

    //https://www.geeksforgeeks.org/problems/bipartite-graph/1
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>> adj)
    {
        int color[] = new int[V];
        for (int i = 0; i < V; i++)
        {
            if (!this.isBipartiteHelper(adj, color, 1, i))
                return false;
        }
        return true;
    }

    public boolean isBipartiteHelper(ArrayList<ArrayList<Integer>> adj, int color[], int colorValue, int node)
    {
        Queue<Integer> queue = new LinkedList<>();
        color[node] = colorValue;
        queue.offer(node);

        while (!queue.isEmpty())
        {
            int currentNode = queue.poll();
            List<Integer> neigbours = adj.get(currentNode);

            for (Integer neighbour : neigbours)
            {
                if (color[neighbour] == 0)
                {
                    color[neighbour] = -color[currentNode];
                    queue.offer(neighbour);
                } else if (color[neighbour] == color[currentNode])
                    return false;
            }
        }
        return true;
    }


    //    https://www.geeksforgeeks.org/problems/eventual-safe-states/1
    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj)
    {
        List<Integer> result = new ArrayList<>();

        boolean visited[] = new boolean[V];
        boolean pathVisited[] = new boolean[V];
        boolean check[] = new boolean[V];

        for (int i = 0; i < V; i++)
        {
            if(!visited[i])
                this.checkSafeNodes(i, adj, visited, pathVisited, check);
        }
        for (int i = 0; i < V; i++)
        {
            if(check[i])
                result.add(i);
        }
        return result;
    }
    private boolean checkSafeNodes(int node, List<List<Integer>> adj, boolean visited[], boolean pathVisited[],
                                   boolean check[])
    {
        visited[node] = true;
        pathVisited[node] = true;
        check[node] = false;
        for (Integer e: adj.get(node))
        {
            if(!visited[e])
            {
                if(checkSafeNodes(e, adj, visited, pathVisited, check))
                    return true;
            }
            else if(pathVisited[e])
                return true;
        }
        check[node] = true;
        pathVisited[node] = false;
        return false;
    }

    //207. Course Schedule
    public boolean canFinish(int numCourses, int[][] prerequisites)
    {

        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();
        boolean visited[] = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++)
            list.add(new ArrayList<>());

        for (int e[] : prerequisites)
            list.get(e[0]).add(e[1]);

        if(this.isCycle(list))
            return false;


        for (int i = 0; i < numCourses; i++)
        {
            if(!visited[i])
                this.canFinishHelper(list, i, stack, visited);
        }

        return stack.size() == numCourses;
    }
    private void canFinishHelper(List<List<Integer>> list, int node, Stack<Integer> stack, boolean visited[])
    {
        visited[node] = true;

        for(Integer e: list.get(node))
        {
            if(!visited[e])
                canFinishHelper(list, e, stack, visited);
        }
        stack.push(node);
    }

    private boolean isCycle(List<List<Integer>> list)
    {
        int v = list.size();
        boolean visited[] = new boolean[v];
        boolean pathVisited[] = new boolean[v];

        for (int i = 0; i < v; i++)
        {
            if(!visited[i])
            {
                if(this.dfsCheckCycle(list, i, visited, pathVisited))
                    return true;
            }
        }
        return false;
    }
    private boolean dfsCheckCycle(List<List<Integer>> list, int node, boolean visited[], boolean pathVisited[])
    {
        visited[node] = true;
        pathVisited[node] = true;

        for(Integer e: list.get(node))
        {
            if(!visited[e])
            {
                if(this.dfsCheckCycle(list, e, visited, pathVisited))
                    return true;
            }
            else if (pathVisited[e])
                return true;
        }
        pathVisited[node] = false;

        return false;
    }

    //https://www.geeksforgeeks.org/problems/alien-dictionary/1
    //  Alien Dictionary
    public String findOrder(String [] dict, int N, int K)
    {
        HashMap<Character, List<Character>> dictionary = new HashMap<>();
        this.populateDictionary(dictionary, dict);

        //bfs
        int indegree[] = new int[K];
        for (char key: dictionary.keySet())
        {
            for (Character it: dictionary.get(key))
                indegree[it-'a']++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < K; i++)
        {
            if(indegree[i] == 0)
                queue.offer(i);
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty())
        {
            int node = queue.poll();
            sb.append((char)(node+ 'a'));
            count++;
            List<Character> neigbours = dictionary.get((char)(node+'a'));
            if(neigbours != null)
            {
                for (Character it: neigbours)
                {
                    indegree[it-'a']--;
                    if(indegree[it-'a'] == 0)
                        queue.offer(it-'a');
                }
            }
        }
        if(count == K)
            return sb.toString();
        else
            return "";
    }
    private void populateDictionary(HashMap<Character, List<Character>> dictionary, String dict[])
    {
        for (int i = 0; i < dict.length-1; i++)
        {
            String first = dict[i];
            String second = dict[i+1];
            int j = 0;

            while (j < first.length() && j < second.length() && first.charAt(j) == second.charAt(j))
                j++;

            if (j < first.length() && j < second.length())
            {
                List<Character> neighbours = dictionary.computeIfAbsent(first.charAt(j), k -> new ArrayList<>());
                neighbours.add(second.charAt(j));

                dictionary.computeIfAbsent(second.charAt(j), k -> new ArrayList<>());
            }
        }
    }


    

    public static void main(String[] args)
    {
        /*int arr[][] = {
                {1, 0},
                {2, 1},
                {3, 1},
                {3, 7},
                {4, 3},
                {5, 3},
                {6, 3}
        };
        int a[] = {3,2,5,4,6,1,7,0};*/
        String[] dict = {"caa","aaa","aab"};
        Main main = new Main();
        System.out.println(main.findOrder(dict, 5, 4));
    }
}
