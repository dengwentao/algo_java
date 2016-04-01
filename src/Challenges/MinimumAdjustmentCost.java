package Challenges;

/**
 * Minimum Adjustment Cost
  Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.
  If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|
  Note, you can assume each number in the array is a positive integer and not greater than 100
 E.g., Given [1,4,2,3] and target=1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal. Return 2.

 * Created by wentaod on 3/31/16.
 */
public class MinimumAdjustmentCost {

    int minAdjCost(int[] array, int target) {
        if(array==null || array.length==0 || target<0)
            return -1;

        int[][] dp = new int[array.length+1][101];
        for(int i=1; i<=array.length; i++) {
            for(int j=1; j<=100; j++) {
                int min = Integer.MAX_VALUE;
                for(int k=j-target; k<=j+target; k++) {
                    if(k<1 || k> 100)
                        continue;
                    if(min > dp[i-1][k])
                        min = dp[i-1][k];
                }

                dp[i][j] = min + Math.abs(array[i-1] - j);
                System.out.print(dp[i][j] + ",\t");
            }
            System.out.println();
        }

        int min = Integer.MAX_VALUE;
        for(int j=1; j<=100; j++)
            if(min > dp[array.length-1][j])
                min = dp[array.length-1][j];
        return min;
    }

    public void test() {
        int[] array = {1,4,2,3};
        int target = 1;
        System.out.println(minAdjCost(array, target));
    }

    static public void main(String args[]) {
        MinimumAdjustmentCost sol = new MinimumAdjustmentCost();
        sol.test();
    }
}
