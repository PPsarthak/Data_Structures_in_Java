package com.dynamicProgramming.DP2D;

import java.util.Arrays;

public class NinjaTraining {
    public static void main(String[] args) {
        int[][] arr = {
                {10, 11, 19},
                {4, 13, 7},
                {1, 8, 13}
        };

        //initial index is kept 3 (not 0, 1 or 2)
        System.out.println(recursive(arr, arr.length-1, 3));

        //since initial index = 3, we will declare dp of 4 columns
        int[][] dp = new int[arr.length][4];
        for(int[] i : dp) Arrays.fill(i,-1);

        System.out.println(memoize(arr, arr.length-1, 3, dp));

        System.out.println(tabulate(arr));
    }
    static int recursive(int[][] arr, int row, int index){
        if(row == 0){
            int ans = -1;
            for(int i=0; i<3; i++){
                if(i!=index) ans = Math.max(ans, arr[row][i]);
            }
            return ans;
        }

        int ans = -1;
        for (int i = 0; i < 3; i++) {
            if(i!=index){
                ans = Math.max(ans, arr[row][i] + recursive(arr, row-1,i));
            }
        }

        return ans;
    }
    static int memoize(int[][] arr, int row, int index, int[][] dp){
        if(row == 0){
            int ans = -1;
            for(int i=0; i<3; i++){
                if(i!=index) ans = Math.max(ans, arr[0][i]);
            }
            return ans;
        }

        if(dp[row][index] != -1) return dp[row][index];

        int ans = -1;
        for (int i = 0; i < 3; i++) {
            if(i != index){
                ans = Math.max(ans, arr[row][i] + memoize(arr, row - 1, i, dp));
            }
        }

        return dp[row][index] = ans;
    }
    static int tabulate(int[][] arr){
        int[][] dp = new int[arr.length][4];

        //for row = 0, take max...now this can be done using for loop but this feels more easy
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        for(int row = 1; row<arr.length; row++){
            for (int index = 0; index < 4; index++) {
                int ans = -1;
                for (int i = 0; i < 3; i++) {
                    if(i != index){
                        ans = Math.max(ans, arr[row][i] + dp[row-1][i]);
                    }
                }
                dp[row][index] = ans;
            }
        }

        return dp[arr.length-1][3];
    }

}
