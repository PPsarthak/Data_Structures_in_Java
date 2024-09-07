package com.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PowerSet {
    static List<List<Integer>> ans = new ArrayList<>();
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        List<Integer> temp = new ArrayList<>();
        getSet(0, nums, temp);
        System.out.println(ans);
    }
    static void getSet(int index, int[] nums, List<Integer> temp){
        if(index >= nums.length){
            ans.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[index]);
        getSet(index + 1, nums, temp);
        temp.remove(temp.size()-1);
        getSet(index + 1, nums, temp);
    }
}
