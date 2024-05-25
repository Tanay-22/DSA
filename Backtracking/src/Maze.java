public class Maze
{
    static int count(int row, int col)
    {
        if(row == 1 || col == 1)
            return 1;

        int left = count(row - 1, col);
        int right = count(row, col - 1);

        return left + right;
    }

    static void pathTill(int row, int col, String path)
    {
        if(row == 1 && col == 1)
        {
            System.out.println(path);
            return;
        }

        if(row > 1)
            pathTill(row - 1, col,path+"R");
        if(col > 1)
            pathTill(row, col - 1,path+"D");
    }

    static void pathTillDiagonal(int row, int col, String path)
    {
        if(row == 1 && col == 1)
        {
            System.out.println(path);
            return;
        }
        if(row > 1 && col > 1)
            pathTillDiagonal(row - 1, col - 1,path+"Di");
        if(row > 1)
            pathTillDiagonal(row - 1, col,path+"R");
        if(col > 1)
            pathTillDiagonal(row, col - 1,path+"D");
    }

    static void pathTillWithObsticle(int row, int col, boolean maze[][], String path)
    {
        if(row == maze.length - 1 && col == maze[0].length - 1)
        {
            System.out.println(path);
            return;
        }

        if(!maze[row][col])
            return;

        if(row < maze.length - 1)
            pathTillWithObsticle(row + 1, col, maze, path + 'R');
        if(col < maze[0].length - 1)
            pathTillWithObsticle(row, col + 1, maze, path + 'D');
    }

    public static void main(String[] args)
    {
        boolean maze[][] =
        {
            {true, true, true},
            {true, false, true},
            {true, true, true}
        };
        pathTillWithObsticle(0, 0, maze, "");
    }
}
