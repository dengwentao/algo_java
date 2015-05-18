package com.leetcode;

/**
 * Created by WentaoD on 5/18/15.
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 */

class MinSizeSolution {
  public int minSubArrayLen(int s, int[] nums) {
    if(nums.length==0)
      return 0;
    int min = Integer.MAX_VALUE;
    int sum = 0;
    int len = 0;

    for(int end=0, start=0; end<nums.length; end++) {
      sum += nums[end];
      len++;
      if(sum < s)
        continue;
      while(start<end && sum - nums[start] >= s) {
        sum -= nums[start];
        start++;
        len--;
      }
      if(sum>=s && len<min)
        min = len;
    }
    return min < Integer.MAX_VALUE ? min : 0;
  }

  public void test() {
    int s = 5;
    int[] array = {2,3,1,1,1,1,1};
    System.out.println(minSubArrayLen(s, array));
  }
}

public class MinSizeSubArraySum {
  public static void main(String args[]) {
    MinSizeSolution sol = new MinSizeSolution();
    sol.test();
  }
}
