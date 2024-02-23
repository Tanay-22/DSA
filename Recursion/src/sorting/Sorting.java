package sorting;

public class Sorting
{
    static void swap(int arr[], int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static void bubbleSort(int arr[], int i, int j)
    {
        if(i == arr.length)
            return;

        if(j < arr.length - i - 1)
        {
            if(arr[j] > arr[j+1])
                swap(arr, j, j+1);
            bubbleSort(arr, i, j+1);
        }
        else
            bubbleSort(arr, i+1, 0);
    }

    static void selectionSort(int arr[], int i, int j, int minIdx)
    {
        if(i == arr.length)
            return;

        if(j < arr.length)
        {
            if(arr[minIdx] > arr[j])
                selectionSort(arr, i, j+1, j);
            else
                selectionSort(arr, i, j+1, minIdx);
        }
        else
        {
            swap(arr, i, minIdx);
            selectionSort(arr, i+1, i+2, i+1);
        }
    }
    static void print(int arr[], int i)
    {
        if(i == arr.length)
            return;
        else
        {
            System.out.print(arr[i] + " ");
            print(arr, i+1);
        }
    }



    public static void main(String[] args)
    {
        int arr[] = {10, 9, 8, 7, 6, 5, 4, 3};
        selectionSort(arr, 0, 1,0);
        print(arr, 0);
    }
}
