package com.dynamicProgramming.subsets;

import java.util.Arrays;

/**
 * Based on DP-14
 * Return true/false based on whether there exists a subset with sum K
 * WORKS IFF SUM>=0
 */
public class SubsetSumK {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4 };

        System.out.println(recursive(arr, arr.length-1, 4));

        int[][] dp = new int[arr.length][5];
        for(int[] i : dp) Arrays.fill(i,-1);

        System.out.println(memoize(arr, arr.length-1, 4, dp));

        System.out.println(tabulate(arr, 4));
    }
    static int recursive(int[] arr, int index, int sum){
        if(sum == 0) return 1;
        if(index == 0){
            if(arr[index] == sum) return 1;
            else return 0;
        }

        int notTake = recursive(arr, index-1, sum);
        if(notTake == 1) return 1;

        int take = 0;
        if(arr[index] <= sum){
            take = recursive(arr, index-1, sum-arr[index]);
        }

        // same as return notTake+take > 0 ?  1 : 0;
        return notTake | take;
    }
    static int memoize(int[] arr, int index, int sum, int[][] dp){
        if(sum == 0) return 1;
        if(index == 0){
            if(arr[index] == sum) return 1;
            else return 0;
        }

        if(dp[index][sum]!=-1) return dp[index][sum];

        int notTake = memoize(arr, index-1, sum, dp);
        if(notTake == 1) return dp[index][sum] = 1;

        int take = 0;
        if(arr[index] <= sum){
            take = memoize(arr, index-1, sum-arr[index], dp);
        }

        // same as return notTake+take > 0 ?  1 : 0;
        return dp[index][sum] = notTake | take;
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

        return dp[arr.length-1][k];
    }
}
