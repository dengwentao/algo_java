package com.company;

/**
 * Created by wedeng on 2/1/15.
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.
    1 transaction is a buy and a later sell.
 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

class Solution_1 {
    //allow 1 transaction only
    public int maxProfit_1(int[] prices) {
        if(prices.length < 2)
            return 0; //no way
        int profit = 0;
        int min = prices[0];
        int max = 0;
        for(int p : prices) {
            if(p < min) {
                min = p;
            }
            else {
                profit = p - min;
                if(max < profit)
                    max = profit;
            }
        }
        return max;
    }

    //allow any number of transactions
    public int maxProfit_2(int[] prices) {
        if(prices.length < 2)
            return 0; //no way
        int sum = 0;
        int valley = prices[0];
        int peak = prices[0];
        boolean up = false;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] > prices[i-1]) {
                if(up==false)
                    valley = prices[i-1];
                up = true;
            }
            else if(prices[i] < prices[i-1]) {
                if(up==true) {
                    peak = prices[i - 1];
                    sum += peak - valley;
                }
                up = false;
            }
        }
        if(up==true)
            sum += prices[prices.length-1] - valley;

        return sum;
    }

    //allow at most 2 transactions.
    public int maxProfit_3(int[] prices) {
        return 0;
    }

    public void test() {
        int[] prices = {3, 2, 1};
        int profit = maxProfit_3(prices);
        System.out.println(profit);
    }
}

public class SellStocks {
    public static void main(String[] args) {
        Solution_1 sol = new Solution_1();
        sol.test();
    }
}

