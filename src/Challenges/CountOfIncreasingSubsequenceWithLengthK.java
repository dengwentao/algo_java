package Challenges;

/**
 * Count Of Increasing Subsequence With Length K
 *  Given an array of integers, find out number of ways which you can select increasing subsequences of length K(K<=n).e.g. array is 1 4 6 2 5 & K=3 then
 the answer is : 1 4 5, 1 2 5,1 4 6, so the count is 3.
 * Created by wentaod on 2/2/16.
 */
public class CountOfIncreasingSubsequenceWithLengthK {

    int[][] dp; // dp[n][k] is count of increasing sequence ending at array[n] and of length k.
    int[] array;

    int ISK(int[] arr, int k) {
        this.dp = new int[arr.length][k+1];
        this.array = arr;
        int sum = 0;
        for(int i=0; i<arr.length; i++)
            sum += T(i, k);

        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<=k; j++)
                System.out.print(T(i, j) + ", ");
            System.out.println();
        }

        return sum;
    }

    int T(int n, int k) {
        if(n<0 || k==0 || n+1<k)
            return 0;
        if(k==1)
            return 1;
        if(dp[n][k] != 0)
            return dp[n][k];

        for(int i=0; i<n; i++)
            if(array[n] > array[i])
                dp[n][k] += T(i, k-1);

        return dp[n][k];
    }

    static public void main(String args[]) {
        CountOfIncreasingSubsequenceWithLengthK solution = new CountOfIncreasingSubsequenceWithLengthK();
        int[] arr = {1, 4, 6, 2, 7};
        int k = 3;
        System.out.println(solution.ISK(arr, k));
    }
}
