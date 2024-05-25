public class AllPaths
{
    static void pathTill(int row, int col, boolean maze[][], String path)
    {
        if(row == maze.length - 1 && col == maze[0].length - 1)
        {
            System.out.println(path);
            return;
        }

        if(row > 1)
            pathTill(row - 1, col, maze, path+"D");
        if(col > 1)
            pathTill(row, col - 1, maze,path+"R");

        if(row < maze.length - 1)
            pathTill(row + 1, col, maze,path+"R");
        if(col < maze[0].length - 1)
            pathTill(row, col + 1, maze,path+"D");
    }

    static void backtracking(int row, int col, boolean maze[][], String path)
    {
        if(row == maze.length - 1 && col == maze[0].length - 1)
        {
            System.out.println(path);
            return;
        }

        if(!maze[row][col])
            return;

        maze[row][col] = false;

        if(row < maze.length - 1)
            backtracking(row + 1, col, maze,path+"D");
        if(col < maze[0].length - 1)
            backtracking(row, col + 1, maze,path+"R");

        if(row > 0)
            backtracking(row - 1, col, maze, path+"U");
        if(col > 0)
            backtracking(row, col - 1, maze,path+"L");

        maze[row][col] = true;
    }

    public static void main(String[] args)
    {
        boolean board[][] =
        {
            {true, true, true},
            {true, true, true},
            {true, true, true}
        };

        backtracking(0, 0, board, "");
    }
}
