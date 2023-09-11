package com.dynamicProgramming;

import java.util.Arrays;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 5;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        System.out.println(recursion(n));
        System.out.println(memoization(n, dp));
        System.out.println(tabulation(n));
        System.out.println(optimized(n));
    }

    /**
     * Space optimized solution
     * @param n index of fibonacci number
     * @return fibonacci number of given index
     * @SpaceComplexity O(1)
     */
    static int optimized(int n){
        if(n<=1) return n;
        int prev = 0;
        int curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
    /**
     * Tabulation method - suitable only for n below 40-45
     * We started from bottom i.e., 0, 1 and 2 and went up filling the array
     * @param n index of fibonacci number
     * @return fibonacci number of given index
     * @TimeComplexity O(N)
     * @SpaceComplexity O(N) : Array + O(N) : Auxiliary Space
     */
    static int tabulation(int n){
        if(n<=1) return n;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    /**
     * Memoization method - suitable only for n below 40-45
     * We started from top i.e., n and went down filling the array
     * @param n index of fibonacci number
     * @return fibonacci number of given index
     * @TimeComplexity O(N)
     * @SpaceComplexity O(N) : Array + O(N) : Auxiliary Space
     */
    static int memoization(int n, int[] dp){
        if(n<=1) return n;
        if(dp[n]!=-1) return dp[n];
        dp[n] = memoization(n-1, dp) + memoization(n-2, dp);
        return dp[n];
    }

    /**
     * Recursion method - lots of overhead
     * @param n index of fibonacci number
     * @return fibonacci number of given index
     * @TimeComplexity O(2^N)
     * @SpaceComplexity O(N): Auxiliary Space
     */
    static int recursion(int n){
        if(n<=1) return n;
        return recursion(n-1) + recursion(n-2);
    }
}
