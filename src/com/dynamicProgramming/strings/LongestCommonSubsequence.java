package com.dynamicProgramming.strings;

import java.util.Arrays;

/**
 * STRIVER DP-25 + My own implementation of DP-26
 * Given 2 strings, return the length (int) of the longest common subsequence/separate elements
 * in both strings
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String str1 = "abcdegkm";
        String str2 = "bdek";

        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

//        System.out.println(memoize(dp, n-1, m-1, str1, str2));


        System.out.println("Length of longest common subsequence " + tabulate(str1, str2));
    }

    /**
     * @TimeComplexity O(NxM)
     * @SpaceComplexity O(NxM):= dp matrix + O(N+M):= Auxiliary space
     * @param dp dp matrix
     * @param i current index of string 1
     * @param j current index of string 2
     * @param str1 string 1
     * @param str2 string 2
     * @return length of the longest common sequence
     */
    static int memoize(int[][] dp, int i, int j, String str1, String str2) {
        if(i<0 || j<0) return 0;
        if(dp[i][j]!=-1) return dp[i][j];

        if(str1.charAt(i) == str2.charAt(j)){
            //if matched move both pointers and compare rest of the string
            return dp[i][j] = 1+memoize(dp, i-1, j-1, str1, str2);
        }

        //if not matched, decrement both pointers one at a time
        //return the max of both outputs
        return dp[i][j] = Math.max(memoize(dp, i-1, j, str1, str2), memoize(dp, i, j-1, str1, str2));
    }

    /**
     * @TimeComplexity O(NxM)
     * @SpaceComplexity O(NxM):= dp matrix
     * @param str1 string 1
     * @param str2 string 2
     * @return length of the longest common sequence
     */
    static int tabulate(String str1, String str2){
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n+1][m+1];
        for(int[] i : dp) Arrays.fill(i, -1);

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for(int j = 0; j <= m; j++){
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) dp[i][j] = 1+dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        //my code for printing subsequence
        int max = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if(dp[i][j] > max){
                    max = dp[i][j];
                    sb.append(str1.charAt(i-1));
                }
            }
        }
        System.out.println("Longest common subsequence: "  + sb);
        return dp[n][m];
    }
}

/*

  a b c d g e k
c 0 0 0 0 0 0 0
d 0 0 0 0 0 0 0
e 1 1 1 1 1 1 1
f 1 2 2 2 2 2 2
g 1 2 2 2 3 3 3
x 1 2 3 3 3 3 3
z 1 1 2 3 3 3 3

String str1 = "abcdgek";
String str2 = "cdefgxz";
 */
