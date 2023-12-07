package com.dynamicProgramming.subsets;

/**
 * Based on DP-16
 * Given an array find the min abs difference of sum of 2 subsets formed by dividing the array
 * JUST WATCH THE VIDEO := <a href="https://youtu.be/GS_OqZb2CWc?feature=shared&t=1068">.</a>
 */
public class MinDiffSubsetSum {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4 };
        System.out.println(tabulate(arr, 4));
    }
    static int tabulate(int[] arr, int k){
        int[][] dp = new int[arr.length][k+1];

        for(int i=0; i< dp.length; i++) dp[i][0] = 1;

        dp[0][arr[0]] = 1;

        for (int index = 1; index < dp.length; index++) {
            for (int sum = 1; sum <= k; sum++) {
                int notTake = dp[index-1][sum];
                int take = 0;
                if(arr[index] <= sum){
                    take = dp[index-1][sum-arr[index]];
                }
                dp[index][sum] = notTake | take;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dp[0].length; i++) {
            if(dp[arr.length-1][i] == 1) min = Math.min(min, Math.abs(k-i-i));
        }
        return min;
    }
}
