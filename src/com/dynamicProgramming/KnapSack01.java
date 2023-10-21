package com.dynamicProgramming;

import java.util.Arrays;

public class KnapSack01 {
    static int[][] dp;
    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5};
        int[] values = {10, 6, 12, 7, 9};
        int wt = 8;

        dp = new int[weights.length][wt + 1];
        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }

        System.out.println(memoize(0, wt, weights, values));
        System.out.println(Arrays.deepToString(dp));
    }
    static int memoize(int index, int wt, int[] weights, int[] values) {
        // return 0 since we have exhausted our array
        if (index == weights.length || wt == 0) {
            return 0;
        }

        // if already cached, return it
        if (dp[index][wt] != -1) return dp[index][wt];

        // ans = if item not taken
        int ans = memoize(index + 1, wt, weights, values);
        if (weights[index] <= wt) {
            // if wt permits, ans = max(not take, taken)
            ans = Math.max(ans, values[index] + memoize(index + 1, wt - weights[index], weights, values));
        }

        return dp[index][wt] = ans;
    }

    static int recursion(int index, int wt, int[] weights, int[] values){
        // return 0 since we have exhausted our array
        if(index == weights.length || wt == 0){
            return 0;
        }

        // ans = if item not taken
        int ans = recursion(index+1, wt, weights, values);
        if(weights[index] <= wt){
            //if wt permits, ans = max(not take, taken)
            ans = Math.max(ans, values[index] +
                    recursion(index+1, wt-weights[index],weights, values));
        }

        return ans;
    }
}
