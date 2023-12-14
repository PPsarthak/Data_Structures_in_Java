package com.dynamicProgramming.lis;

import java.util.Arrays;

/**
 * Based on DP-46
 * Given an array find the length of the longest bitonic subsequence
 * Bitonic means, subsequence is:
 *      - only (strictly) increasing
 *      - only (strictly) decreasing
 *      - (strictly) increasing then (strictly) decreasing
 */
public class LongestBitonicSubseq {
    public static void main(String[] args) {
        int[] arr = {1,11,2,10,4,5,2,1};

        System.out.println(lbs(arr));
    }
    static int lbs(int[] arr){
        int[] lis = new int[arr.length];
        Arrays.fill(lis, 1);

        for(int i=1; i< arr.length; i++){
            for (int j = 0; j < i; j++) {
                if(arr[j]<arr[i]){
                    lis[i] = Math.max(lis[i], 1+lis[j]);
                }
            }
        }

        System.out.println(Arrays.toString(lis));

        int[] lds = new int[arr.length];
        Arrays.fill(lds, 1);

        for(int i= arr.length-1; i>=0; i--){
            for (int j = arr.length-1; j > i; j--) {
                if(arr[j]<arr[i]){
                    lds[i] = Math.max(lds[i], 1+lds[j]);
                }
            }
            //the below for loop can come here
            //max = Math.max(max, (lis[i]+lds[i]-1));
        }

        System.out.println(Arrays.toString(lds));

        //getting the max
        int max = 0;
        for(int i=0; i< arr.length; i++){
            max = Math.max(max, (lis[i]+lds[i]-1));
            //subtract one because lis[] stores LIS from 0 to curr index
            //and lds[] stores LDS from curr index to n
            //hence curr index is counted twice
         }

        return max;
    }
}
