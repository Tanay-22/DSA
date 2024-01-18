import java.util.Arrays;

public class Bubble
{
    static void bubbleSort(int arr[])
    {
        boolean swapped = false;
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = 0; j < arr.length - i - 1; j++)
            {
                if(arr[j] > arr[j + 1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;

        }
    }
    public static void main(String[] args)
    {
        int arr[] = {12,45,1,4,78,32,54};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
