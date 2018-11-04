package com.leetcode;
import java.util.*;

/**
 * Exam Room
 * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
 When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.
 If there are multiple such seats, they sit in the seat with the lowest number.
 (Also, if no one is in the room, then the student sits at seat number 0.)
 Return a class ExamRoom(int N) that exposes two functions:
 - ExamRoom.seat() returning an int representing what seat the student sat in
 - ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.
 It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.
 1. 1 <= N <= 10^9
 2. ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
 3. Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 * Created by wd186013 on 7/31/18.
 */

public class ExamRoom {

    static class Segment implements Comparable<Segment> {
        Integer start;
        Integer end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Segment other) {
            if ((this.end - this.start) / 2 == (other.end - other.start) / 2)
                return this.start - other.start;
            else
                return (other.end - other.start) / 2 - (this.end - this.start) / 2;
        }
    }

    PriorityQueue<Segment> queue;
    int head;
    int tail;
    int size;

    public ExamRoom(int N) {
        size = N;
        head = N - 1;
        tail = N - 1;
        queue = new PriorityQueue<>();
        Segment s = new Segment(0, N - 1);
        queue.offer(s);
    }

    public int seat() {
        Segment s = queue.peek();
        if (head > 0 && head >= (s.end - s.start) / 2) {
            if (tail > head) {
                tail = 0;
                return size - 1;
            }
            head = 0;
            return 0;
        } else if (tail > (s.end - s.start) / 2) {
            tail = 0;
            return size - 1;
        } else {
            queue.poll();
            int mid = (s.end + s.start) / 2;
            queue.offer(new Segment(s.start, mid));
            queue.offer(new Segment(mid, s.end));
            return mid;
        }
    }

    public void leave(int p) {
        Iterator<Segment> it = queue.iterator();
        Segment s1 = null, s2 = null;
        while (it.hasNext()) {
            Segment s = it.next();
            if (s.start == p)
                s2 = s; // p is start of s2
            if (s.end == p)
                s1 = s; // p is end of s1
        }

        if (p == 0) {
            head = s2.end;
        } else if (p == size - 1) {
            tail = size - 1 - s1.start;
        } else {
            queue.remove(s1);
            queue.remove(s2);
            queue.offer(new Segment(s1.start, s2.end));
        }
    }

    public static void main(String[] args) {
        ExamRoom r = new ExamRoom(8);
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        r.leave(0);
        r.leave(7);
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        /*
        ExamRoom r = new ExamRoom(10);
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        r.leave(0);
        r.leave(4);
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        System.out.println(r.seat());
        r.leave(0);
        r.leave(4);
        System.out.println(r.seat());
        System.out.println(r.seat());
        r.leave(7);
        System.out.println(r.seat());
        r.leave(3);
        System.out.println(r.seat());
        r.leave(3);
        System.out.println(r.seat());
        r.leave(9);
        System.out.println(r.seat());
        r.leave(0);
        r.leave(8);
        System.out.println(r.seat());
        System.out.println(r.seat());
        r.leave(0);
        r.leave(8);
        System.out.println(r.seat());
        System.out.println(r.seat());
        r.leave(2);*/


    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */