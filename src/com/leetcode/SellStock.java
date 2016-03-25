package com.leetcode;

/**
 * Created by wentaod on 3/8/16.
 */
public class SellStock {

    static public class SolutionI {
        /*
        Say you have an array for which the ith element is the price of a given stock on day i.
        If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
        * */
        public int maxProfit(int[] prices) {
            if(prices==null || prices.length<2)
                return 0;

            int min = Integer.MAX_VALUE;
            int max = 0;
            for(int price : prices) {
                if(price < min) {
                    min = price;
                }
                else {
                    if(price - min > max)
                        max = price - min;
                }
            }

            return max;
        }

        public void test() {
            int[] prices = {2, 5, 1, 8, 3, 1, 7, 3, 9, 2, 6};
            System.out.println(maxProfit(prices));
        }
    }

    static public class SolutionII {
        /*
        Say you have an array for which the ith element is the price of a given stock on day i.
        Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
        */

        int[] prices;
        Integer dpG[];
        Integer dpL[];

        public int maxProfit(int[] prices) {
            if(prices==null || prices.length<2)
                return 0;

            this.prices = prices;
            dpG = new Integer[prices.length+1];
            dpL = new Integer[prices.length+1];

            return G(prices.length);
        }

        // Global: sell at any day for first n days
        int G(int n) {
            if(n <= 1)
                return 0;
            if(dpG[n] != null)
                return dpG[n];
            dpG[n] = Math.max(G(n-1), L(n));
            return dpG[n];
        }

        // Local: last sell at day n-1 for first n days
        int L(int n) {
            if(n <= 1)
                return 0;
            if(dpL[n] != null)
                return dpL[n];

            dpL[n] = 0;
            for(int i=0; i<n-1; i++) {
                int profit = G(i) + Math.max(prices[n - 1] - prices[i], 0);
                if(dpL[n] < profit)
                    dpL[n] = profit;
            }

            return dpL[n];
        }

        public void test() {
            int[] prices = {2, 5, 1, 8, 3, 1, 7, 3, 9, 2, 6};
            System.out.println(maxProfit(prices));

            System.out.println();
            for(Integer x : dpL)
                System.out.print(x + ", ");
            System.out.println();
            for(Integer x : dpG)
                System.out.print(x + ", ");

        }
    }

    static public class SolutionII_2 {
        public int maxProfit(int[] prices) {
            if(prices == null || prices.length<2)
                return 0;

            int min = prices[0];
            int max = prices[0];
            int total = 0;
            for(int price : prices) {
                if(price >= max) {
                    max = price;
                }
                else {
                    total += max - min;
                    min = price;
                    max = price;
                }
            }

            return total + max - min;
        }

        public void test() {
            int[] prices = {2, 5, 1, 8, 3, 1, 7, 3, 9, 2, 6};
            System.out.println(maxProfit(prices));
        }
    }

    static public class SolutionIII {
        /*
        Say you have an array for which the ith element is the price of a given stock on day i.
        Design an algorithm to find the maximum profit. You may complete at most two transactions.
        */

        int[] prices;
        Integer[] p;
        Integer[] s;
        Integer[] q;
        Integer[] b;

        public int maxProfit(int[] prices) {
            if(prices == null || prices.length < 2)
                return 0;
            this.prices = prices;
            p = new Integer[prices.length + 1];
            s = new Integer[prices.length + 1];
            q = new Integer[prices.length + 1];
            b = new Integer[prices.length + 1];

            int max = 0;
            for(int i=0; i<prices.length; i++) {
                int profit = P(i) + Q(i+1);
                if(profit > max)
                    max = profit;
            }
            return max;
        }

        // max profit of 1 transaction in the range [0, i]
        int P(int i) {
            if(i<=0)
                return 0;
            if(p[i] != null)
                return p[i];
            p[i] = Math.max(P(i-1), S(i));
            return p[i];
        }

        // max profit of 1 transaction in the range [0, i] selling at prices[i].
        int S(int i) {
            if(i==0)
                return 0;
            if(s[i] != null)
                return s[i];
            s[i] = Math.max(0, S(i-1) + prices[i] - prices[i-1]);
            return s[i];
        }

        // max profit of 1 transaction in the range [i, n-1]
        int Q(int i) {
            if(i >= prices.length-1)
                return 0;
            if(q[i] != null)
                return q[i];
            q[i] = Math.max(Q(i+1), B(i));
            return q[i];
        }

        // max profit of 1 transaction in the range [i, n-1] buying at prices[i]
        int B(int i) {
            if(i >= prices.length-1)
                return 0;
            if(b[i] != null)
                return b[i];
            b[i] = Math.max(0, B(i+1) + prices[i+1] - prices[i]);
            return b[i];
        }

