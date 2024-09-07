package com.dynamicProgramming.subsets;

import java.util.Arrays;

/**
 * Based on DP-17
 * Here we are given an array, and we have to count the subset with sum = k
 *
 * FOLLOW UP: THE CODE WORKS ONLY IF arr[i] > 0
 *            IF THERE ARE ZEROES IN YOUR ARRAY, JUST REMOVE THE SUM == 0 BASE CASE
 */
public class CountSubsetSumK {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int k = 7;

        System.out.println(recursive(arr, arr.length-1, k));

        int[][] dp = new int[arr.length][k+1];
        for(int[] i : dp) Arrays.fill(i,-1);

        System.out.println(memoize(arr,arr.length-1, k, dp));

        System.out.println(tabulate(arr, k));
    }
    static int recursive(int[] arr, int index, int sum){
        if(sum == 0) return 1;  //remove this case if array contains 0
        if(index == 0){
            if(arr[index] == sum) return 1;
            else return 0;
        }

        //not take
        int ans = recursive(arr, index-1, sum);
        //take
        if(arr[index] <= sum){
            ans+=recursive(arr, index-1, sum-arr[index]);
        }

        return ans;
    }
    static int memoize(int[] arr, int index, int sum, int[][] dp){
        if(sum == 0) return 1; //remove this case if array contains 0
        if(index == 0){
            if(arr[index] == sum) return 1;
            else return 0;
        }

        if(dp[index][sum]!=-1) return dp[index][sum];

        //not take
        int ans = memoize(arr, index-1, sum, dp);
        //take
        if(arr[index] <= sum){
            ans+=memoize(arr, index-1, sum-arr[index], dp);
        }

        return dp[index][sum]=ans;
    }
    static int tabulate(int[] arr, int k){
        int[][] dp = new int[arr.length][k+1];

        //remove this case if array contains 0
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        dp[0][arr[0]] = 1;

        for (int index = 1; index < arr.length; index++) {
            for (int sum = 1; sum <= k; sum++) {
                int ans = dp[index-1][sum];
                //take
                if(arr[index] <= sum){
                    ans+=dp[index-1][sum-arr[index]];
                }
                dp[index][sum] = ans;
            }
        }

        return dp[arr.length-1][k];
    }
}
