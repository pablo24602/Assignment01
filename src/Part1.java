import java.lang.reflect.Array;
import java.lang.*;
import java.util.*;
import java.time.*;

public class Part1
{
    public static int timeees = 0;


    //Selection Sort
    public static void selectionSort(double arr[])
    {
        int n = arr.length;

        // Sort through and move index of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Use typical code to find minimum
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the minimum element we found with the first element
            double temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }


    //Bubble Sort
    public static void bubbleSort(double arr[])
    {
        int n = arr.length;
        // loop through the array  from 0 through (n-1) and inside that loop 0 through (n-1-i(the looping variable) Use this to compare
        // values and shift upwards or downwards to sort one value at a time.
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    double temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }


    //Insertion Sort
    public static void insertionSort(double arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            double key = arr[i];
            int j = i - 1;

            //Move elements of arr, that are greater than key, //to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    //Merge Sort method! Divides the input array into two halves, calls itself for the two halves, and then merges the two sorted halves.
    public static void mergeSort(double[] a, int n) {
        //if empty or length of 1, just return
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        double[] l = new double[mid];
        double[] r = new double[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    //Merge: helper function of mergeSort, merges the two functions that mergesort creates.
    public static void merge(
            double[] a, double[] l, double[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }




    /// The main function that implements QuickSort()
    static void quickSort(double arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    //Partition for Quick Sort
    static int partition(double arr[], int low, int high)
    {
        double pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        double temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    //returns the timeees variable
    public static  int getTimeees()
    {
        return timeees;
    }

    //increases the timeees variable's value by 1
    public static void increment()
    {
        timeees++;
    }

    // Creates several double arrays f length d, fills them up with distinct values, and then inputs how long each
    // sorting algorithm takes to sort them using System.currentTimeMillis()
    public  static HashMap<String,Long>  Times(int d)
    {
        System.out.print(getTimeees());
        increment();

        double[] selectionCopy = new double[d];
        fill(selectionCopy);

        double[] bubbleCopy = new double[d];
        fill(bubbleCopy);

        double[] insertionCopy = new double[d];
        fill(insertionCopy);

        double[] mergeCopy = new double[d];
        fill(mergeCopy);

        double[] quickCopy =new double[d];
        fill(quickCopy);

        HashMap<String, Long> ans = new HashMap<String, Long>();

        ans.put("length", (long) d);

        long before = System.currentTimeMillis();
        selectionSort(selectionCopy);
        long after = System.currentTimeMillis();
        long delta = after - before;
        ans.put("Selection Sort", delta);

        before = System.currentTimeMillis();
        bubbleSort(bubbleCopy);
        after = System.currentTimeMillis();
        delta = after-before;
        ans.put("Bubble Sort", delta);

        before = System.currentTimeMillis();
        insertionSort(insertionCopy);
        after = System.currentTimeMillis();
        delta = after-before;
        ans.put("Insertion Sort", delta);

        before = System.currentTimeMillis();
        mergeSort(mergeCopy, mergeCopy.length);
        after = System.currentTimeMillis();
        delta = after-before;
        ans.put("Merge Sort", delta);

        before = System.currentTimeMillis();
        quickSort(quickCopy, 0, mergeCopy.length-1);
        after = System.currentTimeMillis();
        delta = after-before;
        ans.put("Quick Sort", delta);

        return ans;
    }

    //Fills a double array with random values from 0 - 499
    public static void fill(double[] ko)
    {
        for (int x = 0; x<ko.length; x++)
        {
            ko[x]= (Math.random()*500);
        }
    }

    public static void main(String[] args) {


        //creates an arraylist we'll use to store as hashmap with all values when it comes to sorting times and length of arrays
        ArrayList<HashMap<String, Long>> jee = new ArrayList<>();
        int numOfArrays = 100;
        int maxLen = 50000;

        //Calls the Times function to get multiple hashmaps to add to the arraylist of hashmaps, this is what fills out data set
        for (int x = 0; x<numOfArrays; x++)
        {
            int num = (int)((Math.random() * maxLen)+1);
            jee.add(Times(num));
        }

        //now prints out all values
        System.out.println("LENGTH");
        for (HashMap<String, Long> xii: jee)
        {
            System.out.println(xii.get("length"));
        }

        System.out.println("Insertion Sort");
        for (HashMap<String, Long> xii: jee)
        {
            System.out.println(xii.get("Insertion Sort"));
        }


        System.out.println("Bubble Sort");
        for (HashMap<String, Long> xii: jee)
        {
            System.out.println(xii.get("Bubble Sort"));
        }

        System.out.println("Selection Sort");
        for (HashMap<String, Long> xii: jee)
        {
            System.out.println(xii.get("Selection Sort"));
        }

        System.out.println("Merge Sort");
        for (HashMap<String, Long> xii: jee)
        {
            System.out.println(xii.get("Merge Sort"));
        }

        System.out.println("Quick Sort");
        for (HashMap<String, Long> xii: jee)
        {
            System.out.println(xii.get("Quick Sort"));
        }

    }
}
