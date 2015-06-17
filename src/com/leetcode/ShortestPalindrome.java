package com.leetcode;

import java.util.HashSet;

/**
 * Created by WentaoD on 6/16/15.
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 For example:
 Given "aacecaaa", return "aaacecaaa".
 Given "abcd", return "dcbabcd".
 */

class Solution {

  class RollingHash {
    long hash; //hash value
    String input; //string to apply the hash
    int len; // window length
    final int base = 101;
    long pow;

    // return value instead of ascii at index.
    int valueAt(int index) {return input.charAt(index) - 'a' + 1;}

    public RollingHash(String s) {
      input = s;
      len = 0;
      hash = 0L;
      pow = 1L;
    }

    //window expand 1 to the right. Pow from left to right, high to low.
    public long expandRight()
    {
      len++;
      hash = hash * base + valueAt(len-1);
      return hash;
    }

    //window expand 1 to the right. Pow from left to right, low to high.
    public long expandRightRev() {
      len++;
      hash = hash + valueAt(len-1) * pow;
      pow = pow * base;
      return hash;
    }

  }

  public String shortestPalindrome(String s) {
    if(s.length()<2)
      return s;

    HashSet<Long> set = new HashSet<>();

    RollingHash front = new RollingHash(s);
    for(int i=0; i<s.length(); i++) {
      Long l = front.expandRight();
      //System.out.println(i + " : " + l);
      set.add(l);
    }

    RollingHash rev = new RollingHash(s);
    int index = 0;
    for(int i=0; i<s.length(); i++) {
      Long l = rev.expandRightRev();
      //System.out.println(i + " : " + l);
      if(set.contains(l))
        index = i;
    }
    //System.out.println(index);

    return new StringBuilder(s.substring(index+1)).reverse().toString() + s;
  }

  public void test() {
    System.out.println(shortestPalindrome("aacecaaa"));
    System.out.println(shortestPalindrome("abcd"));
    System.out.println(shortestPalindrome("aaaaaaaaaaaaaaaaaaaaaa"));
    System.out.println(shortestPalindrome("a"));
    System.out.println(shortestPalindrome(""));
  }
}

public class ShortestPalindrome {
  public static void main(String args[]) {
    Solution sol = new Solution();
    sol.test();
  }
}


