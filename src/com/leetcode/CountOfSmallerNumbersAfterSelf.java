package com.leetcode;
import java.util.*;

/**
 * Count of Smaller Numbers After Self
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * Created by wentaod on 4/5/16.
 */
public class CountOfSmallerNumbersAfterSelf {
    static public class Solution {

        static class BST {
            int value; // value
            int count; // count of nodes in this subtree

            BST left;
            BST right;

            public BST(int value) {
                this.value = value;
                this.count = 1;
            }

            public void print() {
                System.out.println(value + ": " + count);
                if(left != null)
                    left.print();
                else
                    System.out.println('#');
                if(right != null)
                    right.print();
                else
                    System.out.println('#');
            }

            // insert value into current subtree. return count of nodes that is smaller than the value.
            public int insert(int value) {
                this.count++;
                if(value < this.value) {
                    if(left != null)
                        return left.insert(value);
                    else {
                        left = new BST(value);
                        return 0;
                    }
                }
                else if(value > this.value) {
                    if(right != null) {
                        return count - 1 - right.count + right.insert(value); // -1 because we've done count++ above.
                    }
                    else {
                        right = new BST(value);
                        return count-1;
                    }
                }
                else
                    return left==null ? 0 : left.count;
             }
        }

        public List<Integer> countSmaller(int[] nums) {
            List<Integer> result = new LinkedList<>();
            if(nums==null || nums.length==0)
                return result;

            BST root = new BST(Integer.MAX_VALUE);
            for(int i=nums.length-1; i>=0; i--) {
                result.add(0, root.insert(nums[i]));
            }

            root.print();
            return result;
        }

        public void test() {
            int[] nums = {5, 2, 6, 1};
            System.out.println(countSmaller(nums));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
