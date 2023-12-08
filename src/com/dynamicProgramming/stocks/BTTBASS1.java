package com.dynamicProgramming.stocks;

/**
 * Based on DP-35
 * Maximize the profit given the array of prices of stocks
 */
public class BTTBASS1 {
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4}; //ans: 1 and 6

        int min = arr[0];
        int profit = 0;
        for (int i = 1; i < arr.length; i++) {
            int todayCost = arr[i]-min;
            profit = Math.max(profit, todayCost);
            min = Math.min(min, arr[i]);
            //here's the dp, memoizing the prev min value in a variable
        }

        System.out.println(profit);
    }
}
