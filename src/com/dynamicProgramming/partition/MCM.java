package com.dynamicProgramming.partition;

import java.util.Arrays;

public class MCM {
    public static void main(String[] args) {
        int[] arr = {10,100,20,5,80};

        System.out.println(recursive(arr, 1, arr.length-1));

        int[][] dp = new int[arr.length][arr.length];
        for(int[] i : dp) Arrays.fill(i, -1);

        System.out.println(memoize(arr, dp, 1, arr.length-1));

        System.out.println(tabulate(arr));
    }
    static int tabulate(int[] arr){
        int[][] dp = new int[arr.length][arr.length];

        //diagonals marked as 0
        for(int i=0; i<dp.length; i++) dp[i][i] = 0;

        for(int i=arr.length-1; i>=1; i--){
            for (int j=i+1; j< arr.length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int operations = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    min = Math.min(min, operations);
                }
                dp[i][j] = min;
            }
        }
        //return f(1,4)
        return dp[1][arr.length-1];
    }
    static int memoize(int[] arr, int[][] dp, int i, int j){
        if(i==j) return 0;

        if(dp[i][j]!=-1) return dp[i][j];

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            min = Math.min(min, arr[i-1] * arr[k] * arr[j]
                    + memoize(arr, dp, i, k) + memoize(arr, dp,k+1,j));
        }
        return dp[i][j] = min;
    }
    static int recursive(int[] arr, int i, int j){
        if(i==j) return 0;

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            min = Math.min(min, arr[i-1] * arr[k] * arr[j]
                    + recursive(arr, i, k) + recursive(arr, k+1,j));
        }
        return min;
    }
}
