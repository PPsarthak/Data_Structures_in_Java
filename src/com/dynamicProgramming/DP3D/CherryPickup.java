package com.dynamicProgramming.DP3D;

import java.util.Arrays;

/**
 * Based on DP-13
 * Alice and Bob are looking to travel down a matrix (to reach at the base)
 * Alice starts at top left and Bob at top right
 * If they are at same element, the element is added only once
 */
public class CherryPickup {
    public static void main(String[] args) {
        int[][] arr = {
                {2, 3, 1, 2}, {3, 4, 2, 2}, {5, 6, 3, 5}
        };

        System.out.println(recursive(0,0,arr[0].length-1,arr));
        int[][][] dp = new int[arr.length][arr[0].length][arr[0].length];
        for(int[][] i : dp) for(int[] j : i) Arrays.fill(j,-1);

        System.out.println(memoize(0,0,arr[0].length-1,arr, dp));

        System.out.println(tabulate(arr));
    }
    static int recursive(int i, int j1, int j2, int[][] arr){
        if(j1<0 || j2<0 || j1>=arr[0].length || j2>=arr[0].length) return (int)(-1e8);
        if(i == arr.length-1){
            if(j1 == j2) return arr[i][j1];
            else return arr[i][j1]+arr[i][j2];
        }

        int ans = 0;
        for (int newJ1 = -1; newJ1 <= 1; newJ1++){
            for (int newJ2 = -1; newJ2 <= 1; newJ2++) {
                if(newJ1 == newJ2) {
                    ans = Math.max(ans, arr[i][j1] + recursive(i+1, j1+newJ1, j2+newJ2, arr));
                }
                else{
                    ans = Math.max(ans, arr[i][j1] + arr[i][j2] +recursive(i+1,j1+newJ1,j2+newJ2,arr));
                }
            }
        }
        return ans;
    }
    static int memoize(int i, int j1, int j2, int[][] arr, int[][][] dp){
        if(j1<0 || j2<0 || j1>=arr[0].length || j2>=arr[0].length) return (int)(-1e8);
        if(i == arr.length-1){
            if(j1 == j2) return arr[i][j1];
            else return arr[i][j1]+arr[i][j2];
        }

        if(dp[i][j1][j2]!=-1) return dp[i][j1][j2];

        int ans = 0;
        for (int newJ1 = -1; newJ1 <= 1; newJ1++){
            for (int newJ2 = -1; newJ2 <= 1; newJ2++) {
                if(newJ1 == newJ2) {
                    ans = Math.max(ans, arr[i][j1] + memoize(i+1, j1+newJ1, j2+newJ2, arr, dp));
                }
                else{
                    ans = Math.max(ans, arr[i][j1] + arr[i][j2] +memoize(i+1,1+newJ1,j2+newJ2,arr, dp));
                }
            }
        }
        return dp[i][j1][j2]=ans;
    }
    //straight up copied!!
    static int tabulate(int[][] arr){
        int[][][] dp = new int[arr.length][arr[0].length][arr[0].length];

        //base case
        for (int j1 = 0; j1 < arr[0].length; j1++) {
            for (int j2 = 0; j2 < arr[0].length; j2++) {
                if (j1 == j2)
                    dp[arr.length-1][j1][j2] = arr[arr.length-1][j1];
                else
                    dp[arr.length-1][j1][j2] = arr[arr.length-1][j1] + arr[arr.length-1][j2];
            }
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < arr[0].length; j1++) {
                for (int j2 = 0; j2 < arr[0].length; j2++) {
                    int maxi = Integer.MIN_VALUE;

                    // Inner nested loops to try out 9 options
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            int ans;

                            if (j1 == j2)
                                ans = arr[i][j1];
                            else
                                ans = arr[i][j1] + arr[i][j2];

                            // Check if the indices are valid
                            if ((j1 + di < 0 || j1 + di >= arr[0].length) || (j2 + dj < 0 || j2 + dj >= arr[0].length))
                                ans += (int) Math.pow(-10, 9);
                            else
                                ans += dp[i + 1][j1 + di][j2 + dj];

                            // Update maxi with the maximum result
                            maxi = Math.max(ans, maxi);
                        }
                    }
                    // Store the result in the dp array
                    dp[i][j1][j2] = maxi;
                }
            }
        }

        return dp[0][0][arr[0].length - 1];
    }
}
