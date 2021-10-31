package com.algorithm.LeetCode.greedy;

public class Solution_991_Broken_Calculator {
    public static void main(String[] args) {
        int startValue = 68;
        int target = 71;

        int rst = brokenCalc(startValue, target);
        System.out.println("rst = " + rst);
    }

    private static int brokenCalc(int startValue, int target) {
        if (startValue > target) return startValue - target;
        if (startValue == target) return 0;
        int count = 0;
        while (startValue < target) {
            count++;
            if (target % 2 == 0) {
                target /= 2;
            } else {
                target += 1;
            }
        }

        return count + startValue - target;
    }
}
