package com.dynamicProgramming.lis;

import java.util.Arrays;

/**
 * Based on DP-47
 * Count the number of LIS of length = max length of LIS
 */
public class NoOfLIS {
    public static void main(String[] args) {
        int[] arr = {5,4,11,1,16,8};

        countLIS(arr);
    }
    static void countLIS(int[] arr){
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        int[] count = new int[arr.length];
        Arrays.fill(count, 1);

        int max = 0;
        for(int i=1; i< arr.length; i++){
            for (int j = 0; j < i; j++) {
                if(arr[j]<arr[i]){
                    if(dp[i] < 1 + dp[j]){
                        dp[i] = 1 + dp[j];
                        count[i] = count[j];
                    }
                    else if(dp[i] == 1 + dp[j]){
                        count[i]+=count[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(count));
        //getting the count and max

        int cnt = 0;
        for(int i=0; i< dp.length; i++){
            if(dp[i] == max) cnt += count[i];
        }

        System.out.println("Length of LIS: " + max);
        System.out.println("Count of LIS: " + cnt);
    }
}
