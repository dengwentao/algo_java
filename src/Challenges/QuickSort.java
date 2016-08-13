package Challenges;

/**
 * Created by wentaod on 1/4/16.
 */
public class QuickSort {

    static void quickSort(int[] a) {
        quickSort(a, 0, a.length-1);
    }

    static void quickSort(int[] a, int low, int high) {
        if(low < high) {
            int pivot = partition(a, low, high);
            quickSort(a, low, pivot - 1);
            quickSort(a, pivot + 1, high);
        }
    }

    static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i=low, j=high;
        while(i < j) {
            while(a[i] < pivot)
                i++;
            while(i < j && a[j] >= pivot)
                j--;
            if(i < j) {
                swap(a, i, j);
            }
        }
        swap(a, i, high);
        return i;
    }

    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;

    }

    static void mergeSort(int[] a) {
        if(a == null || a.length < 2)
            return;
        mergeSort(a, 0, a.length-1);
    }

    static void mergeSort(int[] a, int start, int end) {
        if(start == end)
            return;

        int mid = (end - start) /2 + start;
        mergeSort(a, start, mid);
        mergeSort(a, mid+1, end);

        int[] b = new int[end-start+1];
        int i=0, p=start, q=mid+1;
        while(p<=mid && q<=end)
            b[i++] = a[p] < a[q] ? a[p++] : a[q++];
        while(i < b.length)
            b[i++] = p == mid+1 ? a[q++] : a[p++];

        for(i=start; i<=end; i++)
            a[i] = b[i-start];
    }

    static public void main(String args[]) {
        int a[] = {3, 65, 11, 11, 8, 29, 41, 21, 4, 6, 11, 11};
        quickSort(a);
        //mergeSort(a);
        for(int i=0; i<a.length; i++)
            System.out.println(a[i]);
    }
}
