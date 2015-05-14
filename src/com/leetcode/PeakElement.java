package com.leetcode;

/**
 * Created by WentaoD on 4/30/15.
 * A peak element is an element that is greater than its neighbors.
 Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 You may imagine that num[-1] = num[n] = -∞.
 For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */

class PeakElementSolution {
  public int findPeakElement(int[] nums) {
    int len = nums.length;
    if(len==0)
      return -1;
    if(len==1)
      return 0;
    if(nums[0]>nums[1])
      return 0;
    if(nums[len-1]>nums[len-2])
      return len-1;
    return findPeak(nums, 1, len-2);
  }

  private int findPeak(int[] nums, int low, int high) {
    if(low==high)
      return low;
    int mid = (high-low)/2 + low;
    if(nums[mid]>nums[mid-1] && nums[mid]>nums[mid+1])
      return mid;
    else if(nums[mid]>nums[mid-1]) {
      if (mid==low)
        return high;
      else
        return findPeak(nums, mid, high);
    }
    else
      return findPeak(nums, low, mid);
  }

  public void test() {
    int[] lst = {1,2,3,1};
    System.out.println(findPeakElement(lst));
  }
}

public class PeakElement {
  public static void main(String args[]) {
    PeakElementSolution sol = new PeakElementSolution();
    sol.test();
  }
}
