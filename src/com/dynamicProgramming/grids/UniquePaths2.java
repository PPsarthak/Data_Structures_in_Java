package com.dynamicProgramming.grids;

import java.util.Arrays;

/**
 * Based on Leetcode 63 := Unique Paths II
 * Given a start and end pt in 2D Matrix, find the no of all unique paths
 * The 2D Matrix is filled with 1: obstacle and 0: no obstacle
 */
public class UniquePaths2 {
        public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

        int[][] dp = new int[grid.length][grid[0].length];
        for(int[] i: dp){
            Arrays.fill(i, -1);
        }

        System.out.println(recursive(grid.length-1 , grid[0].length-1, grid));
        System.out.println(memoize(dp,grid.length-1 , grid[0].length-1, grid));
        System.out.println(tabulate(grid));
    }
        static int tabulate(int[][] grid){
        int m = grid.length-1;
        int n = grid[0].length-1;

        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 1;

        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(grid[i][j] == 1) dp[i][j] = 0;
                else{
                    if(i>0){
                        dp[i][j] += dp[i-1][j];
                    }
                    if(j>0){
                        dp[i][j] += dp[i][j-1];
                    }
                }
            }
        }

        return dp[m][n];
    }

        static int memoize(int[][] dp, int i, int j, int[][] grid){
        if(i<0 || j<0 || grid[i][j] == 1){
            return 0;
        }
        if(i == 0 && j == 0) return 1;

        if(dp[i][j]!=-1) return dp[i][j];

        int top = memoize(dp, i-1, j, grid);
        int left = memoize(dp, i, j-1, grid);

        return dp[i][j] = top+left;
    }
        static int recursive(int i, int j, int[][] grid){
        if(i<0 || j<0 || grid[i][j] == 1){
            return 0;
        }
        if(i == 0 && j == 0) return 1;

        int top = recursive(i-1, j, grid);
        int left = recursive(i, j-1, grid);

        return top+left;
    }
}
