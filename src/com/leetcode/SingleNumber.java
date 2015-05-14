package com.leetcode;
import java.util.*;

/**
 * Created by wedeng on 2/6/15.
 * Given an array of integers, every element appears three times except for one. Find that single one.

 Note:
 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

class SolutionSingle {
    public int singleNumber(int[] A) {
        if(A.length<4)
            return 0;
        int res=0;
        int count = 0;
        for(int j=0; j<Integer.SIZE; j++) {
            count = 0;
            for(int i=0; i<A.length; i++) {
                count += A[i] & 0x01;
                A[i] >>= 1;
            }
            count = count % 3;
            res += (count<<j);
        }
        return res;
    }

    public void test() {
        int[] A = {423, 89, 423, -44, 423, -546, -546, 89, 89, -546};
        System.out.println(singleNumber(A));
    }
}

public class SingleNumber {
    public static void main(String[] args) {
        SolutionSingle sol = new SolutionSingle();
        sol.test();
    }
}
