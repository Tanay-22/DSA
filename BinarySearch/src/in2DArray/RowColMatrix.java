package in2DArray;

import java.util.Arrays;

public class RowColMatrix
{
    // elements are sorted row wise and column wise
    static int[] search(int matrix[][], int target)
    {
        int r = 0, c = matrix[0].length - 1;
        while(r < matrix.length && c >= 0)
        {
            if(matrix[r][c] == target)
                return new int[]{r,c};
            if(matrix[r][c] < target)
                r++;
            else
                c--;
        }
        return new int[]{-1,-1};
    }
    public static void main(String[] args)
    {
        int arr[][] =
                {
                        {10, 20, 30, 40},
                        {15, 25, 35, 45},
                        {28, 29, 37, 49},
                        {33, 34, 38, 50}
                };
        System.out.println(Arrays.toString(search(arr, 49)));
    }
}
