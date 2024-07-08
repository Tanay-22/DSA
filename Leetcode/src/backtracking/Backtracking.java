package backtracking;

import java.util.*;
import java.util.stream.Collectors;

public class Backtracking
{
    // 51. N-Queens

    public static List<List<String>> solveNQueens(int n)
    {
        List<List<String>> queens = new ArrayList<>();
        String board[][] = new String[n][n];
        nQueenHelper(board, 0, queens);
        return queens;
    }

    public static int nQueenHelper(String maze[][], int row, List<List<String>> queens)
    {
        if (row == maze.length)
        {
            //display(maze);
            addToList(maze, queens);
            return 1;
        }

        int count = 0;

        for (int col = 0; col < maze.length; col++)
        {
            if (isSafe(maze, row, col))
            {
                maze[row][col] = "Q";
                count += nQueenHelper(maze, row + 1, queens);
                maze[row][col] = ".";
            }
        }

        return count;
    }

    private static void addToList(String board[][], List<List<String>> queens)
    {
        List<String> ans = new ArrayList<>();
        for (String row[] : board)
        {
            String r = "";
            for (String str : row)
            {
                r += Objects.equals(str, "Q") ? "Q" : ".";
            }
            ans.add(r);
        }
        queens.add(ans);
    }

    private static void display(String board[][])
    {
        System.out.println();
        for (String row[] : board)
        {
            for (String str : row)
                System.out.print((Objects.equals(str, "Q") ? "Q " : ". "));
            System.out.println();
        }
    }

    private static boolean isSafe(String board[][], int row, int col)
    {
        // vertical row
        for (int i = 0; i < row; i++)
        {
            if (Objects.equals(board[i][col], "Q"))
                return false;
        }

        // diagonal left
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++)
        {
            if (Objects.equals(board[row - i][col - i], "Q"))
                return false;
        }

