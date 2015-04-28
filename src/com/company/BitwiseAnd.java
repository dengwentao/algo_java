package com.company;

/**
 * Created by WentaoD on 4/27/15.
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 For example, given the range [5, 7], you should return 4.

 */

class BitwiseAndSolution {
  public int rangeBitwiseAnd(int m, int n) {
    int res = m & n;
    int diff = n - m;
    if(diff<1)
      return m;
    int pow = highestPow2(diff);
    int bit = 0x1;
    for(int i=0; i<=pow; i++) {
      res = res - (res & bit);
      bit <<= 1;
    }
    return res;
  }

  // find highest pow 2 of x. x>0.
  private int highestPow2(int x) {
    int pow = 0;
    while(x>1) {
      x = x >> 1;
      pow++;
    }
    return pow;
  }

  public void test() {
    int res = rangeBitwiseAnd(200, 255);
    System.out.println(res);
  }
}

public class BitwiseAnd {
  static public void main(String args[]) {
    BitwiseAndSolution sol = new BitwiseAndSolution();
    sol.test();
  }
}


