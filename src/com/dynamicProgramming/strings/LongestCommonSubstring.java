package com.dynamicProgramming.strings;

import java.util.Arrays;

public class LongestCommonSubstring {
    /*
    Please understand how this tabulation matrix is different from LCSubsequence
         a c d e b c a
      [0,0,0,0,0,0,0,0],
    a [0,1,0,0,0,0,0,1],
    b [0,0,0,0,0,1,0,0],
    c [0,0,1,0,0,0,2,0],
    d [0,0,0,2,0,0,0,0],
    e [0,0,0,0,3,0,0,0],
    b [0,0,0,0,0,4,0,0],
    c [0,0,1,0,0,0,5,0]
    Here the each element corresponds to whether the charAt(i) and charAt(j) matches or not
    If they match, then we look for the value at i-1 and j-1, and increment it by 1
     */
    public static void main(String[] args) {
        String str1 = "abcdebc";
        String str2 = "acdebca";

        System.out.println(tabulate(str1, str2));
    }
    static int tabulate(String str1, String str2){
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        for(int j = 0; j < m; j++){
            dp[0][j] = 0;
        }

        int maxLen = 0;
        int endIndex = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        endIndex = i - 1; // Update the ending index
                    }
                }
                else{
                    dp[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        System.out.println(str1.substring(endIndex - maxLen + 1, endIndex + 1));
        return maxLen;
    }
}
/*
abcdebc
     a c d e b c a
  [0,0,0,0,0,0,0,0],
a [0,1,0,0,0,0,0,1],
b [0,0,0,0,0,1,0,0],
c [0,0,1,0,0,0,2,0],
d [0,0,0,2,0,0,0,0],
e [0,0,0,0,3,0,0,0],
b [0,0,0,0,0,4,0,0],
c [0,0,1,0,0,0,5,0]
 */