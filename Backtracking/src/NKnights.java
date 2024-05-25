import java.util.Objects;

public class NKnights
{
    private static void display(boolean board[][])
    {
        System.out.println();
        for (boolean row[]: board)
        {
            for (boolean f: row)
                System.out.print(f ? "K " : ". ");
            System.out.println();
        }
    }

    private static boolean isSafe(boolean board[][], int row, int col, int knights)
    {
        // vertical row
        for (int i = 0; i < row; i++)
        {
            if(board[i][col])
                return false;
        }

        // diagonal left
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++)
        {
            if(board[row][i])
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

    static void knight(boolean board[][], int row, int col, int knights)
    {
        if(knights == 0)
        {
            display(board);
        }

        if(row == board.length - 1 && col == board.length)
            return;

        if(col == board.length)
        {
            knight(board, row+1, 0, knights);
            return;
        }

        if(isSafe(board, row, col + 1, knights))
        {
            board[row][col] = true;
            knight(board, row, col + 1, knights - 1);
            board[row][col] = false;
        }

        knight(board, row, col + 1, knights);
    }
}
