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
            if(intervals.isEmpty()) {
                intervals.add(newInterval);
                return intervals;
            }

            int index = findInsertionIndex(intervals, newInterval, 0, intervals.size()-1);

            List<Interval> result = new ArrayList<>();
            for(int i=0; i<intervals.size(); i++) {
                if(i ==  index)
                    appendInterval(result, newInterval);
                appendInterval(result, intervals.get(i));
            }

            if(index == intervals.size())
                appendInterval(result, newInterval);

            return result;
        }

        // do a bi-search to find the index of the new interval where it should be inserted into the ordered list
        int findInsertionIndex(List<Interval> intervals, Interval newInterval, int start, int end) {
            if(start ==  end) {
                return newInterval.start <= intervals.get(start).start ? start : start+1;
            }
            int mid = (end-start)/2 + start;
            int midStart = intervals.get(mid).start;
            if(newInterval.start > midStart)
                return findInsertionIndex(intervals, newInterval, mid+1, end);
            else if(newInterval.start < midStart)
                return findInsertionIndex(intervals, newInterval, start, mid);
            else
                return mid;
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
