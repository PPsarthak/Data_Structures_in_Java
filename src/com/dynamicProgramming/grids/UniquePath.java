package com.dynamicProgramming.grids;

import java.util.Arrays;

/**
 * Based on Leetcode 62 := Unique Paths
 * Given a start and end pt in 2D Matrix, find the no of all unique paths
 */
public class UniquePath {
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        System.out.println(recursion(0,0,m,n));
        int[][] dp = new int[m][n];
        for(int[] i: dp){
            Arrays.fill(i, -1);
        }
        System.out.println(memoize(dp,0,0,m,n));
        System.out.println(tabulate(m,n));
        System.out.println(moreSpaceOptimized(m,n));
    }

    /**
     * We are reducing the problem of tabulation from 2D matrix to 2-1D Arrays problem
     * Since at any given instance, we only need the prev array and the current array
     * @param m total rows
     * @param n total columns
     * @return no of paths
     */
    static int moreSpaceOptimized(int m, int n){
        int[] prevRow;
        int[] currRow = new int[n];

        for (int i = 0; i < m; i++) {
            prevRow = currRow;
            for (int j = 0; j < n; j++) {
                if(j == 0) currRow[j] = 1;
                else{
                    currRow[j] = prevRow[j] + currRow[j-1];
                }
            }
        }
        return currRow[n-1];
    }
    /**
     * @TimeComplexity O(2^mxn)
     * @param i current row
     * @param j current column
     * @param m total rows
     * @param n total columns
     * @return no of paths
     */
    static int recursion(int i, int j, int m, int n){
        if(i == m-1 && j == n-1) return 1;
        if(i >= m || j >= n) return 0;

        int right = recursion(i, j+1, m, n);
        int down = recursion(i+1, j, m, n);

        return right+down;
    }

    /**
     * @TimeComplexity O(mxn)
     * @SpaceComplexity O(m+n) (Stack space := longest path since it will cost max recursion call)
     * O(mxn) := dp matrix
     * @param dp dp matrix
     * @param i current row
     * @param j current column
     * @param m total rows
     * @param n total columns
     * @return no of paths
     */
    static int memoize(int[][] dp, int i, int j, int m, int n){
        if(i == m-1 && j == n-1) return 1;
        if(i >= m || j >= n) return 0;

        if(dp[i][j]!=-1) return dp[i][j];

        int right = memoize(dp, i, j+1, m, n);
        int down = memoize(dp, i+1, j, m, n);

        dp[i][j] = right+down;

        return dp[i][j];
    }

    /**
     * @TimeComplexity O(mxn)
     * @SpaceComplexity O(mxn) := dp matrix
     * @param m total rows
     * @param n total columns
     * @return no of paths
     */
    static int tabulate(int m, int n){
        int[][] dp = new int[m][n];

        dp[0][0] = 1;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(j>0){
                    dp[i][j] += dp[i][j-1];
                }
                if(i>0){
                    dp[i][j] += dp[i-1][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
