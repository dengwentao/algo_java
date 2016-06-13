package com.leetcode;
import java.util.*;

/**
 * Russian Doll Envelopes
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 What is the maximum number of envelopes can you Russian doll? (put one inside other)
 Example:
 Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * Created by wentaod on 6/13/16.
 */
public class RussianDollEnvelopes {
    static public class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes == null || envelopes.length == 0)
                return 0;

            return LIS(sortedOtherwise(envelopes));
        }

        // sort envelopes according to x
        // two envelops may have the same x, in this case, put y in list and sort.
        Map<Integer, List<Integer>> sortedOtherwise(final int[][] envelopes) {
            Map<Integer, List<Integer>> map = new TreeMap<>(); // map x to a list of y's, sort against x.
            for (int[] env : envelopes) {
                if (map.get(env[0]) == null)
                    map.put(env[0], new ArrayList<Integer>());
                map.get(env[0]).add(env[1]);
            }

            for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                Collections.sort(entry.getValue());
            }

            return map;
        }

        // longest increasing subsequence
        int LIS(Map<Integer, List<Integer>> map) {
            List<Integer> lis = new ArrayList<>();
            lis.add(0);

            for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                for (int i=entry.getValue().size()-1; i>=0; i--) { // has to be from larger to smaller
                    int x = entry.getValue().get(i);
                    int pos = findFirstLarger(x, lis);
                    if (pos == -1) {
                        lis.add(x);
                    } else {
                        lis.set(pos, x);
                    }
                }
            }

            return lis.size() - 1;
        }

        // find index of element that is first larger than or equal to x
        int findFirstLarger(int x, List<Integer> lis) {
            int start = 0;
            int end = lis.size() - 1;
            while (start <= end) {
                int mid = (end - start) / 2 + start;
                if (lis.get(mid) < x) {
                    start = mid + 1;
                } else if (lis.get(mid) > x) {
                    if (mid >= 1 && lis.get(mid - 1) < x) {
                        return mid;
                    }
                    end = mid;
                } else {
                    return mid;
                }
            }
            return -1;
        }

        public void test() {
            int[][] envelops = {{3, 5}, {2, 7}, {3, 1}, {4, 4}, {3, 3}, {1, 2}, {5, 6}};
            System.out.println(maxEnvelopes(envelops));
        }
    }

    static public void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
