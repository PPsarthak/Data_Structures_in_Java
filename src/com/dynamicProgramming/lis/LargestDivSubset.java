package com.dynamicProgramming.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Based on DP-44
 * Given an array form a subset (subsequence) where any 2 numbers are divisible with each other
 * you can print the subset in any order
 */
public class LargestDivSubset {
    public static void main(String[] args) {
        int[] arr = {1,16,7,8,4}; //1, 16, 8 , 4

        lds(arr);
    }
    //LIS code (from PrintLIS) but with a change in the 'if'
    //here we check whether curr element was divisible by prev added element
    //bcoz if it is divisible just by the prev element then ...
    //...it will be divisible by all prev elements too
    static void lds(int[] arr){
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int[] prev = new int[arr.length];

        //first sort them
        Arrays.sort(arr);

        for(int i=1; i< arr.length; i++){
            for (int j = 0; j < i; j++) {
                if(arr[i] % arr[j] == 0 && dp[i] < 1+dp[j]){
                    dp[i] = 1+dp[j];
                    prev[i] = j;
                }
            }
        }

        int index = -1;
        int max = 0;
        for(int i=0; i< arr.length; i++){
            if(arr[i]>max){
                max = arr[i];
                index = i;
            }
        }

        List<Integer> lis = new ArrayList<>();
        while(prev[index]!=index){
            lis.add(0, arr[index]);
            index = prev[index];
        }
        lis.add(0,arr[index]);
        System.out.println(lis);
    }
}
