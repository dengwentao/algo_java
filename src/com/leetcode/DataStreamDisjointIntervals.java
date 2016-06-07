package com.leetcode;
import java.util.*;

/**
 * Data Stream as Disjoint Intervals
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
 For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 [1, 1]
 [1, 1], [3, 3]
 [1, 1], [3, 3], [7, 7]
 [1, 3], [7, 7]
 [1, 3], [6, 7]
 * Created by wentaod on 6/7/16.
 */
public class DataStreamDisjointIntervals {
     static class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
     }

    static public class SummaryRanges {

        static class Node {
            Interval interval;
            Node left;
            Node right;

            public Node(Interval interval) {
                this.interval = interval;
            }

            public void insert(int val) {
                if(interval.start > val) { // go left
                    if(interval.start == val + 1) {
                        interval.start -= 1;
                        // find the largest in left tree to see if we can combine
                        if(left != null) { // see if we need to combine
                            Node largest = left;
                            Node parent = null;
                            while (largest.right != null) {
                                parent = largest;
                                largest = largest.right; // find the right-most node in left tree
                            }
                            if(largest.interval.end + 1 == interval.start) { // need to combine
                                interval.start = largest.interval.start;
                                // largest node is to be removed
                                if(parent != null)
                                    parent.right = largest.left;
                                else
                                    left = left.left;
                            }
                        }
                        return;
                    }

                    if(left == null)
                        left = new Node(new Interval(val, val));
                    else
                        left.insert(val);
                } else if(val > interval.end) { // go right
                    if(interval.end + 1 == val) {
                        interval.end += 1;
                        if(right != null) {
                            Node smallest = right;
                            Node parent = null;
                            while(smallest.left != null) {
                                parent = smallest;
                                smallest = smallest.left;
                            }
                            if(smallest.interval.start == interval.end + 1) {
                                interval.end = smallest.interval.end;
                                if(parent != null)
                                    parent.left = smallest.right;
                                else
                                    right = right.right;
                            }
                        }

                        return;
                    }

                    if(right == null)
                        right = new Node(new Interval(val, val));
                    else
                        right.insert(val);
                }
            }

            public List<Interval> traverse() {
                List<Interval> result = new LinkedList<Interval>();
                if( left != null)
                    result.addAll(left.traverse());
                result.add(interval);
                if( right != null)
                    result.addAll(right.traverse());
                return result;
            }
        }

        Node root;

        public SummaryRanges() {
        }

        public void addNum(int val) {
            if(root == null)
                root = new Node(new Interval(val, val));
            else
                root.insert(val);
        }

        public List<Interval> getIntervals() {
            return root == null ? new LinkedList<Interval>() : root.traverse();
        }
    }

    public void test() {
        int[] sequence = {1, 3, 7, 2, 6};
        SummaryRanges obj = new SummaryRanges();
        for(int x : sequence) {
            obj.addNum(x);
            for(Interval intv : obj.getIntervals()) {
                System.out.print("(" + intv.start + ", " + intv.end + ") ");
            }
            System.out.println();
        }
    }

    static public void main(String args[]) {
        DataStreamDisjointIntervals sol = new DataStreamDisjointIntervals();
        sol.test();
    }

}
