package com.dynamicProgramming.lis;

import java.util.ArrayList;
import java.util.List;

/**
 * Based on DP-42
 * Given an array, print the longest increasing subsequence in the arr
 */

public class PrintLIS {
    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18}; //ans := 2,3,7,18
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

//        System.out.println(Arrays.deepToString(dp));

        //printing LIS
        int max = Integer.MIN_VALUE;

        int i = dp.length-1;
        int j = dp[0].length-1;

        List<Integer> list = new ArrayList<>();
        while (i>=0 && j>0){
            if(dp[i][j]>max){
                list.add(0,arr[j-1]);
                max = dp[i][j];
                i--;
                j--;
            }
            else{
                //compare left and top, and move in dir which is greater
                if(dp[i-1][j]>dp[i][j-1]){
                    i--;
                }
                else{
                    j--;
                }
            }
        }
        System.out.println(list);
        return dp[0][0];
    }
}
/*
{10,9,2,5,3,7,101,18};
    10 9  2  5  3  7 101 18
[4, 0, 0, 0, 0, 0, 0, 0, 0],
[4, 1, 0, 0, 0, 0, 0, 0, 0],
[4, 1, 1, 0, 0, 0, 0, 0, 0],
[3, 1, 1, 3, 0, 0, 0, 0, 0],
[3, 1, 1, 3, 2, 0, 0, 0, 0],
[2, 1, 1, 2, 2, 2, 0, 0, 0],
[1, 1, 1, 1, 1, 1, 1, 0, 0],
[1, 1, 1, 1, 1, 1, 1, 0, 0],
[0, 0, 0, 0, 0, 0, 0, 0, 0]]
 */