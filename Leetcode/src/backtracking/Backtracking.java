package backtracking;

import java.util.*;

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
        if(row == maze.length)
        {
            //display(maze);
            addToList(maze, queens);
            return 1;
        }

        int count = 0;

        for (int col = 0; col < maze.length; col++)
        {
            if(isSafe(maze, row, col))
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
        for (String row[]: board)
        {
            String r = "";
            for (String str: row)
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
        for (String row[]: board)
        {
            for (String str: row)
                System.out.print((Objects.equals(str, "Q") ? "Q " : ". "));
            System.out.println();
        }
    }

    private static boolean isSafe(String board[][], int row, int col)
    {
        // vertical row
        for (int i = 0; i < row; i++)
        {
            if(Objects.equals(board[i][col], "Q"))
                return false;
        }

        // diagonal left
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++)
        {
            if(Objects.equals(board[row - i][col - i], "Q"))
                return false;
        }

        // diagonal left
        int maxRight = Math.min(row, board.length - col - 1);
        for (int i = 1; i <= maxRight; i++)
        {
            if(Objects.equals(board[row - i][col + i], "Q"))
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
        if(closing < opening)
            return;

        if(opening == 0 && closing == 0)
        {
            list.add(s);
            return;
        }

        if (opening > 0)
            generateParenthesisHelper(list, s+"(", opening - 1, closing);
        if (closing > 0)
            generateParenthesisHelper(list, s+")", opening, closing - 1);
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
        if(sum == target)
        {
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < arr.length; i++)
            {
                for (int j = 0; j < flag[i]; j++)
                {
                    nums.add(arr[i]);
                }
            }
            if(!nums.isEmpty())
                list.add(nums);
        }

        if(sum > target)
        {
            return;
        }

        for (int i = currentPos; i < arr.length; i++)
        {
            flag[i]++;
            combinationSumHelper(arr, target, sum+arr[i], list, flag, i);
            flag[i]--;
        }
    }

    //40. Combination Sum II

    public static List<List<Integer>> combinationSum2(int[] arr, int target)
    {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(arr);
        if(Arrays.stream(arr).sum() < target)
            return ans;

        int freq[] = new int[arr[arr.length - 1] - arr[0] + 1];
        int flag[] = new int[arr[arr.length - 1] - arr[0] + 1];

        for (int i = 0; i < arr.length; i++)
            freq[arr[i] - arr[0]]++;


        combinationSum2Helper(arr, target, 0, ans, freq, flag, 0);
        return ans;
    }

    private static void combinationSum2Helper(int arr[], int target, int sum,
                                                     List<List<Integer>> list, int freq[],int flag[], int currentPos)
    {
        if(sum == target)
        {
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < flag.length; i++)
            {
                for (int j = 0; j < flag[i]; j++)
                {
                    nums.add(i + arr[0]);
                }
            }
            if(!nums.isEmpty())
            {
                boolean isUnique = true;
                for (List<Integer> l: list)
                {
                    if(l.equals(nums))
                    {
                        isUnique = false;
                        break;
                    }
                }
                if(isUnique)
                    list.add(nums);
            }
        }

        if(sum > target)
        {
            return;
        }

        for (int i = currentPos; i < freq.length; i++)
        {
            if(freq[i] > 0)
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
       if(k == 0)
       {
           list.add(new ArrayList<>(sub));
           return;
       }


        for (int i = currentPosition; i < n; i++)
        {
            if(!flag[i])
            {
                sub.add(i + 1);
                flag[i] = true;
                combineHelper(n, k - 1, list, flag, sub, i + 1);
                flag[i] = false;
                sub.remove(sub.size()-1);
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
        if(k == 0)
        {
            if(n == 0)
                list.add(new ArrayList<>(sub));
            return;
        }

        for (int i = currentPosition; i < n && i < 9; i++)
        {
            flag[i] = true;
            sub.add(i+1);
            combinationSum3Helper(k-1, n-i-1, list, sub, flag, i+1);
            flag[i] = false;
            sub.remove(sub.size()-1);
        }
    }


    public static void main(String[] args)
    {

        List<List<Integer>> ans = combinationSum3(2, 18);
        ans.stream().forEach(System.out::println);
    }
}
