package Challenges;
import java.util.*;

/**
 * Created by wentaod on 10/28/15.
 * Given an array, you can only pick elements that are not adjacent to each other. Find a sub-sequence that has max sum.
 */
public class MaxSumOfInadjacent {

    int[] array;
    int[] dp;

    public int pickMax(int[] input) {
        if(input == null || input.length == 0)
            return 0;

        dp = new int[input.length];
        Arrays.fill(dp, Integer.MIN_VALUE);

        array = input;
        return pickMax(0);
    }

    // return the result when input array starting from index.
    private int pickMax(int index) {
        if(index >= array.length)
            return 0;

        if(dp[index] != Integer.MIN_VALUE)
            return dp[index];

        int pickFirst = array[index] + pickMax(index + 2);
        int noFirst = pickMax(index + 1);
        dp[index] = Math.max(pickFirst, noFirst);
        return dp[index];
    }

    static public void main(String args[]) {
        MaxSumOfInadjacent sol = new MaxSumOfInadjacent();
        int[] arr = {3, -5, -2, 6, -1, 0, 3, 13, 9, -1};
        int sum = sol.pickMax(arr);
        System.out.println(sum);
    }
}
