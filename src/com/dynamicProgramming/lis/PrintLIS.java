package com.dynamicProgramming.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Based on DP-42
 * Given an array, print the longest increasing subsequence in the arr
 */

public class PrintLIS {
    public static void main(String[] args) {
        int[] arr = {5,4,11,1,16,8}; //ans := 5,11,16 or 4,11,16
        System.out.println(preRequisite(arr));
        printLIS(arr);
    }
    static int preRequisite(int[] arr){
        //5,4,11,1,16,8
        //1 1  2 1  3 2 := 11 => 2 meaning longest LIS till 11 is of len = 2
        //each element tells us how long the lis is from this index
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        for(int i=1; i< arr.length; i++){
            for (int j = 0; j < i; j++) {
                if(arr[j]<arr[i]){
                    dp[i] = Math.max(dp[i], 1+dp[j]);
                }
            }
        }

        //getting the max
        int max = 0;
        for(int i : dp) max = Math.max(i, max);

        return max;
    }
    static void printLIS(int[] arr){
        //we use an extra array, in the previous approach, that stores the prev index of LIS
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        int[] prev = new int[arr.length];
//        Arrays.fill(prev, 0);

        for(int i=1; i< arr.length; i++){
            for (int j = 0; j < i; j++) {
                if(arr[j]<arr[i] && dp[i] < 1+dp[j]){
                    dp[i] = 1+dp[j];
                    prev[i] = j;
                }
            }
        }

        //get the index where the max val is present in dp
        int index = -1;
        int max = 0;
        for(int i=0; i< arr.length; i++){
            if(arr[i]>max){
                max = arr[i];
                index = i;
            }
        }

        //now start hopping/backtracking from this index in the prev array
        List<Integer> lis = new ArrayList<>();
        while(prev[index]!=index){
            lis.add(0, arr[index]);
            index = prev[index];
        }
        lis.add(0,arr[index]);
        System.out.println(lis);
    }
}