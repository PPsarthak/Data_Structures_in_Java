package com.dynamicProgramming.stocks;

import java.util.Arrays;

/**
 * Based on DP-36
 * Still not proper DP Que, can be solved using Greedy
 * The leetcode method (fucking fastest) just finds the instances where we have a profit (no matter
 * how big or small the profit is) and adds it to total
 *
 * REMEMBER (IF YOU ARE LOOKING AT GREEDY APPROACH),
 *      WE CAN SELL AND THEN AGAIN BUY ON THE SAME DAY i.e., WE CAN PROLONG IT IN THIS WAY
 *
 * PS: THIS THINKING IS DONE AUTOMATICALLY BY DP APPROACH
 */
public class BTTBASS2 {
    public static void main(String[] args) {
        int[] arr = {7,1,3,5,4,3,6}; // ans:= 1 and 5 + 3 and 6 => 4 + 3 = 7

        System.out.println(recursive(arr, 0, true));

        int[][] dp = new int[arr.length][2];
        for(int[] i : dp) Arrays.fill(i,-1);

        System.out.println(memoize(arr, 0, 1, dp));

        System.out.println(fuckingFast(arr));
    }
    static int fuckingFast(int[] arr){
        //approach: buy and sell max no of times to get max profit
        int profit = 0;
        for (int i = 0; i < (arr.length-1); i++){
            if (arr[i] < arr[i+1]){
                System.out.println(arr[i+1] + " " + arr[i]);
                profit += arr[i+1] - arr[i];
            }
        }
        return profit;
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
