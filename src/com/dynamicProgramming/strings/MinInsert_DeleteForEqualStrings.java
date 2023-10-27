package com.dynamicProgramming.strings;

import java.util.Arrays;

public class MinInsert_DeleteForEqualStrings {
    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "anc";

        //any string can be converted to another by appending str2 to str1 and str1 to str2
        //therefore, in any case the max no of operations := n+m


        //Approach
        //to convert abcd to anc, we need to delete the chars which are not common in the 2 strings
        //from str1 := so b and d gets deleted
        //then we need to insert the chars which not common in the 2 strings from str2
        // so n is inserted

        int lcs = tabulate(str1, str2);
        System.out.println("The length of lcs: " + lcs);
        System.out.println("The no of deletions: " + (str1.length() - lcs));
        System.out.println("The no of insertions: " + (str2.length() - lcs));
        System.out.println("Total steps: " + (str1.length()+str2.length()-lcs-lcs));
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
