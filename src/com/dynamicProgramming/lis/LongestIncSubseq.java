package com.dynamicProgramming.lis;

import java.util.Arrays;

public class LongestIncSubseq {
    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};

        System.out.println(recursive(arr, 0, -1));

        int[][] dp = new int[arr.length][arr.length+1];
        for (int[] i : dp) Arrays.fill(i, -1);

        System.out.println(memoize(arr, 0, -1, dp));

        System.out.println(tabulate(arr));
    }
    static int recursive(int[] arr, int index, int prev){
        if(index >= arr.length){
            return 0;
        }

        //not picked:= do this irrespective of whether we picked or not
        int notPick = recursive(arr, index+1, prev);

        int pick = 0;
        //pick only if curr > prev || prev = -1 (we haven't picked any yet)
        if(prev == -1 || arr[index] > arr[prev]){
            pick = 1+recursive(arr, index+1, index);
        }

        return Math.max(pick, notPick);
    }
    static int memoize(int[] arr, int index, int prev, int[][] dp){
        if(index >= arr.length){
            return 0;
        }

        if(dp[index][prev+1] != -1) return dp[index][prev+1];

        //not picked:= do this irrespective of whether we picked or not
        int notPick = memoize(arr, index+1, prev, dp);

        int pick = 0;
        //pick only if curr > prev || prev = -1 (we haven't picked any yet)
        if(prev == -1 || arr[index] > arr[prev]){
            pick = 1+memoize(arr, index+1, index, dp);
        }

        return dp[index][prev+1] = Math.max(pick, notPick);
    }
    static int tabulate(int[] arr){
        int[][] dp = new int[arr.length+1][arr.length+1];

        //base case := unnecessary since all are already zero but still ...
        for (int i = 0; i < dp[0].length; i++) {
            dp[arr.length][i] = 0;
        }

        for(int index=arr.length-1; index>=0; index--){
            for(int prev=index-1; prev>=-1; prev--){
                //not picked
                int ans = dp[index+1][prev+1];
                if(prev == -1 || arr[index] > arr[prev]){
                    ans = Math.max(ans,1+dp[index+1][index+1]);
                }
                dp[index][prev+1] = ans;
            }
        }

        return dp[0][0];
    }
}
