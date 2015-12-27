package com.leetcode;
import java.util.*;

/**
 * Merge Intervals
 * Given a collection of intervals, merge all overlapping intervals.
 * Created by wentaod on 12/19/15.
 */
public class MergeIntervals {

    static public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
        public void print() {
            System.out.print("(" + start + ", " + end + "), ");
        }
    }

    static public class Solution {

        public List<Interval> merge(List<Interval> intervals) {
            if(intervals == null || intervals.size() < 2)
                return intervals;

            ListIterator<Interval> it = intervals.listIterator();
            while(it.hasNext()) {
                Interval intv = it.next();
                if(intv.start == intv.end)
                    it.remove();
                else if(intv.start > intv.end) {
                    int temp = intv.start;
                    intv.start = intv.end;
                    intv.end = temp;
                }
            }
            if(intervals.size() < 2)
                return intervals;

            Collections.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval intv1, Interval intv2) {
                    return intv1.start - intv2.start;
                }
            });

            List<Interval> result = new ArrayList<>();
            result.add(intervals.get(0));
            for(int i=1; i<intervals.size(); i++) {
                Interval intv1 = result.get(result.size()-1);
                Interval intv2 = intervals.get(i);
                if(intv2.start <= intv1.end) {  // connected, maybe fully covered
                    intv1.end = Math.max(intv1.end, intv2.end);
                }
                else  // disconnected
                    result.add(intv2);
            }
            return result;
        }

        public void test() {
            List<Interval> intervals = new LinkedList<>();
            intervals.add(new Interval(2, 6));
            intervals.add(new Interval(1, 3));
            intervals.add(new Interval(15, 18));
            intervals.add(new Interval(0, 0));
            intervals.add(new Interval(5, 3));
            intervals.add(new Interval(8, 10));
            intervals = merge(intervals);
            for(Interval interval : intervals) {
                (interval).print();
            }
        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
