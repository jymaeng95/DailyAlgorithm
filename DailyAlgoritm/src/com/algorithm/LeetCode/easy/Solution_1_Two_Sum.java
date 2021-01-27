package com.algorithm.LeetCode.easy;

public class Solution_1_Two_Sum {
    public static void main(String[] args) {
        int[] nums = {2,7,11,5};
        int[] ints = twoSum(nums, 9);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        boolean flag = false;
        int[] index = new int[2];
        for(int i =0;i<nums.length;i++){
            for(int j =i+1;j<nums.length;j++){
                if(nums[i]+nums[j] == target) {
                    flag = true;
                    index = new int[]{i,j};
                    break;
                }
            }
            if(flag) {
                break;
            }
        }
        return index;
    }
}
