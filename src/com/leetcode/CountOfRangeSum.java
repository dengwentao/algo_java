package com.leetcode;

/**
 * 327. Count of Range Sum
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 Example:
 Given nums = [-2, 5, -1], lower = -2, upper = 2, Return 3.
 The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 * Created by wentaod on 4/13/16.
 */
public class CountOfRangeSum {
    static public class Solution {

        static class BST {
            long value; // value
            int count; // count of nodes in this subtree

            BST left;
            BST right;

            public BST(long value) {
                this.value = value;
                this.count = 1;
            }

            // insert value into current subtree.
            public void insert(long value) {
                count++;
                if(value < this.value) {
                    if(left != null)
                        left.insert(value);
                    else
                        left = new BST(value);
                }
                else if(value > this.value) {
                    if(right != null)
                        right.insert(value);
                    else
                        right = new BST(value);
                }
            }

            // return count of elements smaller than value.
            // if inclusive is true, also include those equal.
            public int getSmaller(long value, boolean inclusive) {
                if(value < this.value)
                    return left==null ? 0 : left.getSmaller(value, inclusive);
                else if(value > this.value) {
                    if(right == null)
                        return count;
                    else
                        return count - right.count + right.getSmaller(value, inclusive);
                }
                else {
                    if(inclusive)
                        return count - (right==null ? 0 : right.count);
                    else
                        return left==null ? 0 : left.count;
                }
            }
        }

        public int countRangeSum(int[] nums, int lower, int upper) {
            if(nums == null || nums.length == 0 || upper < lower) {
                return 0;
            }

            int result = 0;
            BST tree = new BST(0); // for empty array
            long sum = 0;
            for(int x : nums) {
                sum += x;
                int larger = tree.getSmaller(sum - lower, true); // sum - lower is higher boundary, here returns how many nodes are below higher boundary
                int smaller = tree.getSmaller(sum - upper, false);  // sum - upper is lower boundary, here returns how many nodes are below lower boundary
                result += larger - smaller;
                tree.insert(sum);
            }

            return result;
        }

        public void test() {
            int[] nums = {-2, 5, -1, 1};
            System.out.println(countRangeSum(nums, -2, 2));
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
