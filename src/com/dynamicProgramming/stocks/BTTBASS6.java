package com.dynamicProgramming.stocks;

import java.util.Arrays;

/**
 * Based on DP-40
 * Same as BTBASS5, (i.e., with a cooldown) but we have to pay a transaction fee when selling
 */
public class BTTBASS6 {
    public static void main(String[] args) {
        int[] arr = {1,3,2,8,4,9};
        int fee = 2;

        System.out.println(recursive(arr, 0, true, fee));
        int[][] dp = new int[arr.length][2];
        for(int[] i : dp) Arrays.fill(i,-1);

        System.out.println(memoize(arr, 0, 1, fee, dp));

        System.out.println(tabulate(arr, fee));
    }
    static int recursive(int[] arr, int index, boolean canBuy, int fee){
        if(index == arr.length) return 0;

        int profit = 0;

        if(canBuy){
            int notBuy = recursive(arr, index+1, canBuy, fee);
            int buy = recursive(arr, index+1, !canBuy, fee) - arr[index];
            profit = Math.max(profit, Math.max(buy, notBuy));
        }
        else{
            int notSell = recursive(arr, index+1, canBuy, fee);
            int sell = arr[index] - fee + recursive(arr, index+1, !canBuy, fee);
            profit = Math.max(profit, Math.max(sell, notSell));
        }

        return profit;
    }
    static int memoize(int[] arr, int index, int canBuy, int fee, int[][] dp){
        if(index == arr.length) return 0;

        if(dp[index][canBuy]!=-1) return dp[index][canBuy];

        int profit = 0;

        if(canBuy == 1){
            int notBuy = memoize(arr, index+1, 1, fee, dp);
            int buy = memoize(arr, index+1, 0, fee, dp) - arr[index];
            profit = Math.max(profit, Math.max(buy, notBuy));
        }
        else{
            int notSell = memoize(arr, index+1, 0, fee, dp);
            int sell = arr[index] - fee + memoize(arr, index+1, 1, fee, dp);
            profit = Math.max(profit, Math.max(sell, notSell));
        }

        return dp[index][canBuy]=profit;
    }
    static int tabulate(int[] arr, int fee){
        int[][] dp = new int[arr.length+1][2];

        //both are already zero but still just to show the base case
        dp[arr.length][0] = 0;
        dp[arr.length][1] = 0;

        for(int index = arr.length-1; index >= 0; index--){
            for(int canBuy = 0; canBuy<= 1; canBuy++){
                int profit = 0;
                if(canBuy == 1){
                    int notBuy = dp[index+1][1];
                    int buy = dp[index+1][0] - arr[index];
                    profit = Math.max(profit, Math.max(buy, notBuy));
                }
                else{
                    int notSell = dp[index+1][0];
                    int sell = arr[index] - fee + dp[index+1][1];
                    profit = Math.max(profit, Math.max(sell, notSell));
                }
                dp[index][canBuy] = profit;
            }
        }

        return dp[0][1];
    }
}
