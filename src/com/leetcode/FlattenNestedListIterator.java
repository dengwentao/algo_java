package com.leetcode;
import java.util.*;

/**
 * Flatten Nested List Iterator
 * Given a nested list of integers, implement an iterator to flatten it.
 Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 Example 1:
 Given the list [[1,1],2,[1,1]],
 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * Created by wentaod on 5/12/16.
 */
public class FlattenNestedListIterator {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
     }

    static class NestInt implements NestedInteger {
        List<NestedInteger> l;
        Integer i;

        public NestInt(int x) {
            i = x;
        }

        public NestInt(List<NestedInteger> l) {
            this.l = l;
        }

        public boolean isInteger() {
            return l == null;
        }

        public Integer getInteger() {
            return i;
        }

        public List<NestedInteger> getList() {
            return l;
        }

        public void print() {
            if (i != null)
                System.out.print(" " + i + " ");
            if (l != null) {
                System.out.print(" [ ");
                for (NestedInteger n : l) {
                    ((NestInt)n).print();
                }
                System.out.print(" ] ");
            }
        }
    }

    static public class NestedIterator implements Iterator<Integer> {

        ListIterator<NestedInteger> it; // point to current level list
        NestedIterator lowerIt; // point to lower level list
        Integer next; // next Int to return

        public NestedIterator(List<NestedInteger> nestedList) {
            it = nestedList.listIterator();
        }

        @Override
        public Integer next() {
            return next;
        }

        @Override
        public boolean hasNext() {
            if (lowerIt == null) { // not in lower level
                if (it.hasNext()) {
                    NestedInteger nestInt = it.next();
                    if (nestInt.isInteger()) {
                        next = nestInt.getInteger();
                        return true;
                    } else {
                        lowerIt = new NestedIterator(nestInt.getList());
                        if (lowerIt.hasNext()) {
                            next = lowerIt.next();
                            return true;
                        } else {
                            lowerIt = null;
                            return hasNext(); // if no more in lower level, this level should move on.
                        }
                    }
                } else { // no more in this level
                    return false;
                }
            } else { // in lower level
                if (lowerIt.hasNext()) {
                    next = lowerIt.next();
                    return true;
                } else {
                    lowerIt = null;
                    return hasNext(); // if no more in lower level, go back to this level.
                }
            }
        }
    }


    static public void main(String args[]) {
        List<NestedInteger> nestedList = new ArrayList<>();
/*
        List<NestedInteger> nestedList1 = new ArrayList<>();
        nestedList1.add(new NestInt(1));
        nestedList1.add(new NestInt(2));
        nestedList.add(new NestInt(nestedList1));
        nestedList.add(new NestInt(3));
        List<NestedInteger> nestedList3 = new ArrayList<>();
        nestedList.add(new NestInt(nestedList3));
        List<NestedInteger> nestedList2 = new ArrayList<>();
        nestedList2.add(new NestInt(4));
        nestedList2.add(new NestInt(5));
        nestedList.add(new NestInt(nestedList2));
        nestedList.add(new NestInt(nestedList3));
*/

        List<NestedInteger> nestedList2 = new ArrayList<>();
        nestedList2.add(new NestInt(new ArrayList<NestedInteger>()));
        List<NestedInteger> nestedList3 = new ArrayList<>();
        nestedList3.add(new NestInt(nestedList2));
        nestedList.add(new NestInt(nestedList3));

        new NestInt(nestedList).print();
        System.out.println();

        NestedIterator i = new NestedIterator(nestedList);
        //for (int x=0; x<10; x++)
        while (i.hasNext())
            System.out.println(i.next());

    }

}
