package com.algorithm.LeetCode.greedy;

import java.util.Arrays;

public class SolutIon_1877_Minimize_Maximum_Pair_Sum_In_Array {
    public static void main(String[] args) {
        int[] nums = {3,5,2,3};
        int rst = minPairSum(nums);
        System.out.println("rst = " + rst);
    }

    private static int minPairSum(int[] nums) {
        Arrays.parallelSort(nums);
        int max = 0;
        for(int i=0; i<nums.length/2; i++) {
            max = Math.max(max, nums[i]+nums[nums.length-1-i]);
        }
        return max;
    }
}
