package com.dynamicProgramming.DP1D;

import java.util.Arrays;

/**
 * Based on DP-5
 * Same as Maximum sum of non-adjacent elements
 * Here we count the max of the 2 ways
 * REMEMBER: In Que where we have to get the max of all the ways, we generally return the
 *           actual element or a very large value if the current scenario is optimal
 *           else we return a very large value so that it get discarded in Math.max()
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] arr = {1, 2, 9, 4, 5, 0, 4, 11, 6};

        System.out.println(recursive(arr.length-1, arr));

        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);

        System.out.println(memoize(arr.length-1, arr, dp));

        System.out.println(tabulate(arr));
    }
    static int recursive(int index, int[] arr){
        if(index == 0) return arr[0];
        if(index < 0) return 0;


        int notTake = recursive(index-1, arr);
        int take = arr[index] + recursive(index-2, arr);

        return Math.max(take, notTake);
    }
    static int memoize(int index, int[] arr, int[] dp){
        if(index == 0) return arr[0];
        if(index < 0) return 0;

        if(dp[index]!=-1) return dp[index];

        int notTake = memoize(index-1, arr, dp);
        int take = arr[index] + memoize(index-2, arr, dp);

        return dp[index] = Math.max(take, notTake);
    }
    static int tabulate(int[] arr){
        int[] dp = new int[arr.length + 1];

        dp[0] = 0; //corresponds to index<0
        dp[1] = arr[0]; //corresponds to index == 0

        for (int index = 2; index <= arr.length; index++) {
            int notTake = dp[index - 1];
            int take = arr[index - 1] + dp[index - 2];
            //arr[index-1] bcoz dp array is 1 index ahead of arr
            //so when for loop reaches arr.length, we have to put arr[arr.length-1] to access
            dp[index] = Math.max(take, notTake);
        }
        return dp[arr.length];
    }

}
