package com.dynamicProgramming.stocks;

import java.util.Arrays;

public class BTBASS4 {
    public static void main(String[] args) {
        int[] arr = {3,3,5,0,0,3,1,4};
        int k = 2;

        int[][][] dp = new int[arr.length][2][k+1];
        for(int[][] i : dp) for(int[] j : i) Arrays.fill(j,-1);

        System.out.println(recursive(arr,0, true, k));
        System.out.println(memoize(arr,0,1,k,dp));
        System.out.println(tabulate(arr,k));
    }
    static int recursive(int[] arr, int index, boolean canBuy, int cap){
        if(cap == 0) return 0;
        if(index == arr.length) return 0;

        int profit = 0;

        if(canBuy){
            int notBuy = recursive(arr, index+1, canBuy, cap);
            int buy = recursive(arr, index+1, !canBuy, cap) - arr[index];
            profit = Math.max(profit, Math.max(buy, notBuy));
        }
        else{
            int notSell = recursive(arr, index+1, canBuy, cap);
            int sell = arr[index] + recursive(arr, index+1, !canBuy, cap-1);
            profit = Math.max(profit, Math.max(sell, notSell));
        }

        return profit;
    }
    static int memoize(int[] arr, int index, int canBuy, int cap, int[][][] dp){
        if(cap == 0) return 0;
        if(index == arr.length) return 0;

        if(dp[index][canBuy][cap]!=-1) return dp[index][canBuy][cap];

        int profit = 0;

        if(canBuy == 1){
            int notBuy = memoize(arr, index+1, 1, cap, dp);
            int buy = memoize(arr, index+1, 0, cap, dp) - arr[index];
            profit = Math.max(profit, Math.max(buy, notBuy));
        }
        else{
            int notSell = memoize(arr, index+1, 0, cap, dp);
            int sell = arr[index] + memoize(arr, index+1, 1, cap-1, dp);
            profit = Math.max(profit, Math.max(sell, notSell));
        }

        return dp[index][canBuy][cap]=profit;
    }
    static int tabulate(int[] arr, int k){
        int[][][] dp = new int[arr.length+1][2][k+1];

        // there are 2 base cases, both return 0 => now all values are zeroes,
        // hence we skip writing base case

        for(int index = arr.length-1; index>=0; index--){
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int cap = 1; cap <= k ; cap++) {
                    int profit = 0;

                    if(canBuy == 1){
                        int notBuy = dp[index+1][1][cap];
                        int buy = dp[index+1][0][cap] - arr[index];
                        profit = Math.max(profit, Math.max(buy, notBuy));
                    }
                    else{
                        int notSell = dp[index+1][0][cap];
                        int sell = arr[index] + dp[index+1][1][cap-1];
                        profit = Math.max(profit, Math.max(sell, notSell));
                    }

                    dp[index][canBuy][cap] = profit;
                }
            }
        }

        return dp[0][1][k];
    }
}
