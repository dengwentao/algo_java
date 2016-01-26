package com.leetcode;
import java.util.*;

/**
 * Insert Interval
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 You may assume that the intervals were initially sorted according to their start times.
 * Created by wentaod on 1/15/16.
 */
public class InsertAnInterval {
    static  class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public class Solution {
        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
            boolean inserted = false;
            List<Interval> result = new ArrayList<>();

            for(int i=0; i<intervals.size(); i++) {
                if(!inserted && i<intervals.size() && intervals.get(i).start > newInterval.start) {
                    appendInterval(result, newInterval);
                    inserted = true;
                }
                appendInterval(result, intervals.get(i));
            }

            if(!inserted)
                appendInterval(result, newInterval);

            return result;
        }

        // append new interval into an ordered exisiting intervals list, combine when necessary.
        void appendInterval(List<Interval> intervals, Interval newInterval) {
            if(intervals.isEmpty())
                intervals.add(newInterval);
            else {
                Interval last = intervals.get(intervals.size()-1);
                if(last.end < newInterval.start)
                    intervals.add(newInterval);
                else {
                    last.start = Math.min(newInterval.start, last.start);
                    last.end = Math.max(newInterval.end, last.end);
                }
            }
        }
    }
}
