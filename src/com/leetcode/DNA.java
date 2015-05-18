package com.leetcode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by WentaoD on 5/18/15.
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 */
class DNASolution {

  class RollingHash {
    private String seq;
    private int start;
    private int end;
    private int hash;

    //start and end are inclusive.
    public RollingHash(String seq, int start, int end) {
      this.seq = seq;
      this.start = start;
      this.end = end;
      for(int i=start; i<=end; i++) {
        hash = hash*4 + translate(seq.charAt(i));
      }
    }

    private int translate(char c) {
      switch(c) {
        case 'A':
          return 0;
        case 'C':
          return 1;
        case 'G':
          return 2;
        case 'T':
          return 3;
        default:
          System.out.println("Wrong DNA: "+c);
          return -1;
      }
    }

    // slide the window right 1 step.
    public void step() {
      hash -= translate(seq.charAt(start)) * Math.pow(4, 9);
      start++;
      end++;
      hash = hash*4 + translate(seq.charAt(end));
      //System.out.println(getWindow() + " : " + getValue());
    }

    public int getValue() {
      return hash;
    }

    public String getWindow() {
      return seq.substring(start, end+1);
    }
  }

  public List<String> findRepeatedDnaSequences(String s) {
    List<String> result = new ArrayList<>();
    if(s.length()<=10)
      return result;

    HashSet<Integer> keys = new HashSet<>();
    HashSet<String> values = new HashSet<>();
    RollingHash hash = new RollingHash(s, 0, 9);
    keys.add(hash.getValue());

    for(int i=0; i<s.length()-10; i++) {
      hash.step();
      if(keys.contains(hash.getValue()))
        values.add(hash.getWindow());
      else
        keys.add(hash.getValue());
    }

    for(String str : values) {
      result.add(str);
    }
    return result;
  }

  public void test() {
    String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
    for(String dna : findRepeatedDnaSequences(s))
      System.out.println(dna);
  }
}

public class DNA {
  public static void main(String args[]) {
    DNASolution sol = new DNASolution();
    sol.test();
  }
}
