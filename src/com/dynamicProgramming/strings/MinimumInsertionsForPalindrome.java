package com.dynamicProgramming.strings;

import java.util.Arrays;

public class MinimumInsertionsForPalindrome {
    //any string can be converted to palindrome by appending a reversed string of itself
    //hence the max answer will always be str.length()
    public static void main(String[] args) {
        String s1 = "abcdeda";
        String s2 = new StringBuilder(s1).reverse().toString();

        //the min insertions will be obtained when we maintain the palindrome that already exists
        //and add those chars which are not palindrome
        //remember this is very easy Q bcoz we are not asked about the position
        //we are only asked about how many insertions ? => str.length() - palindrome.length()
        int palindromicLength = tabulate(s1,s2);
        System.out.println(s1.length()-palindromicLength);
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

        //my code for printing subsequence
        // System.out.println(Arrays.deepToString(dp));
        // int max = 0;
        // StringBuilder sb = new StringBuilder();
        // for (int i = 1; i < dp.length; i++) {
        //     for (int j = 1; j < dp[0].length; j++) {
        //         if(dp[i][j] > max){
        //             max = dp[i][j];
        //             sb.append(str1.charAt(i-1));
        //         }
        //     }
        // }
        // System.out.println("Longest common subsequence: "  + sb);
        return dp[n][m];
    }
}