        // diagonal left
        int maxRight = Math.min(row, board.length - col - 1);
        for (int i = 1; i <= maxRight; i++)
        {
            if (Objects.equals(board[row - i][col + i], "Q"))
                return false;
        }
        return true;
    }

    //22. Generate Parentheses
    public static List<String> generateParenthesis(int n)
    {
        List<String> ans = new ArrayList<>();
        generateParenthesisHelper(ans, "", n, n);
        return ans;
    }

    public static void generateParenthesisHelper(List<String> list, String s, int opening, int closing)
    {
        if (closing < opening)
            return;

        if (opening == 0 && closing == 0)
        {
            list.add(s);
            return;
        }

        if (opening > 0)
            generateParenthesisHelper(list, s + "(", opening - 1, closing);
        if (closing > 0)
            generateParenthesisHelper(list, s + ")", opening, closing - 1);
    }

    //39. Combination Sum
    public static List<List<Integer>> combinationSum(int[] candidates, int target)
    {
        List<List<Integer>> ans = new ArrayList<>();
        int flag[] = new int[candidates.length];
        combinationSumHelper(candidates, target, 0, ans, flag, 0);
        return ans;
    }

    public static void combinationSumHelper(int arr[], int target, int sum,
                                            List<List<Integer>> list, int flag[], int currentPos)
    {
        if (sum == target)
        {
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < arr.length; i++)
            {
                for (int j = 0; j < flag[i]; j++)
                {
                    nums.add(arr[i]);
                }
            }
            if (!nums.isEmpty())
                list.add(nums);
        }

        if (sum > target)
        {
            return;
        }

        for (int i = currentPos; i < arr.length; i++)
        {
            flag[i]++;
            combinationSumHelper(arr, target, sum + arr[i], list, flag, i);
            flag[i]--;
        }
    }

    //40. Combination Sum II

    public static List<List<Integer>> combinationSum2(int[] arr, int target)
    {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(arr);
        if (Arrays.stream(arr).sum() < target)
            return ans;

        int freq[] = new int[arr[arr.length - 1] - arr[0] + 1];
        int flag[] = new int[arr[arr.length - 1] - arr[0] + 1];

        for (int i = 0; i < arr.length; i++)
            freq[arr[i] - arr[0]]++;


        combinationSum2Helper(arr, target, 0, ans, freq, flag, 0);
        return ans;
    }

    private static void combinationSum2Helper(int arr[], int target, int sum,
                                              List<List<Integer>> list, int freq[], int flag[], int currentPos)
    {
        if (sum == target)
        {
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < flag.length; i++)
            {
                for (int j = 0; j < flag[i]; j++)
                {
                    nums.add(i + arr[0]);
                }
            }
            if (!nums.isEmpty())
            {
                boolean isUnique = true;
                for (List<Integer> l : list)
                {
                    if (l.equals(nums))
                    {
                        isUnique = false;
                        break;
                    }
                }
                if (isUnique)
                    list.add(nums);
            }
        }

        if (sum > target)
        {
            return;
        }

        for (int i = currentPos; i < freq.length; i++)
        {
            if (freq[i] > 0)
            {
                flag[i]++;
                freq[i]--;
                combinationSum2Helper(arr, target, sum + i + arr[0], list, freq, flag, i);
                flag[i]--;
                freq[i]++;
            }
        }


    }

    //77. Combinations
    public static List<List<Integer>> combine(int n, int k)
    {
        boolean flag[] = new boolean[n];
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        combineHelper(n, k, ans, flag, sub, 0);

        return ans;
    }

    private static void combineHelper(int n, int k, List<List<Integer>> list, boolean flag[],
                                      List<Integer> sub, int currentPosition)
    {
        if (k == 0)
        {
            list.add(new ArrayList<>(sub));
            return;
        }


        for (int i = currentPosition; i < n; i++)
        {
            if (!flag[i])
            {
                sub.add(i + 1);
                flag[i] = true;
                combineHelper(n, k - 1, list, flag, sub, i + 1);
                flag[i] = false;
                sub.remove(sub.size() - 1);
            }
        }
    }

    //216. Combination Sum III
    public static List<List<Integer>> combinationSum3(int k, int n)
    {
        boolean flag[] = new boolean[n];
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();

        combinationSum3Helper(k, n, ans, sub, flag, 0);

        return ans;
    }

    private static void combinationSum3Helper(int k, int n, List<List<Integer>> list, List<Integer> sub,
                                              boolean flag[], int currentPosition)
    {
        if (k == 0)
        {
            if (n == 0)
                list.add(new ArrayList<>(sub));
            return;
        }

        for (int i = currentPosition; i < n && i < 9; i++)
        {
            flag[i] = true;
            sub.add(i + 1);
            combinationSum3Helper(k - 1, n - i - 1, list, sub, flag, i + 1);
            flag[i] = false;
            sub.remove(sub.size() - 1);
        }
    }

    //37. Sudoku Solver
    public static void solveSudoku(char[][] board)
    {
        boolean f = solveSudokuHelper(board);
    }

    private static boolean solveSudokuHelper(char[][] board)
    {
        int n = board.length;
        int row = -1;
        int col = -1;

        boolean emptyLeft = true;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == '.')
                {
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
            if (!emptyLeft)
                break;
        }
        if (emptyLeft)
            return true;

        for (char number = '1'; number <= '9'; number++)
        {
            if (isSudokuSafe(board, row, col, number))
            {
                board[row][col] = number;

                if (solveSudokuHelper(board))
                    return true;
                else
                    board[row][col] = '.';
            }
        }
        return false;
    }

    private static boolean isSudokuSafe(char board[][], int row, int col, char num)
    {
        for (int i = 0; i < board.length; i++)
        {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }


        int sqrt = (int) Math.sqrt(board.length);
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;

        for (int r = rowStart; r < rowStart + sqrt; r++)
        {
            for (int c = colStart; c < colStart + sqrt; c++)
            {
                if (board[r][c] == num)
                    return false;
            }
        }
        return true;

    }

    //79. Word Search
    public static boolean exist(char[][] board, String word)
    {
        int m = board.length, n = board[0].length;

        if (m * n < word.length())
            return false;

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == word.charAt(0))
                {
                    boolean flag[][] = new boolean[m][n];
                    if (existHelper(board, i, j, word, 0, flag))
                        return true;
                }
            }
        }

        return false;
    }

    private static boolean existHelper(char board[][], int row, int col, String word, int index, boolean flag[][])
    {
        if (index == word.length())
            return true;

        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
                board[row][col] != word.charAt(index) || flag[row][col])
            return false;

        flag[row][col] = true;

        boolean found =
                existHelper(board, row + 1, col, word, index + 1, flag) ||
                        existHelper(board, row - 1, col, word, index + 1, flag) ||
                        existHelper(board, row, col + 1, word, index + 1, flag) ||
                        existHelper(board, row, col - 1, word, index + 1, flag);

        flag[row][col] = false;

        return found;
    }

    //494. Target Sum
    public static int findTargetSumWays(int[] nums, int target)
    {
        boolean flag[] = new boolean[nums.length];

        return findTargetSumWaysHelper(nums, target, 1, nums[0]) +
                findTargetSumWaysHelper(nums, target, 1, -nums[0]);

    }

    private static int findTargetSumWaysHelper(int arr[], int target, int index, int sum)
    {
        if (index == arr.length)
        {
            if (sum == target)
                return 1;
            else
                return 0;
        }


        return findTargetSumWaysHelper(arr, target, index + 1, sum + arr[index]) +
                findTargetSumWaysHelper(arr, target, index + 1, sum - arr[index]);
    }

    //1545. Find Kth Bit in Nth Binary String
    public static char findKthBit(int n, int k)
    {
        StringBuilder s = new StringBuilder("0");
        return findKthBitHelper(n - 1, k, s);
    }

    private static char findKthBitHelper(int n, int k, StringBuilder s)
    {
        while (n-- > 0)
        {
            StringBuilder st = new StringBuilder(s.toString());
            inverse(s);
            s.reverse();

            st.append("1" + s);
            s = st;
        }

        return s.charAt(k - 1);
    }

    private static void inverse(StringBuilder s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c == '0')
                s.setCharAt(i, '1');
            else if (c == '1')
                s.setCharAt(i, '0');
        }
    }

    //779. K-th Symbol in Grammar
    public static int kthGrammar(int n, int k)
    {
        if (n == 1)
            return 0;

        int mid = (int) Math.pow(2, n - 1) / 2;

        if (k <= mid)
            return kthGrammar(n - 1, k);
        else
            return 1 - kthGrammar(n - 1, k - mid);

    }

    //1922. Count Good Numbers
    public static int countGoodNumbers(long n)
    {
        int mod = (int) (1e9) + 7;

        long nEven = (n + 1) / 2, nOdd = n / 2;

        long result = countGoodNumbersHelper(4, nOdd, mod) * countGoodNumbersHelper(5, nEven, mod);
        return (int) (result % mod);
    }

    private static long countGoodNumbersHelper(int multiplier, long count, int mod)
    {
        if (count == 0)
            return 1;

        long pow = countGoodNumbersHelper(multiplier, count / 2, mod);

        if ((count & 1) == 0)
            return ((pow % mod) * (pow % mod)) % mod;
        else
            return (multiplier * (pow % mod) * (pow % mod)) % mod;
    }

    //90. Subsets II
    public static List<List<Integer>> subsetsWithDup(int arr[])
    {
        Arrays.sort(arr);
        Set<List<Integer>> ans = new HashSet<>();
        ans.add(new ArrayList<>());

        for (int i = 0; i < arr.length; i++)
        {
            List<Integer> sub = new ArrayList<>();
            subsetsWithDup(arr, 0, i + 1, sub, ans);
        }
        return ans.stream().toList();
    }

    private static void subsetsWithDup(int arr[], int offset, int size, List<Integer> sub,
                                       Set<List<Integer>> ans)
    {
        if (offset == size)
            return;

        sub.add(arr[offset]);
        subsetsWithDup(arr, offset + 1, size, sub, ans);

        ans.add(new ArrayList<>(sub));

        sub.remove(sub.size() - 1);
        subsetsWithDup(arr, offset + 1, size, sub, ans);
    }

    //47. Permutations II
    public static List<List<Integer>> permuteUnique(int[] nums)
    {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        boolean visited[] = new boolean[nums.length];

        permuatation(list, sub, nums, visited);

        return list;
    }

    private static void permuatation(List<List<Integer>> list, List<Integer> sub, int nums[], boolean visited[])
    {
        if (sub.size() == nums.length)
        {
            list.add(new ArrayList<>(sub));
            return;
        }
        for (int i = 0; i < nums.length; i++)
        {
            if (visited[i] || (i > 0 && !visited[i - 1] && nums[i - 1] == nums[i]))
                continue;

            visited[i] = true;
            sub.add(nums[i]);
            permuatation(list, sub, nums, visited);
            sub.remove(sub.size() - 1);
            visited[i] = false;
        }
    }

    //526. Beautiful Arrangement
    public static int countArrangement(int n)
    {
        int count[] = new int[1];
        boolean flag[] = new boolean[n];

        countArrangementHelper(n, 1, count, flag);
        return count[0];
    }

    private static void countArrangementHelper(int n, int num, int count[], boolean flag[])
    {
        if (num > n)
        {
            count[0]++;
            return;
        }

        for (int i = 1; i <= n; i++)
        {
            if (flag[i - 1])
                continue;

            if (num % i == 0 || i % num == 0)
            {
                flag[i - 1] = true;
                countArrangementHelper(n, num + 1, count, flag);
                flag[i - 1] = false;
            }
        }
    }

 
    /*public int uniquePathsIII(int[][] grid)
    {
        int directions[][] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int m = grid.length, n = grid[0].length;
        boolean visited[][] = new boolean[m][n];

        int startRow = -1, startCol = -1, endRow = -1, endCol = -1;
        boolean startFound = false, endFound = false;
        for (int i = 0; i < m && !(startFound && endFound); i++)
        {
            for (int j = 0; j < n; j++)
            {
                if(grid[i][j] == 1)
                {
                    startRow = i;
                    startCol = j;
                    startFound = true;
                }
                if(grid[i][j] == 2)
                {
                    endRow = i;
                    endCol = j;
                    endFound = true;
                }
            }
        }
        
    }
    private void uniquePathsIIIHelper(int grid[][], int row, int col, int targetRow, int targetCol, 
                                      boolean visited[][], int directions[][], int count[])
    {
        if(row < 0 || row == grid.length || col < 0 || col == grid[0].length)
            return;
        
        if(row == targetRow && col == targetCol)
        {
            count[0]++;
            return;
        }

        for(int[] direction: directions)
        {
            int nrow = row + direction[0];
            int ncol = col + direction[1];
            visited[nrow][ncol] = true;
        }
    }*/

    //46. Permutations
    public static List<List<Integer>> permute(int[] nums)
    {
        List<List<Integer>> ans = new ArrayList<>();
        boolean flag[] = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();

        permute(ans, list, nums, flag);

        return ans;
    }
    private static void permute(List<List<Integer>> ans, List<Integer> list, int nums[], boolean flag[])
    {
        if(list.size() == nums.length)
        {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++)
        {
            if(!flag[i])
            {
                flag[i] = true;
                list.add(nums[i]);
                permute(ans, list, nums, flag);
                list.remove(list.size()-1);
                flag[i] = false;
            }
        }
    }

    public static void main(String[] args)
    {
        int arr[] = {0, 1};
        System.out.println(permute(arr));
    }
}
