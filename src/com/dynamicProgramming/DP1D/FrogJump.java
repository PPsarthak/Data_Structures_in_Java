package com.dynamicProgramming.DP1D;

import java.util.Arrays;

/**
 * Based on DP-3
 *
 * Here we count the min of the 2 ways
 *
 * QUE: A frog can either jump by 1 step or by 2 step the total energy consumed by the frog
 *      is the difference between the current position and the position he wants to jump to.
 *      Calculate the min energy required
 *
 * REMEMBER: In Que where we have to get the min of all the ways, we generally return 0
 *          if the current scenario is optimal else we return a very large value so that it get
 *          discarded in Math.min()
 */
public class FrogJump {
    public static void main(String[] args) {
        int[] arr = {30,10,60,10,60,50};

        System.out.println(recursive(arr.length-1, arr));

        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);

        System.out.println(memoize(arr.length-1, arr, dp));

        System.out.println(tabulate(arr));
    }
    static int recursive(int index, int[] arr){
        if(index == 0) return 0;

        int jump1 = recursive(index-1, arr) + Math.abs(arr[index]-arr[index-1]);
        int jump2 = (int)(1e8);
        if(index >=2 ){
            jump2 = recursive(index-2, arr) + Math.abs(arr[index]-arr[index-2]);
        }

        return Math.min(jump1, jump2);
    }
    static int memoize(int index, int[] arr, int[] dp){
        if(index == 0) return 0;

        if(dp[index]!=-1) return dp[index];

        int jump1 = recursive(index-1, arr) + Math.abs(arr[index]-arr[index-1]);
        int jump2 = (int)(1e8);
        if(index >=2 ){
            jump2 = recursive(index-2, arr) + Math.abs(arr[index]-arr[index-2]);
        }

        return dp[index]=Math.min(jump1, jump2);
    }
    static int tabulate(int[] arr){
        int[] dp = new int[arr.length];

        dp[0] = 0;

        for (int index = 1; index < arr.length; index++) {
            int jump1 = dp[index-1] + Math.abs(arr[index]-arr[index-1]);

            int jump2 = (int)(1e8);
            if(index >=2){
                jump2 = dp[index-2] + Math.abs(arr[index]-arr[index-2]);
            }

            dp[index]=Math.min(jump1, jump2);
        }
        return dp[arr.length-1];
    }
}
