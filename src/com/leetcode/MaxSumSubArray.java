package com.leetcode;

/**
 * Created by wedeng on 1/22/15.
 * Input a circular list, and output the max sum.
 */

class SolutionMaxSum {
    public int maxSubArray(int[] A) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<A.length; i++)
        {
            if(sum < 0)
                sum = Math.max(sum, A[i]);
            else
                sum += A[i];

            if(sum > max)
                max = sum;
        }
        return max;
    }

    public int maxSubArrayV2(int[] A) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<A.length; i++) {
            sum += A[i];
            if(sum < 0)
                sum = 0;
            else {
                if(max < sum)
                    max = sum;
            }
        }
        return max;
    }

    // should return negative if all neg.
    public int maxSubArrayV3(int[] A) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<A.length; i++) {
            sum += A[i];
            if(sum < 0) {
                sum = 0;
                max = Math.max(A[i], max);
            }
            else {
                if(max < sum)
                    max = sum;
            }
        }
        return max;
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
        System.out.println(maxSubArray(a));
        System.out.println(maxSubArrayV2(a));
        System.out.println(maxSubArrayV3(a));
        System.out.println(maxSubCircularArray(a));

        int[] aa = {-4,-2,-3,-5,-3,-2};
        System.out.println(maxSubArrayV3(aa));
    }
};

public class MaxSumSubArray {
    public static void main(String[] args) {
        // write your code here
        SolutionMaxSum sol = new SolutionMaxSum();
        sol.test();
    }
}

