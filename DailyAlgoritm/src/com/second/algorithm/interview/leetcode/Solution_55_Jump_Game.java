package com.second.algorithm.interview.leetcode;

public class Solution_55_Jump_Game {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};
        boolean rst = canJump(nums);
        System.out.println("rst = " + rst);
    }

    private static boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for(int index = 1; index < nums.length; index++) {
            if(dp[index - 1] == 0 && index - 1 < nums.length - 1) return false;

            dp[index] = Math.max(dp[index - 1] - 1, nums[index]);
        }

        return true;
    }
}
