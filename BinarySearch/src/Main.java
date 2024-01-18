public class Main
{
    //  SEARCH IN 2D ARRAY
    public static int findRow(int arr[][], int target)
    {
        int start = 0, end = arr.length - 1;
        while(start <= end)
        {
            int mid = start + (end - start)/2;
            if(arr[mid][0] < target)
                start = mid + 1;
            else if(arr[mid][0] > target)
                end = mid - 1;
            else
                return mid;
        }
        System.out.println(start + " " + end);
        return end;
    }
    public static boolean searchMatrix(int[][] arr, int target)
    {
        int rowNo = findRow(arr, target);
        System.out.println(rowNo);
        if(rowNo < 0 || rowNo >= arr.length)
            return false;
        int start = 0, end = arr[rowNo].length - 1;
        while(start <= end)
        {
            int mid = start + (end - start) / 2;
            if(arr[rowNo][mid] < target)
                start = mid + 1;
            else if(arr[rowNo][mid] > target)
                end = mid - 1;
            else
                return true;
        }
        return false;
    }

    // 81. Search in Rotated Sorted Array II
    static int res = -1;
    public static void rotatedSortedArrayGreatestIndex(int arr[], int start, int end)
    {
        if(start < end)
        {
            int mid = start + (end - start)/2;
            if(arr[mid] > arr[mid+1])
                res = mid;
            else
            {
                rotatedSortedArrayGreatestIndex(arr,start, mid);
                rotatedSortedArrayGreatestIndex(arr, mid+1, end);
            }
        }
    }
    public static int binSearch(int arr[], int start, int end, int target)
    {
        while( start < end)
        {
            int mid = start + (end - start)/2;
            if(arr[mid] == target)
                return mid;
            else if ( arr[mid] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }
    public static boolean search(int[] arr, int target)
    {
        rotatedSortedArrayGreatestIndex(arr, 0, arr.length-1);
        int result = binSearch(arr, 0, res, target);
        if(result == -1)
            result = binSearch(arr,res+1,arr.length-1,target);
        if(result == -1)
            return false;
        return true;
    }

    //167. Two Sum II - Input Array Is Sorted
    public static int[] twoSum(int[] arr, int target)
    {
        int res[] = {-1, -1};
        int start = 0, end = arr.length - 1;
        while (start < end)
        {
            int mid = start + (end - start)/2;
            System.out.println(mid);
            if(arr[mid] + arr[mid+1] == target)
            {
                res[0] = mid + 1;
                res[1] = mid + 2;
                return res;
            }
            else if(arr[mid] + arr[mid+1] < target)
                start = mid + 1;
            else
                end = mid;
        }
        return res;
    }

    public static void main(String[] args)
    {

        int arr[] = {2,7,11,15};
        int res[] = twoSum(arr, 26);
        System.out.println(res[0] + " " + res[1]);
    }
}