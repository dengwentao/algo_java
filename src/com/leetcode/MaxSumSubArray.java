package com.leetcode;

/**
 * Created by wedeng on 1/22/15.
 * Input a circular list, and output the max sum.
 */

class SolutionMaxSum {
    public int maxSubArray(int[] A) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<A.length; i++)
        {
            if(sum < 0)
                sum = Math.max(sum, A[i]);
            else
                sum += A[i];

            if(sum > res)
                res = sum;
        }
        return res;
    }

    public int maxSubCircularArray(int[] A) {
        int case1 = maxSubArray(A);
        int sum = 0;
        for(int i=0; i<A.length; i++)
        {
            sum += A[i];
            A[i] = -A[i];
        }
        int case2 = sum + maxSubArray(A);
        return case1 > case2 ? case1 : case2;
    }

    public void test()
    {
        int[] a = {1,-2,3,5,-3,2};//{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubCircularArray(a));
    }
};

public class MaxSumSubArray {
    public static void main(String[] args) {
        // write your code here
        SolutionMaxSum sol = new SolutionMaxSum();
        sol.test();
    }
}

