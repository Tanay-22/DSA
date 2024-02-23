package patterns;



public class Pattern
{
    static void starTriangle1(int row, int col)
    {
        if(row == 0)
            return;
        if(col < row)
        {
            System.out.print("* ");
            starTriangle1(row, col+1);
        }
        else
        {
            System.out.println();
            starTriangle1(row-1, 0);
        }
    }

    static void starTriangle2(int row, int col)
    {
        if(row == 0)
            return;
        if(col < row)
        {
            starTriangle2(row, col+1);
            System.out.print("* ");
        }
        else
        {
            starTriangle2(row-1, 0);
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        starTriangle2(4,0);
    }
}
