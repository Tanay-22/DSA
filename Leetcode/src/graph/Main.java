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

        @Override
        public String toString()
        {
            return "RCPair{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
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
            if (!visited[i])
                this.checkSafeNodes(i, adj, visited, pathVisited, check);
        }
        for (int i = 0; i < V; i++)
        {
            if (check[i])
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
        for (Integer e : adj.get(node))
        {
            if (!visited[e])
            {
                if (checkSafeNodes(e, adj, visited, pathVisited, check))
                    return true;
            } else if (pathVisited[e])
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

        if (this.isCycle(list))
            return false;


        for (int i = 0; i < numCourses; i++)
        {
            if (!visited[i])
                this.canFinishHelper(list, i, stack, visited);
        }

        return stack.size() == numCourses;
    }

    private void canFinishHelper(List<List<Integer>> list, int node, Stack<Integer> stack, boolean visited[])
    {
        visited[node] = true;

        for (Integer e : list.get(node))
        {
            if (!visited[e])
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
            if (!visited[i])
            {
                if (this.dfsCheckCycle(list, i, visited, pathVisited))
                    return true;
            }
        }
        return false;
    }

    private boolean dfsCheckCycle(List<List<Integer>> list, int node, boolean visited[], boolean pathVisited[])
    {
        visited[node] = true;
        pathVisited[node] = true;

        for (Integer e : list.get(node))
        {
            if (!visited[e])
            {
                if (this.dfsCheckCycle(list, e, visited, pathVisited))
                    return true;
            } else if (pathVisited[e])
                return true;
        }
        pathVisited[node] = false;

        return false;
    }

    //https://www.geeksforgeeks.org/problems/alien-dictionary/1
    //  Alien Dictionary
    public String findOrder(String[] dict, int N, int K)
    {
        HashMap<Character, List<Character>> dictionary = new HashMap<>();
        this.populateDictionary(dictionary, dict);

        //bfs
        int indegree[] = new int[K];
        for (char key : dictionary.keySet())
        {
            for (Character it : dictionary.get(key))
                indegree[it - 'a']++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < K; i++)
        {
            if (indegree[i] == 0)
                queue.offer(i);
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty())
        {
            int node = queue.poll();
            sb.append((char) (node + 'a'));
            count++;
            List<Character> neigbours = dictionary.get((char) (node + 'a'));
            if (neigbours != null)
            {
                for (Character it : neigbours)
                {
                    indegree[it - 'a']--;
                    if (indegree[it - 'a'] == 0)
                        queue.offer(it - 'a');
                }
            }
        }
        if (count == K)
            return sb.toString();
        else
            return "";
    }

    private void populateDictionary(HashMap<Character, List<Character>> dictionary, String dict[])
    {
        for (int i = 0; i < dict.length - 1; i++)
        {
            String first = dict[i];
            String second = dict[i + 1];
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

    //https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1
    //Shortest path in Directed Acyclic Graph
    private class Pair
    {
        int vertex;
        int weight;

        public Pair(int vertex, int weight)
        {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public int[] shortestPath(int N, int M, int[][] edges)
    {
        List<List<Pair>> list = new ArrayList<>();

        for (int i = 0; i < N; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < M; i++)
        {
            Pair pair = new Pair(edges[i][1], edges[i][2]); // { node, weight }
            list.get(edges[i][0]).add(pair);
        }
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[N];
        for (int i = 0; i < N; i++)
        {
            if (!visited[i])
                this.topoDFS(i, visited, stack, list);
        }
        int[] distance = new int[N];
        Arrays.fill(distance, (int) 1e9);
        distance[0] = 0;
        while (!stack.isEmpty())
        {
            int current = stack.pop();
            for (Pair n : list.get(current))
            {
                if (distance[current] + n.weight < distance[n.vertex])
                    distance[n.vertex] = distance[current] + n.weight;
            }
        }
        for (int i = 0; i < N; i++)
        {
            if (distance[i] == 1e9)
                distance[i] = -1;
        }
        return distance;
    }

    private void topoDFS(int node, boolean visited[], Stack<Integer> stack, List<List<Pair>> list)
    {
        visited[node] = true;
        for (Pair neighbour : list.get(node))
        {
            if (!visited[neighbour.vertex])
                topoDFS(neighbour.vertex, visited, stack, list);
        }
        stack.push(node);
    }


    //  https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1
    //  Shortest path in Undirected Graph
    public int[] shortestPath(int[][] edges, int n, int m, int src)
    {
        List<List<Pair>> list = new ArrayList<>();

        for (int i = 0; i < n; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < m; i++)
        {
            Pair pair = new Pair(edges[i][1], edges[i][2]); // { node, weight }
            list.get(edges[i][0]).add(pair);
        }
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[n];
        for (int i = 0; i < n; i++)
        {
            if (!visited[i])
                this.topoDFS(i, visited, stack, list);
        }
        int[] distance = new int[n];
        Arrays.fill(distance, (int) 1e9);
        distance[0] = 0;
        while (!stack.isEmpty())
        {
            int current = stack.pop();
            for (Pair pair : list.get(current))
            {
                if (distance[current] + pair.weight < distance[pair.vertex])
                    distance[pair.vertex] = distance[current] + pair.weight;
            }
        }
        for (int i = 0; i < n; i++)
        {
            if (distance[i] == 1e9)
                distance[i] = -1;
        }
        return distance;
    }

    //  127. Word Ladder
    private class Word
    {
        String word;
        int level;

        public Word(String word, int level)
        {
            this.word = word;
            this.level = level;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
        Set<String> set = new HashSet<>();
        int wordLength = wordList.get(0).length();
        set.addAll(wordList);
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(beginWord, 1));
        set.removeIf(word -> word.equals(beginWord));

        while (!queue.isEmpty())
        {
            Word word = queue.poll();
            if (word.word.equals(endWord))
                return word.level;

            for (int i = 0; i < wordLength; i++)
            {
                for (char j = 'a'; j <= 'z'; j++)
                {
                    char newWordArray[] = word.word.toCharArray();
                    newWordArray[i] = j;
                    String newWord = String.valueOf(newWordArray);

                    if (set.contains(newWord))
                    {
                        set.remove(newWord);
                        queue.offer(new Word(newWord, word.level + 1));
                    }
                }
            }
        }
        return 0;
    }

    // 126. Word Ladder II
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
    {
        List<List<String>> ans = new ArrayList<>();

        if (!wordList.contains(endWord))
            return ans;

        int level = 0;
        Set<String> set = new HashSet<>();
        int wordLength = wordList.get(0).length();
        set.addAll(wordList);

        List<String> list = new ArrayList<>();
        List<String> levelList = new ArrayList<>();
        list.add(beginWord);

        Queue<List<String>> queue = new LinkedList<>();
        queue.add(list);

        while (!queue.isEmpty())
        {
            List<String> current = queue.poll();
            if (current.size() > level)
            {
                level++;
                for (String s : levelList)
                    set.remove(s);
            }
            String word = current.get(current.size() - 1);
            if (word.equals(endWord))
            {
                if (ans.isEmpty() || ans.get(0).size() == current.size())
                    ans.add(current);
            }
            for (int i = 0; i < wordLength; i++)
            {
                for (char j = 'a'; j <= 'z'; j++)
                {
                    char newWordArray[] = word.toCharArray();
                    newWordArray[i] = j;
                    String newWord = String.valueOf(newWordArray);

                    if (set.contains(newWord))
                    {
                        current.add(newWord);
                        queue.add(new ArrayList<>(current));
                        levelList.add(newWord);
                        current.remove(current.size() - 1);
                    }
                }
            }
        }
        return ans;
    }

    //1091. Shortest Path in Binary Matrix
    public int shortestPathBinaryMatrix(int[][] grid)
    {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0)
            return -1;

        int directions[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                grid[i][j] = (grid[i][j] == 1) ? -1 : (int) 1e9;
        }
        Queue<RCPair> queue = new LinkedList<>();
        queue.offer(new RCPair(0, 0));
        grid[0][0] = 0;
        while (!queue.isEmpty())
        {
            RCPair current = queue.poll();

            for (int direction[] : directions)
            {
                int nrow = current.row + direction[0];
                int ncol = current.col + direction[1];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && grid[nrow][ncol] != -1)
                {
                    if (grid[nrow][ncol] > grid[current.row][current.col] + 1)
                    {
                        grid[nrow][ncol] = grid[current.row][current.col] + 1;
                        queue.offer(new RCPair(nrow, ncol));
                    }
                }
            }
        }
        return grid[n - 1][n - 1] == 1e9 ? -1 : grid[n - 1][n - 1] + 1;
    }

    //1631. Path With Minimum Effort
    private class Trio
    {
        int row;
        int col;
        int effort;

        public Trio(int row, int col, int effort)
        {
            this.row = row;
            this.col = col;
            this.effort = effort;
        }
    }

    public int minimumEffortPath(int[][] heights)
    {
        int m = heights.length, n = heights[0].length;
        int directions[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        int efforts[][] = new int[m][n];
        for (int[] e : efforts)
            Arrays.fill(e, (int) 1e9);

        PriorityQueue<Trio> priorityQueue = new PriorityQueue<>((x, y) -> x.effort - y.effort);
        priorityQueue.offer(new Trio(0, 0, 0));
        efforts[0][0] = 0;

        while (!priorityQueue.isEmpty())
        {
            Trio current = priorityQueue.poll();
            if (current.row == m - 1 && current.col == n - 1)
                return current.effort;

            for (int direction[] : directions)
            {
                int nrow = current.row + direction[0];
                int ncol = current.col + direction[1];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n)
                {
                    int newEffort = Math.max(
                            Math.abs(heights[nrow][ncol] - heights[current.row][current.col]), current.effort);

                    if (newEffort < efforts[nrow][ncol])
                    {
                        efforts[nrow][ncol] = newEffort;
                        priorityQueue.offer(new Trio(nrow, ncol, newEffort));
                    }
                }
            }
        }
        return 0;
    }

    //787. Cheapest Flights Within K Stops
    private class Flight
    {
        int destination;
        int price;

        public Flight(int destination, int price)
        {
            this.destination = destination;
            this.price = price;
        }
    }

    private class Tuple
    {
        int stops;
        int city;
        int cost;

        public Tuple(int stops, int city, int cost)
        {
            this.stops = stops;
            this.city = city;
            this.cost = cost;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k)
    {
        List<List<Flight>> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            list.add(new ArrayList<>());
        for (int i = 0; i < flights.length; i++)
            list.get(flights[i][0]).add(new Flight(flights[i][1], flights[i][2]));

        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(new Tuple(0, src, 0));
        int costs[] = new int[n];
        Arrays.fill(costs, (int) 1e9);
        costs[src] = 0;

        while (!queue.isEmpty())
        {
            Tuple current = queue.poll();

            if (current.stops > k)
                continue;
            for (Flight flight : list.get(current.city))
            {
                if (current.cost + flight.price < costs[flight.destination] && current.stops <= k)
                {
                    costs[flight.destination] = current.cost + flight.price;
                    queue.offer(new Tuple(current.stops + 1, flight.destination,
                            current.cost + flight.price));
                }
            }
        }
        return costs[dst] == 1e9 ? -1 : costs[dst];
    }

    //  https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1
    //  Minimum Multiplications to reach End
    int minimumMultiplications(int[] arr, int start, int end)
    {
        int mod = (int) 1e5;
        int multiplications[] = new int[mod];
        Arrays.fill(multiplications, (int) 1e9);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.offer(start);
        multiplications[0] = 1;

        while (!priorityQueue.isEmpty())
        {
            Integer current = priorityQueue.poll();
            for (int muliplier : arr)
            {
                int newNum = (current * muliplier) % mod;
                if (multiplications[newNum] > multiplications[current] + 1)
                {
                    multiplications[newNum] = multiplications[current] + 1;
                    if (newNum == end)
                        return multiplications[newNum];

                    priorityQueue.offer(newNum);
                }
            }
        }
        return -1;
    }

    //  1976. Number of Ways to Arrive at Destination
    private class Road
    {
        long time;
        int destination;

        public Road(int destination, long time)
        {
            this.destination = destination;
            this.time = time;
        }
    }
    public int countPaths(int n, int[][] roads)
    {
        int mod = (int)(1e9) + 7;
        List<List<Road>> list = new ArrayList<>();
        long totalTime[] = new long[n];
        Arrays.fill(totalTime, Long.MAX_VALUE);
        int ways[] = new int[n];

        for (int i = 0; i < n; i++)
            list.add(new ArrayList<>());
        for (int i = 0; i < roads.length; i++)
        {
            list.get(roads[i][0]).add(new Road(roads[i][1], roads[i][2]));
            list.get(roads[i][1]).add(new Road(roads[i][0], roads[i][2]));
        }

        PriorityQueue<Road> priorityQueue = new PriorityQueue<>((x, y) -> Long.compare(x.time, y.time));
        priorityQueue.offer(new Road(0, 0));
        totalTime[0] = 0l;
        ways[0] = 1;

        while (!priorityQueue.isEmpty())
        {
            Road current = priorityQueue.poll();

            for (Road road: list.get(current.destination))
            {
                if(current.time + road.time < totalTime[road.destination])
                {
                    totalTime[road.destination] = current.time + road.time;
                    priorityQueue.offer(new Road(road.destination, totalTime[road.destination]));
                    ways[road.destination] = ways[current.destination];
                }
                else if (current.time + road.time == totalTime[road.destination])
                    ways[road.destination] = (ways[current.destination] + ways[road.destination]) % mod;
            }
        }
        return ways[n-1] % mod;
    }


    // 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
    public int findTheCity(int n, int[][] edges, int distanceThreshold)
    {
        int distance[][] = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(i != j)
                    distance[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < edges.length; i++)
        {
            distance[edges[i][0]][edges[i][1]] = edges[i][2];
            distance[edges[i][1]][edges[i][0]] = edges[i][2];
        }

        for (int k = 0; k < n; k++)
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if(distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE)
                        continue;
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        int min = n;
        int city = -1;
        for (int i = 0; i < n; i++)
        {
            int count = 0;
            for (int j = 0; j < n; j++)
            {
                if(distance[i][j] <= distanceThreshold)
                    count++;
            }
            if(count <= min)
            {
                min = count;
                city = i;
            }
        }
        return city;
    }

    //  909. Snakes and Ladders
    public int snakesAndLadders(int[][] board)
    {
        int n = board.length;
        int[] dices = {1, 2, 3, 4, 5, 6};
        boolean[] visited = new boolean[n*n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[0] = true;
        int minCount = 0;

        while (!queue.isEmpty())
        {
            int size = queue.size();
            while (size > 0)
            {
                int currentPosition = queue.poll();
                if(currentPosition == n * n)
                    return minCount;

                for (int steps: dices)
                {
                    int newNum = currentPosition + steps > n*n ? n*n : currentPosition + steps;
                    RCPair newPosition = this.numToIndex(newNum, n);

                    if(board[newPosition.row][newPosition.col] != -1)
                        newNum = board[newPosition.row][newPosition.col];

                    if(visited[newNum-1])
                        continue;

                    queue.offer(newNum);
                    visited[newNum-1] = true;
                }
                size--;
            }
            minCount++;
        }
        return -1;
    }
    private RCPair numToIndex(int num, int n)
    {
        int r = (num - 1) / n;
        int c = (num - 1) % n;
        if (r % 2 == 1)
            c = n - 1 - c;

        int row = n - 1 - r;
        int col = c;

        return new RCPair(row, col);
    }


    //
    public int[] findRedundantConnection(int[][] edges)
    {
        Set<Integer> set = new HashSet<>();
        for(int[] e: edges)
        {
            if(set.contains(e[0]) && set.contains(e[1]))
                return e;
            else
            {
                set.add(e[0]);
                set.add(e[1]);
            }

        }
        return null;
    }

    //  721. Accounts Merge
    public List<List<String>> accountsMerge(List<List<String>> accounts)
    {
        int n = accounts.size();
        List<List<String>> ans = new ArrayList<>();
        DisjointSet disjointSet = new DisjointSet(n);
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < accounts.size(); i++)
        {
            List<String> list = accounts.get(i);
            for (int j = 1; j < list.size(); j++)
            {
                if(!hashMap.containsKey(list.get(j)))
                    hashMap.put(list.get(j), i);
                else
                    disjointSet.unionBySize(i, hashMap.get(list.get(j)));
            }
        }
        List<String>[] mergeMail = new ArrayList[n];
        for (int i = 0; i < n; i++)
            mergeMail[i] = new ArrayList<>();

        for (Map.Entry<String, Integer> set: hashMap.entrySet())
        {
            String mail = set.getKey();
            int node = disjointSet.findParent(set.getValue());
            mergeMail[node].add(mail);
        }
        for (int i = 0; i < n; i++)
        {
            if(mergeMail[i].size() == 0)
                continue;
            Collections.sort(mergeMail[i]);
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            for (String j: mergeMail[i])
                temp.add(j);
            ans.add(temp);
        }
        return ans;
    }

    // 827. Making A Large Island
    public int largestIsland(int[][] grid)
    {
        int n = grid.length;
        DisjointSet disjointSet = new DisjointSet(n * n);
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(grid[i][j] == 0)
                    continue;
                for(int direction[]: directions)
                {
                    int nrow = i + direction[0];
                    int ncol = j + direction[1];

                    if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && grid[nrow][ncol] == 1)
                    {
                        int num = n * i + j;
                        int nNum = n * nrow + ncol;
                        disjointSet.unionBySize(num, nNum);
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == 1)
                    continue;
                Set<Integer> components = new HashSet<>();
                for(int direction[]: directions)
                {
                    int nrow = i + direction[0];
                    int ncol = j + direction[1];

                    if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && grid[nrow][ncol] == 1)
                        components.add(disjointSet.findParent(nrow * n + ncol));
                }
                int sizeTotal = 0;
                for (Integer parent: components)
                    sizeTotal += disjointSet.size.get(parent);
                max = Math.max(max, sizeTotal + 1);
            }
        }
        for (int i = 0; i < n * n; i++)
            max = Math.max(max, disjointSet.size.get(i));

        return max;
    }

    public int removeStones(int[][] stones)
    {
        int n = stones.length;
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < n; i++)
        {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        DisjointSet disjointSet = new DisjointSet(maxRow + maxCol + 1);
        HashMap<Integer, Integer> stonesNodes = new HashMap<>();
        for (int i = 0; i < n; i++)
        {
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxRow + 1;
            disjointSet.unionBySize(nodeRow, nodeCol);
            stonesNodes.put(nodeRow, 1);
            stonesNodes.put(nodeCol, 1);
        }
        int count = 0;
        for(Map.Entry<Integer, Integer> set: stonesNodes.entrySet())
        {
            if(disjointSet.findParent(set.getKey()) == set.getKey())
                count++;
        }
        return n - count;
    }

    //  2976. Minimum Cost to Convert String I
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
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost)
    {
        List<List<Vertex>> list = new ArrayList<>();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < original.length; i++)
        {
            if(!hashMap.containsKey(original[i]))
            {
                hashMap.put(original[i], count++);
                list.add(new ArrayList<>());
            }
        }
        for (int i = 0; i < changed.length; i++)
        {
            if(!hashMap.containsKey(changed[i]))
            {
                hashMap.put(changed[i], count++);
                list.add(new ArrayList<>());
            }
        }
        for (int i = 0; i < original.length; i++)
        {
            Vertex node = new Vertex(hashMap.get(changed[i]), cost[i]);
            list.get(hashMap.get(original[i])).add(node);
        }
        long totalCost = 0l;
        for (int i = 0; i < source.length(); i++)
        {
            if(source.charAt(i) != target.charAt(i))
            {
                if(!hashMap.containsKey(source.charAt(i)) || !hashMap.containsKey(target.charAt(i)))
                    return -1;

                int distance = dijsktra(list, hashMap.get(source.charAt(i)), hashMap.get(target.charAt(i)));
                if(distance == -1)
                    return -1;
                totalCost += distance;
            }
        }

        return totalCost;
    }
    private int dijsktra(List<List<Vertex>> list, int start, int target)
    {
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>((x, y) -> x.weight - y.weight);
        int v = list.size();
        int distance[] = new int[v];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        priorityQueue.offer(new Vertex(start, 0));

        while(!priorityQueue.isEmpty())
        {
            Vertex current = priorityQueue.poll();
            if (current.value == target)
                return distance[target];

            for(Vertex neighbour: list.get(current.value))
            {
                int newDist = current.weight + neighbour.weight;
                if(newDist < distance[neighbour.value])
                {
                    distance[neighbour.value] = newDist;
                    priorityQueue.offer(new Vertex(neighbour.value, newDist));
                }
            }
        }
        return distance[target] == Integer.MAX_VALUE ? -1 : distance[target];
    }


    public static void main(String[] args)
    {
        String source = "abcd";
        String target = "acbe";
        char[] original = {'a', 'b', 'c', 'c', 'e', 'd'};
        char[] changed = {'b', 'c', 'b', 'e', 'b', 'e'};
        int[] cost = {2, 5, 5, 1, 2, 20};

        Main main = new Main();
        System.out.println(main.minimumCost(source, target, original, changed, cost));

    }
}