        public void test() {
            int[] prices = {2, 5, 1, 8, 3, 1, 7, 3, 9, 2, 6};
            System.out.println(maxProfit(prices));
        }

    }

    static public class SolutionIII_2 {
        public int maxProfit(int[] prices) {
            int[] s = new int[prices.length];
            for(int i=1; i<prices.length; i++) {
                s[i] = Math.max(0, s[i-1] + prices[i] - prices[i-1]);
            }

            int[] p = new int[prices.length+1];
            for(int i=1; i<prices.length; i++) {
                p[i] = Math.max(p[i-1], s[i]);
            }

            int[] b = new int[prices.length];
            for(int i=prices.length-2; i>=0; i--) {
                b[i] = Math.max(0, b[i+1] + prices[i+1] - prices[i]);
            }

            int[] q = new int[prices.length+1];
            for(int i=prices.length-2; i>=0; i--) {
                q[i] = Math.max(q[i+1], b[i]);
            }

            int max = 0;
            for(int i=0; i<prices.length; i++) {
                int profit = p[i] + q[i+1];
                if(profit > max)
                    max = profit;
            }
            return max;
        }

        public void test() {
            int[] prices = {2, 5, 1, 8, 3, 1, 7, 3, 9, 2, 6};
            System.out.println(maxProfit(prices));
        }
    }

    static public class SolutionIV {
        /*
        Say you have an array for which the ith element is the price of a given stock on day i.
        Design an algorithm to find the maximum profit. You may complete at most k transactions.
        */

        public int maxProfit(int k, int[] prices) {
            if(k==0 || prices.length<2)
                return 0;

            int L[] = new int[prices.length+1];
            int G_old[] = new int[prices.length+1];
            for(int j=0; j<k; j++) {
                int G[] = new int[prices.length+1];
                for (int i = 2; i <= prices.length; i++) {
                    int last = prices[i - 1] - prices[i - 2];
                    L[i] = Math.max(L[i - 1] + last, G_old[i - 2] + Math.max(0, last));
                    if (L[i] < 0)
                        L[i] = 0;
                    G[i] = Math.max(L[i], G[i - 1]);
                }
                G_old = G;
            }

            return G_old[prices.length];
        }

        public void test() {
            int k = 1;
            int[] prices = {2, 5, 1, 8, 3, 1, 7, 3, 9, 2, 6};
            System.out.println(maxProfit(k, prices));
        }
    }

    static public class SolutionV {
        /*Say you have an array for which the ith element is the price of a given stock on day i.
        Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
        You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
        After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
        */

        int[] prices;
        Integer[] dpF;
        Integer[] dpG;

        public int maxProfit(int[] prices) {
            if(prices==null || prices.length<2)
                return 0;
            this.prices = prices;
            this.dpF = new Integer[prices.length+1];
            this.dpG = new Integer[prices.length+1];
            return g(prices.length-1);
        }

        // max profit when last transaction ends at day x with 1 day cool down.
        int f(int x) {
            if(x<1)
                return 0;
            if(dpF[x] != null)
                return dpF[x];

            dpF[x] = 0;
            for(int i=0; i<x; i++) {
                if(prices[x] > prices[i]) {
                    int profit = prices[x] - prices[i] + g(i - 2);
                    if (dpF[x] < profit)
                        dpF[x] = profit;
                }
            }

            return dpF[x];
        }

        // max profit when last transaction ends at day x with 1 day cool down.
        int g(int x) {
            if(x<1)
                return 0;
            if(dpG[x] != null)
                return dpG[x];

            dpG[x] = Math.max(g(x - 1), f(x));
            return dpG[x];
        }

        public void test() {
            int[] prices = {2, 5, 1, 8, 3, 1, 7, 3, 9, 2, 6};
            System.out.println(maxProfit(prices));
        }
    }

    static public class SolutionV_2 {
        public int maxProfit(int[] prices) {
            if(prices==null || prices.length<2)
                return 0;
            int[] dpG = new int[prices.length+1]; // max profit when last transaction ends at day x with 1 day cool down.

            int max = 0;
            for(int x=1; x<prices.length; x++) {
                int maxF = 0;
                for(int i=0; i<x; i++) {
                    if(prices[x] > prices[i]) {
                        int profit = prices[x] - prices[i] + (i>=2 ? dpG[i-2] : 0);
                        if(profit > maxF)
                            maxF = profit;
                    }
                }
                dpG[x] = Math.max(dpG[x-1], maxF);
                if(max < dpG[x])
                    max = dpG[x];
            }

            return max;
        }

        public void test() {
            int[] prices = {2, 5, 1, 8, 3, 1, 7, 3, 9, 2, 6};
            System.out.println(maxProfit(prices));
        }
    }

    static public void main(String args[]) {
        SolutionV_2 sol = new SolutionV_2();
        sol.test();
    }
}
