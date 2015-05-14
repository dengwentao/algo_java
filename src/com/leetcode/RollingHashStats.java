package com.leetcode;
import java.lang.String;
import java.lang.Math;
import java.util.*;

/**
 * Created by wedeng on 1/29/15.
 * Given a string, find the substring  which occurs most frequently.  This
 substring must have length between K and L, and must have no more than M
 distinct characters, assuming string has only lower case letters 'a' to 'z'.
 */

class Distinct {
    HashMap<Character, Integer> dict; //mappings between char in the window and its count.

    public Distinct() {
        dict = new HashMap<Character, Integer>(26);
    }

    public Distinct(Distinct d) {
        this.dict = new HashMap<Character, Integer>(d.dict);
    }

    public void insert(char c) {
        Integer i = dict.get(c);
        if(i==null)
            i = 0;
        dict.put(c, i+1);
    }

    public void remove(char c) {
        Integer i = dict.get(c);
        //if(i==null)
        //    throw new Exception("Remove a char not in dict.");
        if(i==1)
            dict.remove(c);
        else
            dict.put(c, i - 1);
    }

    public int count() {
        return dict.size();
    }
}


//Attention: it's the caller's responsibility to not pass the end of the input string.
class RollingHash {
    long hash; //hash value
    String input; //string to apply the hash
    int left; //window left boundary index on the input
    int right; //window right boundary index on the input
    final int base = 26;
    final long mod = Long.MAX_VALUE >> 2;
    long pow;

    Distinct dict;

    // return value instead of ascii at index.
    int valueAt(int index) {return input.charAt(index) - 'a' + 1;}

    public RollingHash(String s, int l, int r) {
        input = s;
        left = l;
        right = r;
        hash = 0L;
        dict = new Distinct();
        for(int i=left; i<=right; i++) {
            hash = hash * base % mod + valueAt(i);
            dict.insert(input.charAt(i));
        }
        pow = (long) Math.pow(base, right-left+1);
    }

    RollingHash(RollingHash h) {
        this.hash = h.hash;
        this.input = h.input;
        this.left = h.left;
        this.right = h.right;
        this.pow = h.pow;
        this.dict = new Distinct(h.dict);
    }

    public long getValue()
    {
        return hash;
    }

    //window shift right 1
    public long shiftRight()
    {
        hash = hash * base % mod + valueAt(right+1) - valueAt(left)* pow  % mod;
        dict.insert(input.charAt(right+1));
        dict.remove(input.charAt(left));
        left++;
        right++;
        return hash;
    }

    //window expand 1 right
    public long expandRight()
    {
        hash = hash * base + valueAt(right+1);
        pow *= base;
        dict.insert(input.charAt(right+1));
        right++;
        return hash;
    }

    public int getDistinct() {
        return dict.count();
    }

    public int right() {
        return right;
    }

    public int left() {
        return left;
    }

    // input hash value and decode it into a string
    public String decode(long h) {
        StringBuilder sb = new StringBuilder();
        while(h != 0) {
            sb.insert(0, (char)((h % base) + 'a' - 1));
            h /= base;
        }
        return sb.toString();
    }
}

public class RollingHashStats {

    //check boundary
    static String FindMostFreqSubStr(String input, int low, int high, int dist)
    {
        if(input.length() < low) {
            System.out.println("Input string is too short.");
            return "";
        }
        if(low > high) {
            System.out.println("low is larger than high.");
            return "";
        }
        if(dist <=0) {
            System.out.println("Distinct count is less then 1.");
            return "";
        }

        HashMap<Long, Integer> freq = new HashMap<Long, Integer>();
        RollingHash initialHash = new RollingHash(input, 0, low - 2);

        //window size from low to high
        for(int size=low; size<=high && input.length()>=size; size++) {
            System.out.println("********** Window size is now: " + size);

            initialHash.expandRight();
            RollingHash h = new RollingHash(initialHash);

            //shift window right
            while(h.right() < input.length()) {
                System.out.println("Window left: " + h.left() + " right: " + h.right() + " hash is: " + h.getValue());

                if (h.getDistinct() <= dist) {
                    Long k = h.getValue();
                    Integer v = freq.get(k);
                    if (v == null)
                        v = 0;
                    freq.put(k, v + 1);
                }
                if(h.right() == input.length()-1)
                    break;
                h.shiftRight();
            }
        }

        int max = 0;
        long key = 0;
        for(Map.Entry<Long, Integer> entry : freq.entrySet()) {
            int f = entry.getValue();
            if(f > max) {
                max = f;
                key = entry.getKey();
            }
        }

        System.out.println(key + " with frequency " + max);
        return initialHash.decode(key);
    }

    public static void main(String[] args)
    {
        String input = "hellofromhellhehell";
        int K = 2;
        int L = 4;
        int M = 2;
        String sub = FindMostFreqSubStr(input, K, L, M);
        System.out.println(sub);
    }
}
