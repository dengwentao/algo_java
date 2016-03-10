package Challenges;

/**
 * Created by wentaod on 3/7/16.
 Largest Sum of K Sub-Arrays
 Given int array of size N, pick K sub-arrays so that the sum of these K sub-arrays has max value. E.g., array is {3, -2, 8, -9, 1, 3} and K is 3, we should return 15.
 Empty subarray is not allowed.
 */
public class MaxSumSubArrays {

    int[] arr;
    Integer[][] dpL; // local dp table
    Integer[][] dpG; // global dp table

    // Global: sum of first n elements in k subarrays, NO need to end at a[n-1].
    int G(int n, int k) {
        if(k == 0)
            return 0;
        if(n == 0)
            return Integer.MIN_VALUE; // invalid situation if choosing k subarrays in 0 elements.
        if(dpG[n][k] != null)
            return dpG[n][k];

        dpG[n][k] = Math.max(G(n-1, k), L(n, k));
        return dpG[n][k];
    }

    // Local: sum of first n elements in k subarrays, need to end at a[n-1]
    int L(int n, int k) {
        if(k == 0)
            return 0;
        if(n == 0)
            return Integer.MIN_VALUE; // invalid situation if choosing k subarrays in 0 elements.
        if(dpL[n][k] != null)
            return dpL[n][k];

        dpL[n][k] = arr[n-1] + Math.max(G(n-1, k-1), L(n-1, k));
        return dpL[n][k];
    }

    public void test() {
        arr = new int[] {3, -2, 5, -9, 1, 3, -10, 5};
        int k = 3;
        dpL = new Integer[arr.length+1][k+1];
        dpG = new Integer[arr.length+1][k+1];

        System.out.println(G(arr.length, k));
    }

    public static void main(String args[]) {
        MaxSumSubArrays sol = new MaxSumSubArrays();
        sol.test();
    }
}
