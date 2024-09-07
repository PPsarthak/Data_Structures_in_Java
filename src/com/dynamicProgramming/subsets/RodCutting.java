package com.dynamicProgramming.subsets;

import java.util.Arrays;

public class RodCutting {
    public static void main(String[] args) {
        int[] arr = {2,5,3,4,6,7};
        int rod = 14;

        int[][] dp = new int[arr.length][rod+1];
        for(int[] i : dp) Arrays.fill(i, -1);

        System.out.println(memoize(arr.length-1, rod, arr, dp));
    }
    static int memoize(int index, int rod, int[] arr, int[][] dp){
        if(index == 0){
            return rod*arr[0];
        }
        if(dp[index][rod]!=-1) return dp[index][rod];

        //not cut
        int ans = memoize(index-1, rod, arr, dp);

        //if rod.length (index+1) permits, then only cut it ~ 01knapsack
        if(index+1<=rod){
            ans = Math.max(ans, arr[index]+memoize(index, rod-index-1, arr, dp));
        }

        return dp[index][rod] = ans;
    }
}
