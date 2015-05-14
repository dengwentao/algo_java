package com.leetcode;
import java.util.*;

/**
 * Created by wedeng on 2/4/15.
 * Given a list of non negative integers, arrange them such that they form the largest number.

 For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

 Note: The result may be very large, so you need to return a string instead of an integer.
 */

class SolutionLargestNumber {

    class NumString implements Comparable<NumString> {
        String str;

        NumString(Integer i) {
            str = i.toString();
        }

        public int compareTo(NumString o) {
            return (o.str+str).compareTo(str+o.str);
        }

        public String str() {
            return str;
        }
    }

    public String largestNumber(int[] num) {
        if(num.length == 0)
            return "";
        boolean all0 = true;
        for(int i : num) {
            if(i!=0) {
                all0 = false;
                break;
            }
        }
        if(all0)
            return "0";

        ArrayList<NumString> arr = new ArrayList<SolutionLargestNumber.NumString>(num.length);
        for(int i : num) {
            arr.add(new NumString(i));
        }
        Collections.sort(arr);
        StringBuilder sb = new StringBuilder();
        for(NumString s : arr) {
            System.out.println(s.str());
            sb.append(s.str());
        }
        String res = sb.toString();
        return res;
    }

    public void test() {
        int[] a = {3, 30, 31, 2, 5, 9};
        System.out.println(largestNumber(a));
    }
}

public class LargestNumber {
    public static void main(String[] args) {
        // write your code here
        SolutionLargestNumber sol = new SolutionLargestNumber();
        sol.test();
    }
}
