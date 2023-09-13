package com.dataStructures;

import java.util.Arrays;

public class FenwickTree {
    int[] FTree;
    int size;
    FenwickTree(int size){
        this.size = size+1;
        FTree = new int[size+1];
    }

    /**
     * Update any given index
     * @param value to update
     * @param i index where value is to be updated
     * @TimeComplexity O(log n) to the base 2 => only index that are multiple of 2 are updated
     */
    void update(int value, int i){
        //convert 0 based indexing to 1 based indexing
        i++;
        while(i<size){
            FTree[i] += value;
            i = i + (i&(-i));
            //2's compliment, '&' with i, add with i
        }
    }

    /**
     * Return the prefix sum till index i
     * @param i index
     * @return prefix sum
     * @TimeComplexity O(log n)
     */
    int get(int i){
        i++;
        int sum = 0;
        while(i>0){
            sum+=FTree[i];
            i = i-(i&(-i));
        }
        return sum;
    }

    /**
     * Return the prefix in the given range
     * @param left starting index of prefix sum
     * @param right ending index of prefix sum
     * @return prefix sum
     */
    int rangeSum(int left, int right){
        if(left < 0) return 0;
        return get(right) - get(left-1);
    }

    /**
     * Returns the index till which the prefix sum <= given value
     * @param value of sum whose lower bound is to be found
     * @return last index till prefix sum is less than given value
     */
    int lowerBoundPrefixSum(int value){
        int ans = 0;
        int currIdx = 0;
        int prevSum = 0;
        int start =  ((int)Math.log(size)/(int)Math.log(2));
        for(int i = start; i>=0; i--){
            /* Adjusting the value of 2's power while sum is less than value */
            if(FTree[currIdx + (1<<i)] + prevSum < value){
                currIdx += 1<<i;
                prevSum+=FTree[currIdx];
            }
        }
        return currIdx+1;
    }
    public static void main(String[] args) {
        int[] arr = {1,3,5};
        FenwickTree ft = new FenwickTree(arr.length);
        for (int i = 0; i < arr.length; i++) {
            ft.update(arr[i], i);
        }
        System.out.println(Arrays.toString(ft.FTree));
        System.out.println(ft.rangeSum(0,2));
    }
}
