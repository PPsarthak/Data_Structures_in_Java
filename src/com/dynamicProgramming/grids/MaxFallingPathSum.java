package com.dynamicProgramming.grids;

import java.util.Arrays;

/**
 * Based on DP-12
 * Variable starting point, variable ending point
 */
public class MaxFallingPathSum {
    public static void main(String[] args) {
        int[][] arr = {
                {1,2,10,4}, {100,3,2,1}, {1,1,20,2}, {1,2,2,1}
        };

        int max = 0;
        for (int i = 0; i < arr[0].length; i++) {
            max = Math.max(max, recursive(arr, arr.length-1, i));
        }
        System.out.println(max);

        int[][] dp = new int[arr.length][arr[0].length];
        for(int[] i : dp) Arrays.fill(i,-1);

        max = 0;
        for (int i = 0; i < arr[0].length; i++) {
            max = Math.max(max, memoize(arr, arr.length-1, i, dp));
        }
        System.out.println(max);

        System.out.println(tabulate(arr));
    }
    static int recursive(int[][] arr, int i, int j){
        if(j<0 || j>=arr[0].length) return (int)(-1e8);
        if(i==0) return arr[0][j];

        int up = arr[i][j] + recursive(arr,i-1,j);
        int left = arr[i][j] + recursive(arr,i-1,j-1);
        int right = arr[i][j] + recursive(arr,i-1,j+1);

        return Math.max(up, Math.max(left, right));
    }
    static int memoize(int[][] arr, int i, int j, int[][] dp){
        if(j<0 || j>=arr[0].length) return (int)(-1e8);
        if(i==0) return arr[0][j];

        if(dp[i][j]!=-1) return dp[i][j];

        int up = arr[i][j] + memoize(arr,i-1,j,dp);
        int left = arr[i][j] + memoize(arr,i-1,j-1,dp);
        int right = arr[i][j] + memoize(arr,i-1,j+1,dp);

        return dp[i][j]=Math.max(up, Math.max(left, right));
    }
    static int tabulate(int[][] arr){
        int[][] dp = new int[arr.length][arr[0].length];

        for(int i=0; i<arr[0].length; i++) dp[0][i] = arr[0][i];

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                int up = arr[i][j] + dp[i-1][j];

                int left = arr[i][j];
                if(j-1>=0) left += dp[i-1][j-1];
                else left+=(int)(-1e8);

                int right = arr[i][j];
                if(j+1<arr[0].length) right += dp[i-1][j+1];
                else right+=(int)(-1e8);

                dp[i][j]=Math.max(up, Math.max(left, right));
            }
        }

        int max = -1;
        for (int i = 0; i < arr[0].length; i++) {
            max = Math.max(max, dp[arr.length-1][i]);
        }
        return max;
    }
}
