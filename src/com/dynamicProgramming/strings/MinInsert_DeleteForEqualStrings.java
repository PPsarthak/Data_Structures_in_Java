package com.dynamicProgramming.strings;

import java.util.Arrays;

public class MinInsert_DeleteForEqualStrings {
    public static void main(String[] args) {
        String str1 = "abcdefhg";
        String str2 = "abcfedg";
        System.out.println(tabulate(str1,str2));
    }
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

        return dp[n][m];
    }
}
