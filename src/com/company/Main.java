package com.company;
import java.lang.String;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        LinkedList<Integer> l = new LinkedList(Arrays.asList(1, 4, 13, 24));
        int v = 7;
        if(l.getFirst() >= v)
            l.addFirst(v);
        else if(l.getLast() <= v)
            l.addLast(v);
        else
        {
            for (ListIterator<Integer> it = l.listIterator(); it.hasNext(); ) {
                int i = it.next();
                if (i > v) {
                    it.previous();
                    it.add(v);
                    break;
                }
            }
        }

        for(int x : l)
        {
            System.out.println(x);
        }
    }
}
