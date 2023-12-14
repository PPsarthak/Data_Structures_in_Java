package com.dynamicProgramming.DP1D;

import java.util.Arrays;

/**
 * Based on DP-2
 * Here we count the number of ways
 * REMEMBER: In Que where we have to count the number of ways, we generally return 0 or 1
 * based on whether the path is valid or not
 */
public class ClimbingStairs {
    //tested on very few cases
    public static void main(String[] args) {
        int n = 3;
        System.out.println(recursive(n-1));

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        System.out.println(memoize(n-1, dp));

        System.out.println(tabulate(n));
    }
    static int recursive(int index){
        // if we reached 0th index that means it is a valid path, hence return 1
        if(index == 0) return 1;
        // if we reached index -1 (i.e.<0) then this is not a valid path, return 0
        if(index < 0) return 0;

        int jump1 = recursive(index-1);
        int jump2 = recursive(index-2);

        return jump1+jump2;
    }
    static int memoize(int index, int[] dp){
        // if we reached 0th index that means it is a valid path, hence return 1
        if(index == 0) return 1;
        // if we reached index -1 (i.e.<0) then this is not a valid path, return 0
        if(index < 0) return 0;

        if(dp[index]!=-1) return dp[index];

        int jump1 = memoize(index-1, dp);
        int jump2 = memoize(index-2, dp);

        return dp[index] = jump1+jump2;
    }
    static int tabulate(int n){
        int[] dp = new int[n+1]; //for -1 to n

        dp[0] = 0; //corresponds to index = -1
        dp[1] = 1; //corresponds to index = 0

        for (int index = 2; index <= n; index++) {
            int jump1 = dp[index-1];
            int jump2 = dp[index-2];

            dp[index] = jump1+jump2;
        }

        return dp[n];
    }
}
