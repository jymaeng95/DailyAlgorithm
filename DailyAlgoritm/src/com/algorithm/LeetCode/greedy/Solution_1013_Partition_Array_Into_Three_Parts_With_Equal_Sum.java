package com.algorithm.LeetCode.greedy;

public class Solution_1013_Partition_Array_Into_Three_Parts_With_Equal_Sum {
    public static void main(String[] args) {
        int[] arr = {0,0,0,0,0,0,0};
        boolean ans = canThreePartsEqualSum(arr);
        System.out.println("ans = " + ans);
    }

    private static boolean canThreePartsEqualSum(int[] arr) {
        int count = 0;
        int sum = 0;
        int parts = 0;

        for(int x : arr) sum += x;

        if(sum%3 != 0) return false;

        for(int x : arr) {
            parts += x;
            if(parts == sum/3) {
                parts = 0;
                count++;
            }
        }

        return count >= 3;
    }
}
