package Challenges;

/**
 * Created by wentaod on 1/15/16.
 */
public class InsertionSort {

    // return the insertion index of x in sorted array a.
    static int findInsertionIndex(int[] a, int x) {
        if(a.length == 0)
            return 0;
        return findInsertionIndex(a, x, 0, a.length-1);
    }

    // find insertion index which is the first element larger than x.
    static int findInsertionIndex(int[] a, int x, int start, int end) {
        if(start ==  end)
            return a[start] >= x ? start : start + 1; // if x is larger, has to append to the tail.
        // we don't need to check if start+1 == end because this won't happen - we keep mid when going right only but mid==start instead of mid==end if there're 2 elements only.
        int mid = (end - start)/2 + start;
        if(a[mid] < x) // no need to keep mid in this case.
            return findInsertionIndex(a, x, mid+1, end);
        else if(a[mid] > x) // we have to keep mid because we are searching for the first element larger than x.
            return findInsertionIndex(a, x, start, mid);
        else
            return mid;
    }

    public static void main(String[] args) {
        System.out.println(findInsertionIndex(new int[]{3, 4, 6, 7, 12}, 13));
    }
}
