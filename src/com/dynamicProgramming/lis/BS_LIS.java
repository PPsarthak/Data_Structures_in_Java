package com.dynamicProgramming.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Based on DP-43
 * Used only to get the length of LIS, not for printing the LIS
 */
public class BS_LIS {
    public static void main(String[] args) {
        int[] arr = {1,7,8,4,5,6,-1,9};
        //1 4 5 6 9 => ans = 5

        System.out.println(binarySearchLIS(arr));
    }
    static int binarySearchLIS(int[] arr){
        List<Integer> list = new ArrayList<>();

        //initially add the first element
        list.add(arr[0]);

        //now we add elements only in the increasing order
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > list.get(list.size()-1)){
                list.add(arr[i]);
            }
            else{
                int index = Arrays.binarySearch(list.toArray(), arr[i]);
                System.out.println(list + " " + (-1*index-1) + " " + arr[i]);
                list.set(-1*index-1, arr[i]);
            }
        }
        System.out.println(list);
        return list.size();
    }
}
