package com.dynamicProgramming.lis;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Based on DP-45
 * Given an array of string, get the longest increasing string chain
 */
public class LongestIncStringChain {
    public static void main(String[] args) {
//        String[] arr = {"a", "b", "ba", "bca", "bda", "bdca"};
        String[] arr = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        System.out.println(LICS(arr));
    }
    static int LICS(String[] arr){
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        //additional step
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });

        System.out.println(Arrays.toString(arr));

        for(int i=1; i< arr.length; i++){
            for (int j = 0; j < i; j++) {
                if(check(arr[i], arr[j])){
                    dp[i] = Math.max(dp[i], 1+dp[j]);
                }
            }
        }

        //getting the max
        int max = 0;
        for(int i : dp) max = Math.max(i, max);

        return max;
    }
    static boolean check(String s1, String s2){
        if(s2.length() < s1.length() && s1.length() != s2.length()+1) return false;

        int i = 0;
        int j = 0;
        while (i<s1.length()){
            if(j == s2.length()) break;
            if(s1.charAt(i) == s2.charAt(j)){
                i++;
                j++;
            }
            else{
                i++;
            }
        }
        return (i == s1.length() && j == s2.length());
    }
}
