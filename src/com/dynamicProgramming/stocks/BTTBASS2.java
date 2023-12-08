package com.dynamicProgramming.stocks;

import java.util.Arrays;

public class BTTBASS2 {
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4}; // ans:= 1 and 5 + 3 and 6 => 4 + 3 = 7

        System.out.println(recursive(arr, 0, true));

        int[][] dp = new int[arr.length][2];
        for(int[] i : dp) Arrays.fill(i,-1);
    }
    static int recursive(int[] arr, int index, boolean canBuy){
        if(index == arr.length) return 0;

        int profit = 0;

        if(canBuy){
            int notBuy = recursive(arr, index+1, canBuy);
            int buy = recursive(arr, index+1, !canBuy) - arr[index];
            profit = Math.max(profit, Math.max(buy, notBuy));
        }
        else{
            int notSell = recursive(arr, index+1, canBuy);
            int sell = arr[index] + recursive(arr, index+1, !canBuy);
            profit = Math.max(profit, Math.max(sell, notSell));
        }

        return profit;
    }
    static int memoize(int[] arr, int index, int canBuy, int[][] dp){
        if(index == arr.length) return 0;

        if(dp[index][canBuy]!=-1) return dp[index][canBuy];

        int profit = 0;

        if(canBuy == 1){
            int notBuy = memoize(arr, index+1, 1, dp);
            int buy = memoize(arr, index+1, 0, dp) - arr[index];
            profit = Math.max(profit, Math.max(buy, notBuy));
        }
        else{
            int notSell = memoize(arr, index+1, 0, dp);
            int sell = arr[index] + memoize(arr, index+1, 1, dp);
            profit = Math.max(profit, Math.max(sell, notSell));
        }

        return dp[index][canBuy]=profit;
    }
}
