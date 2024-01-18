package in2DArray;
// log n + log m
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
        while(rStart < (rEnd - 1))  // while true, it will have more than 2 rows
        {
            int mid = rStart + (rEnd - rStart) / 2;
            if(matrix[mid][cMid] == target)
                return new int[]{mid, cMid};
            if(matrix[mid][cMid] < target)
                rStart = mid;
            else
                rEnd = mid;
        }
        // now we have 2 rows
        // check whether the target is in the col of 2 rows
        if(matrix[rStart][cMid] == target)
            return new int[]{rStart, cMid};
        if(matrix[rStart + 1][cMid] == target)
            return new int[]{rStart + 1, cMid};

        // search in 1st half
        if(matrix[rStart + 1][cMid] <= target)
            return binarySearch(matrix, rStart, 0, cMid - 1, target);

        // search in 2nd half
        if(matrix[rStart + 1][cMid + 1] >= target && target <= matrix[rStart][cols - 1])
            return binarySearch(matrix, rStart, cMid + 1, cols - 1, target);

        // search in 3rd half
        if(matrix[rStart + 1][cMid] <= target)
            return binarySearch(matrix, rStart + 1, 0, cMid - 1, target);

        // search in 4th half
        else
            return binarySearch(matrix, rStart + 1, cMid + 1, cols - 1, target);
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
