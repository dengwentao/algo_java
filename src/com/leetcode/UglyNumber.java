package com.leetcode;

import java.util.*;

/**
 * Ugly Number II
 * Created by wentaod on 8/31/15.
 * Write a program to find the n-th ugly number.
 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 Note that 1 is typically treated as an ugly number.
 */
public class UglyNumber {
    static public class SolutionII {

        List<Long> uglyArray = new ArrayList<>(); // result array
        List<Queue<Long>> queues = new LinkedList<Queue<Long>>(); // queues to merge
        // numbers [startIndex, endIndex] in uglyArray is seeds to generate new ugly numbers.
        int startIndex;
        int endIndex;
        int size;  // size of uglyArray

        class Pair implements Comparable<Pair> {
            Queue<Long> queue; // the queue from which this value comes.
            long value;
            Pair(Queue<Long> queue, long value) {
                this.queue = queue;
                this.value = value;
            }

            public int compareTo(Pair p) {
                return (int)(this.value - p.value);
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        public SolutionII() {
            int seeds[] = {1, 2, 3, 4, 5, 6, 8, 9, 10, 12};
            for(long  i: seeds) {
                uglyArray.add(i);
                size++;
            }
            startIndex = 2;
            endIndex = 6;
        }

        public int nthUglyNumber(int n) {
            while(size < n)
            {
                generateUglyNumber(n);
            }
            long l = uglyArray.get(n-1);
            return (int)l;
        }

        // generate ugly number by multiplying 2/3/5 on numbers [startIndex, endIndex] in uglyArray.
        // stop if array size reaches n.
        private void generateUglyNumber(int n) {
            Iterator<Queue<Long>> it = queues.listIterator();
            while(it.hasNext()) {
                Queue<Long> q = it.next();
                if(q.isEmpty())
                    it.remove();
            }

            Queue<Long> ugly2 = generate(2);
            pq.add(new Pair(ugly2, ugly2.poll()));
            queues.add(ugly2);
            Queue<Long> ugly3 = generate(3);
            pq.add(new Pair(ugly3, ugly3.poll()));
            queues.add(ugly3);
            Queue<Long> ugly5 = generate(5);
            pq.add(new Pair(ugly5, ugly5.poll()));
            queues.add(ugly5);

            multiMerge(n);
        }

        // generate ugly number by multiplying x on numbers [startIndex, endIndex] in uglyArray.
        private Queue<Long> generate(int x) {
            Queue<Long> ugly = new LinkedList<>();
            for(int i=startIndex; i<=endIndex; i++) {
                ugly.add(x * uglyArray.get(i));
            }
            return ugly;
        }

        // multi-way merge sort on queues until size reaches n or a new dock number is met.
        // a dock number is power of 2, i.e., uglyArray[endIndex] * 2.
        private void multiMerge(int n) {
            while(pq.size() > 0) {
                Pair p = pq.poll();
                if(p.value > uglyArray.get(size-1)) {
                    uglyArray.add(p.value);
                    size++;
                    //System.out.println("" + size + " -- " + p.value);
                }

                // If q is empty, simply skip it without deleting it, because it won't appear on the priority queue any more.
                Queue<Long> q = p.queue;
                if(q.size() > 0)
                    pq.add(new Pair(p.queue, q.poll()));

                // we stop a generation in power of 2.
                if(p.value == uglyArray.get(endIndex) * 2) {
                    startIndex = endIndex + 1;
                    endIndex = size - 1;
                    return;
                }
            }
        }

        public void test() {
            System.out.println(nthUglyNumber(40));
        }
    }

    static public class Solution {

        public int nthUglyNumber(int n) {
            List<Integer> ugly = new ArrayList<>();
            ugly.add(1);
            int i2=0, i3=0, i5=0;
            int u2=2, u3=3, u5=5;
            for(int i=1; i<n; i++) {
                int u = min(u2, u3, u5);
                ugly.add(u);
                if (u == u2) {
                    i2++;
                    u2 = ugly.get(i2) * 2;
                }
                if (u == u3) {
                    i3++;
                    u3 = ugly.get(i3) * 3;
                }
                if  (u == u5) {
                    i5++;
                    u5 = ugly.get(i5) * 5;
                }
            }

            return ugly.get(n-1);
        }

        private int min(int a, int b, int c) {
            return Math.min(a, Math.min(b, c));
        }

        public void test() {
            //System.out.println(nthUglyNumber(0));
            System.out.println(nthUglyNumber(1));
            //System.out.println(nthUglyNumber(13));
            //System.out.println(nthUglyNumber(130));

            //System.out.println(nthUglyNumber(14));
            //System.out.println(nthUglyNumber(15));
            //System.out.println(nthUglyNumber(37));
            //System.out.println(nthUglyNumber(38));
            //System.out.println(nthUglyNumber(39));
            System.out.println(nthUglyNumber(40));
            //System.out.println(nthUglyNumber(1700));
        }
    }

        public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
