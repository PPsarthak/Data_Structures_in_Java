package com.dynamicProgramming.grids;

import java.util.Arrays;

/**
 * Based on DP-11
 * Fixed Start point and variable ending point
 */
public class Triangle {
    public static void main(String[] args) {
        int[][] grid = {
                {2},{3,4},{6,5,7},{4,1,8,3}
        };
        int n = grid[grid.length-1].length;

        System.out.println(recursive(0,0,grid));

         int[][] dp = new int[n][n];
         for(int[] i : dp) Arrays.fill(i, -1);

        System.out.println(memoize(dp,0,0,grid));

        System.out.println(tabulate(grid,n));
    }
    static int recursive(int i, int j, int[][] grid){
        if(i == grid.length-1){
            return grid[i][j];
        }

        int down = grid[i][j] + recursive(i+1, j, grid);
        int right = grid[i][j] + recursive(i+1, j+1, grid);

        return Math.min(down,right);
    }
    static int memoize(int[][] dp, int i, int j, int[][] grid){
        if(i == grid.length-1){
            return grid[i][j];
        }

        if(dp[i][j]!=-1) return dp[i][j];

        int down = grid[i][j] + memoize(dp ,i+1, j, grid);
        int right = grid[i][j] + memoize(dp, i+1, j+1, grid);

        return dp[i][j] = Math.min(down,right);
    }
    static int tabulate(int[][] grid, int n) {
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = grid[n - 1][n - 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (i == n - 1) dp[i][j] = grid[i][j];
                else {
                    dp[i][j] = grid[i][j];
                    dp[i][j] += Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }
}
