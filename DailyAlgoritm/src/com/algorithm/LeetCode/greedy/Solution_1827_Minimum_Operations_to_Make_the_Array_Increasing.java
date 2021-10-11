package com.algorithm.LeetCode.greedy;

public class Solution_1827_Minimum_Operations_to_Make_the_Array_Increasing {
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int count = minOperations(nums);
        System.out.println(count);
    }

    private static int minOperations(int[] nums) {
        int count = 0;
        int present = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(present >= nums[i]) {
                int plus = present - nums[i] + 1;
                nums[i] += plus;
                count += plus;
            }
            present = nums[i];
        }
        return count;
    }
}
