package com.dynamicProgramming.lis;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Based on DP-45
 * Given an array of string, get the longest increasing string chain
 * Here, subsequence is not required i.e., you can select elements in any order
 * For e.g., {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"}
 *  Here, you can select := xb then xbc then cxbc, pcxbc, pcxbcf (ans = 5)
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
        Arrays.sort(arr, (o1, o2) -> o1.length()-o2.length());

//        System.out.println(Arrays.toString(arr));

        int max = 1;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (check(arr[i], arr[j]) && 1 + dp[j] > dp[i]) {
                    dp[i] = 1 + dp[j];
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }
    static boolean check(String s1, String s2){
        if(s2.length() < s1.length() && s1.length() != s2.length()+1) return false;

        int i = 0;
        int j = 0;
        while (i<s1.length()){
            if(j < s2.length() && s1.charAt(i) == s2.charAt(j)){
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
