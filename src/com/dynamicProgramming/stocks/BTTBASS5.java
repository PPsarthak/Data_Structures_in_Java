package com.dynamicProgramming.stocks;

import java.util.Arrays;

/**
 * Based on DP-39
 * Same as BTBASS2, unlimited transactions but with a cooldown
 * You can't sell and buy directly on the next day, you have to wait for 1 day
 */
public class BTTBASS5 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,0,2};
        System.out.println(recursive(arr, 0, 1, 0));

        int[][][] dp = new int[arr.length][2][2];
        for(int[][] i : dp) for(int[] j : i) Arrays.fill(j,-1);

        System.out.println(memoize(arr,0,1,0, dp));
    }
    static int recursive(int[] arr, int index, int canBuy, int cool){
        if(index == arr.length) return 0;

        int profit = 0;

        if(canBuy == 1){
            if(cool == 1){
                //if we have a cooldown, not buying is the only option
                int notBuy = recursive(arr, index+1, 1, 0);
                profit = Math.max(profit, notBuy);
            }
            else{
                //if we are not under a cooldown, we can buy or not buy
                int notBuy = recursive(arr, index+1, 1, 0);
                int buy = recursive(arr, index+1, 0, 0) - arr[index];
                profit = Math.max(profit, Math.max(buy, notBuy));
            }
        }
        else{
            int notSell = recursive(arr, index+1, 0, 0);
            int sell = arr[index] + recursive(arr, index+1, 1, 1); //cooldown after selling
            profit = Math.max(profit, Math.max(sell, notSell));
        }

        return profit;
    }
    static int memoize(int[] arr, int index, int canBuy, int cool, int[][][] dp){
        if(index == arr.length) return 0;

        if(dp[index][canBuy][cool]!=-1) return dp[index][canBuy][cool];

        int profit = 0;

        if(canBuy == 1){
            if(cool == 1){
                int notBuy = memoize(arr, index+1, 1, 0, dp);
                profit = Math.max(profit, notBuy);
            }
            else{
                int notBuy = memoize(arr, index+1, 1, 0, dp);
                int buy = memoize(arr, index+1, 0, 0, dp) - arr[index];
                profit = Math.max(profit, Math.max(buy, notBuy));
            }
        }
        else{
            int notSell = memoize(arr, index+1, 0, 0, dp);
            int sell = arr[index] + memoize(arr, index+1, 1, 1, dp);
            profit = Math.max(profit, Math.max(sell, notSell));
        }

        return dp[index][canBuy][cool]=profit;
    }
    static int tabulate(int[] arr) {
        int n = arr.length;
        int[][][] dp = new int[n + 1][2][2];

        // Initialize base case values for dp[arr.length][canBuy][cool]
        for (int canBuy = 0; canBuy <= 1; canBuy++) {
            for (int cool = 0; cool <= 1; cool++) {
                dp[n][canBuy][cool] = 0;
            }
        }

        for (int index = n - 1; index >= 0; index--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int cool = 0; cool <= 1; cool++) {
                    int profit = 0;

                    if (canBuy == 1) {
                        if (cool == 1) {
                            int notBuy = dp[index + 1][1][0];
                            profit = Math.max(profit, notBuy);
                        } else {
                            int notBuy = dp[index + 1][1][0];
                            int buy = dp[index + 1][0][0] - arr[index];
                            profit = Math.max(profit, Math.max(buy, notBuy));
                        }
                    } else {
                        int notSell = dp[index + 1][0][0];
                        int sell = arr[index] + dp[index + 1][1][1];
                        profit = Math.max(profit, Math.max(sell, notSell));
                    }

                    dp[index][canBuy][cool] = profit;
                }
            }
        }

        return dp[0][1][0];
    }

}
