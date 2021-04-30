import java.util.*;

public class Part2 {

    static void random(int arr[],int low,int high)
    {
        Random rand= new Random();
        int pivot = rand.nextInt(high-low)+low;

        int temp1=arr[pivot];
        arr[pivot]=arr[high];
        arr[high]=temp1;
    }

    //classic partition method, helps QuickSort function
    static int partition(int arr[], int low, int high)
    {
        // pivot is choosen randomly
        random(arr,low,high);
        int pivot = arr[high];


        int i = (low-1); // index of smaller element
        for (int j = low; j < high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    // randomised version of quicksort
    static int QuickSort(int arr[], int low, int high)
    {
        if (low < high)
        {
            // pi is partitioning index
            int pi = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            QuickSort(arr, low, pi-1);
            QuickSort(arr, pi+1, high);
        }
        return -1;
    }


    //Typical bubbleSort method. Chose this method of sorting as it has a O(n^2) time complexity,
    // and an average time of O(n^2) so it will be easy to compare against a method like quicksort, since it isn't very fast.
   static void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }


    //our new bubble sort, that can use the left and right parameters to sort a sub array
    static void bubbleSort(int arr[], int left, int right)
    {
        int x = arr.length;
        for (int i = 0; i < x-(right-left); i++){
            for (int j = left; j < right-i; j++){
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }}
        }
    }

    // Hybrid Sort, calls quicksort if the subarray is greater than 350 values long and bubblesort if not.
    // I chose this as the cutoff value because the data I collected in the first part of this assignment
    // shows that that's around where quickosrt starts being much faster than O(n^2) sorting algorithsm.
    static void hybridSort(int[] arr, int left, int right)
    {
        if ((right-left)>350)
        {
            QuickSort(arr, left, right);
        }
        else
        {
            bubbleSort(arr, left, right);
        }

    }

    public static void main(String[] args) {
        //fills up two random arrays of length 1000, then sorts specific subarray using hybrid Sort
        int len = 1000;
        int[] j = new int[len];
        for (int x = 0; x<len; x++)
        {
            j[x]= (int) (Math.random() * 100);
        }
        int[] y = new int[len];

        for (int s = 0; s<len; s++)
        {
            y[s]= (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(j));
        System.out.println(Arrays.toString(y));

        hybridSort(j, 3, 400);
        System.out.println(Arrays.toString(j));

        hybridSort(y, 2, 5);
        System.out.println(Arrays.toString(y));

    }
}

