package com.leetcode;
import java.util.*;

/**
 * Created by WentaoD on 5/6/15.
 * Say you have an array for which the ith element is the price of a given stock on day i.
 Design an algorithm to find the maximum profit. You may complete at most k transactions.
 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

class StockSolution4 {
  private int[] slots;
  private int[][] prof_single;
  private int[][] prf;

  // max profit with a single transaction in slots range [start, end]
  private int Q(int start, int end) {
    if(end-start<1 || start>=slots.length-1 || start<0)
      return 0;
    return prof_single[start][end];
  }

  //initialize 2D array prof_single[][].
  //Each cell[i][j] is the profit of 1 transaction in slots range [i, j]
  private void initQ() {
    prof_single = new int[slots.length][slots.length];
    for(int start=0; start<prof_single.length; start++) {
      int profit = 0;
      int min = slots[start];
      for(int end=start; end<prof_single.length; end++) {
        if(slots[end]<=min)
          min = slots[end];
        else {
          int  p = slots[end] - min;
          if(p > profit)
            profit = p;
        }
        prof_single[start][end] = profit;
      }
    }
  }

  //max profit with m transactions in first n slots
  private int P(int m, int n) {
    if(n<2 || m<1)
      return 0;
    if(prf[m][n]!=-1)
      return prf[m][n];

    int max = 0;
    for(int x = 0; x<=n; x++) {
      int v = P(m-1, x) + Q(x, n-1);
      if(v > max)
        max = v;
    }

    prf[m][n] = max;
    return max;
  }

  public int maxProfit(int k, int[] prices) {
    if(k==0 || prices.length<2)
      return 0;

    //If you can make unlimited transactions, then another algorithm is faster!
    if(k>=prices.length/2)
      return maxProfit2(prices);

    slots = prices;
    initQ();
    prf = new int[k+1][prices.length+1];
    for(int i=0; i<prf.length; i++)
      Arrays.fill(prf[i], -1);

    return P(k, prices.length);
  }

  //Assuming you can make unlimited transactions, return the max profit.
  public int maxProfit2(int[] prices) {
    if(prices.length < 2)
      return 0;

    int profit = 0;
    int min = prices[0];
    int max = prices[0];
    for(int i=0; i<prices.length; i++) {
      if(prices[i] > max)
        max = prices[i];
      else {
        profit += max - min;
        min = prices[i];
        max = prices[i];
      }
    }
    if(max==prices[prices.length-1])
      profit += max - min;
    return profit;
  }

  public void test() {
    int[] prices = {4, 6, 1, 7, 2, 6, 1, 2};
    int k = 3;
    System.out.println(maxProfit(k, prices));
    //for(int i=0; i<=prices.length; i++)
    //  System.out.println(Q(i));
    //for(int i=0; i<prf.length; i++) {
    //  for (int j = 0; j < prf[0].length; j++)
    //    System.out.format("%s\t", prf[i][j]);
    //  System.out.println();
    //}
  }
}

///////////////////////////////////////////////////

class StockSolution4_2 {

  //at most k transactions
  public int maxProfit(int k, int[] prices) {
    int i = prices.length;
    if(i<2 || k<1)
      return 0;

    //If you can make unlimited transactions, then another algorithm is faster!
    if(k>=prices.length/2)
      return maxProfit2(prices);

    int[][] P = new int[k+1][i];
    int[][] E = new int[k+1][i];
    for(int x=0; x<k+1; x++) {
      Arrays.fill(P[x], 0);
      Arrays.fill(E[x], 0);
    }

    for(int x=1; x<k+1; x++) { //transaction
      for(int y=1; y<i; y++) { //day
        int diff = prices[y] - prices[y-1];
        E[x][y] = Math.max(P[x-1][y-1] + Math.max(0, diff), E[x][y-1] + diff);
        P[x][y] = Math.max(P[x][y-1], E[x][y]);
      }
    }
    return P[k][i-1];
  }

  //Assuming you can make unlimited transactions, return the max profit.
  public int maxProfit2(int[] prices) {
    if(prices.length < 2)
      return 0;

    int profit = 0;
    int min = prices[0];
    int max = prices[0];
    for(int i=0; i<prices.length; i++) {
      if(prices[i] > max)
        max = prices[i];
      else {
        profit += max - min;
        min = prices[i];
        max = prices[i];
      }
    }
    if(max==prices[prices.length-1])
      profit += max - min;
    return profit;
  }

  public void test() {
    int[] prices = {4, 6, 1, 7, 2, 6, 1, 2};
    int k = 3;
    System.out.println(maxProfit(k, prices));
    //for(int i=0; i<=prices.length; i++)
    //  System.out.println(Q(i));
    //for(int i=0; i<prf.length; i++) {
    //  for (int j = 0; j < prf[0].length; j++)
    //    System.out.format("%s\t", prf[i][j]);
    //  System.out.println();
    //}
  }
}

public class SellStocs_4 {
  public static void main(String args[]) {
    StockSolution4_2 sol = new StockSolution4_2();
    sol.test();
  }
}


