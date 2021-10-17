package com.algorithm.LeetCode.simulation;

public class Solution_1920_Build_Array_from_Permutation {
    public static void main(String[] args) {
        int[] nums = {0,2,1,5,3,4};
        int[] ints = buildArray(nums);
    }

    private static int[] buildArray(int[] nums) {
        int[] arr = new int[nums.length];

        for(int i=0; i<nums.length; i++) {
            arr[i] = nums[nums[i]];
        }

        return arr;
    }
}
