package com.dynamicProgramming.grids;

import java.util.Arrays;
/**
 * Based on Leetcode 64 := Min Path Sum
 * Given a 2D matrix find the path with min sum from 0,0 to m,n
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        for(int[] i: dp){
            Arrays.fill(i, -1);
        }
        System.out.println(recursive(m-1, n-1, grid));
        System.out.println(memoize(dp, m-1, n-1, grid));
    }

    static int recursive(int i, int j, int[][] grid) {
        if(i == 0 && j == 0) return grid[i][j];
        if(i<0 || j<0) return 10000000;

        int top = grid[i][j] + recursive(i-1, j, grid);
        int left = grid[i][j] + recursive(i, j-1, grid);

        return top+left;
    }

    static int memoize(int[][] dp, int i, int j, int[][] grid){
        if(i == 0 && j == 0) return grid[i][j];
        if(i<0 || j<0) return 10000000;

        if(dp[i][j]!=-1) return dp[i][j];

        int top = grid[i][j] + memoize(dp, i-1, j, grid);
        int left = grid[i][j] + memoize(dp, i, j-1, grid);

        return dp[i][j] = Math.min(top, left);
    }
}
