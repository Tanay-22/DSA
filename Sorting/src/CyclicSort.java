import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CyclicSort
{
    //  ONLY APPLICABLE WHEN NUMBERS FORM A CERTAIN RANGE
    static void sort(int arr[])
    {
        int i = 0;
        while(i < arr.length)
        {
            int correct = arr[i] - 1;   // to be set according to the range-index relation
            if(arr[i] != arr[correct])
            {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            }
            else
                i++;
        }
    }

    static int Q268(int arr[])
    {
        int i = 0;
        while(i < arr.length)
        {
            int correct = arr[i];   // to be set according to the range-index relation
            if(correct != arr.length && arr[i] != arr[correct])
            {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            }
            else
                i++;
        }
        for (int j = 0; j < arr.length; j++)
        {
            if(arr[j] != j)
                return j;
        }
        return arr.length;
    }

    public static List<Integer> findDisappearedNumbers(int[] arr)
    {
        int i = 0;
        List<Integer> list = new ArrayList<Integer>();
        while(i < arr.length)
        {
            int correct = arr[i] - 1;   // to be set according to the range-index relation
            if(correct != arr.length && arr[i] != arr[correct])
            {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            }
            else
                i++;
        }
        System.out.println(Arrays.toString(arr));
        for (int j = 0; j < arr.length; j++)
        {
            if(arr[j] != j + 1)
                list.add(j+1);
        }
        return list;
    }

    //287. Find the Duplicate Number
    public static int findDuplicate(int[] arr)
    {
        int i = 0;
        while(i < arr.length)
        {
            int correct = arr[i] - 1;   // to be set according to the range-index relation
            if(arr[i] != arr[correct])
            {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            }
            else
                i++;
        }
        for (int j = 0; j < arr.length; j++)
        {
            if(arr[j] != j + 1)
                return arr[j];
        }
        return arr.length;
    }

    //442. Find All Duplicates in an Array
    public static List<Integer> findDuplicates(int[] arr)
    {
        List<Integer> list = new ArrayList<Integer>();
        int i = 0;
        while(i < arr.length)
        {
            int correct = arr[i] - 1;   // to be set according to the range-index relation
            if(arr[i] != arr[correct])
            {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            }
            else
                i++;
        }
        for (int j = 0; j < arr.length; j++)
        {
            if(arr[j] != j + 1)
                list.add(arr[j] );
        }
        return list;
    }

    //645. Set Mismatch
    public static int[] findErrorNums(int[] arr)
    {
        int err[] =new int[2];
        int i = 0;
        while(i < arr.length)
        {
            int correct = arr[i] - 1;   // to be set according to the range-index relation
            if(arr[i] != arr[correct])
            {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            }
            else
                i++;
        }
        for (int j = 0; j < arr.length; j++)
        {
            if(arr[j] != j + 1)
            {
                err[0] = arr[j];
                err[1] = j + 1;
            }
        }
        return err;
    }

    //41. First Missing Positive
    public static int firstMissingPositive(int arr[])
    {
        int i = 0;
        while(i < arr.length)
        {
            int correct = arr[i] - 1;   // to be set according to the range-index relation
            if(arr[i] > 0 && arr[i] < arr.length && arr[i] != arr[correct])
            {
                int temp = arr[i];
                arr[i] = arr[correct];
                arr[correct] = temp;
            }
            else
                i++;
        }
        for (int j = 0; j < arr.length; j++)
        {
            if(arr[j] != j + 1)
                return j + 1;
        }
        return arr.length + 1;
    }

    public static int findMin(int arr[])
    {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++)
            min = Math.min(min, arr[i]);
        return min;
    }

    public static void main(String[] args)
    {
        int arr1[] = {1,2,0};
        int arr2[] = {3,4,-1,1};
        System.out.println(firstMissingPositive(arr2));

    }
}
