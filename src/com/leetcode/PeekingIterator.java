package com.leetcode;
import java.util.*;

/**
 * Peeking Iterator
 * Created by wentaod on 10/20/15.
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 */
public class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> it;
    int next;
    boolean peeking;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        it = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(peeking)
            return next;
        next = it.next();
        peeking = true;
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(peeking) {
            peeking = false;
            return next;
        }
        return it.next();
    }

    @Override
    public boolean hasNext() {
        if(peeking)
            return true;
        return it.hasNext();
    }

    @Override
    public void remove() {

    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        PeekingIterator it = new PeekingIterator(list.iterator());
        //while(it.hasNext()) {
        //    System.out.println(it.next());
        //}
        System.out.println(it.hasNext());
        System.out.println(it.peek());
        System.out.println(it.peek());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.peek());
        System.out.println(it.peek());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
    }
}

