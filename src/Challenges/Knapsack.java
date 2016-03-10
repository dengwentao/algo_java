package Challenges;

import java.util.Iterator;

/**
 * Created by wentaod on 3/7/16.
 * Given a set of items, each with a weight and a value, determine the number of each item to include in a collection
 * so that the total weight is less than or equal to a given limit and the total value is as large as possible.
 * Each item can be chosen multiple times.
 */

public class Knapsack {

    int[][] arr;
    Integer[][] dp;

    public int V(int i, int w) {
        if(w < 0)
            return Integer.MIN_VALUE;
        if(i == arr.length || w == 0)
            return 0;

        if(dp[i][w] != null)
            return dp[i][w];

        dp[i][w] = Math.max(V(i+1, w), arr[i][1] + Math.max(V(i+1, w-arr[i][0]), V(i, w-arr[i][0])));
        return dp[i][w];
    }

    public void test() {
        arr = new int[][]{{3, 12}, {5, 10}, {2, 4}, {1, 3}, {9, 20}}; // {weight, value}
        int weight = 10;
        dp = new Integer[arr.length+1][weight+1];
        System.out.println(V(0, weight)); // choose weight less than 30
    }

    static public void main(String args[]) {
        Knapsack sack = new Knapsack();
        sack.test();
    }
}
