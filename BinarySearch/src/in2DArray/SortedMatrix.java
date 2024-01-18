package in2DArray;

public class SortedMatrix
{
    static int[] search(int matrix[][], int target)
    {
        int rows = matrix.length;
        int cols = matrix[0].length;    // check for null matrix

        if(rows == 1)
            return binarySearch(matrix, 0, 0, cols, target);

        int rStart = 0, rEnd = rows - 1, cMid = cols / 2;

        // run the loop till 2 rows are remaining
        while(rStart < (rEnd - 1))
        {
            int mid = rStart + (rEnd - rStart) / 2;
        }
    }

    static int[] binarySearch(int matrix[][], int row, int cStart, int cEnd, int target)
    {
        while(cStart <= cEnd)
        {
            int mid = cStart + (cEnd - cStart) / 2;
            if(matrix[row][mid] == target)
                return new int[]{row, mid};
            if (matrix[row][mid] < target)
                cStart = mid + 1;
            else
                cEnd = mid - 1;
        }
        return new int[]{-1,-1};
    }
    public static void main(String[] args)
    {

    }
}
