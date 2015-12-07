package com.leetcode;
import java.util.*;

/**
 * Find Median from Data Stream
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 Examples:
 [2,3,4] , the median is 3
 [2,3], the median is (2 + 3) / 2 = 2.5
 Design a data structure that supports the following two operations:
 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.
 * Created by wentaod on 12/6/15.
 */
public class MedianOfDataStream {

    static class MedianFinder {

        static class RevComp implements Comparator<Integer> {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        }

        PriorityQueue<Integer> pqMin;
        PriorityQueue<Integer> pqMax;
        int size;

        public MedianFinder() {
            pqMin = new PriorityQueue<>(1024);
            pqMax = new PriorityQueue<>(1024, new RevComp ());
            size = 0;
        }

        // Adds a number into the data structure.
        public void addNum(int num) {
            if(size == 0)
                pqMin.offer(num);
            else if(size == 1) {
                int min = pqMin.peek(); // when size is odd, always keep the extra one in pqMin.
                if(min >= num)
                    pqMax.offer(num);
                else {
                    pqMin.offer(num);
                    pqMax.offer(pqMin.poll());
                }
            }
            else {
                int min = pqMin.peek();
                int max = pqMax.peek(); // note that min >= max
                if((size % 2) == 0) { // even size
                    if (num >= min)
                        pqMin.offer(num);
                    else {
                        pqMax.offer(num);
                        pqMin.offer(pqMax.poll());
                    }
                }
                else { // odd size, pqMin.size == pqMax.size+1
                    if(num > min) {
                        pqMin.offer(num);
                        pqMax.offer(pqMin.poll());
                    }
                    else {
                        pqMax.offer(num);
                    }
                }
            }

            size++;
        }

        // Returns the median of current data stream
        public double findMedian() {
            if(size==0)
                return 0;
            if((size%2)==0)
                return (pqMax.peek() + pqMin.peek())/2.0;
            else
                return pqMin.peek();
        }
    };

    static public void main(String args[]) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        mf.addNum(-1);
        System.out.println(mf.findMedian());
    }
}
