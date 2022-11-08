package com.second.algorithm.programmers;

public class Solution_68646_풍선_터뜨리기 {
    public static void main(String[] args) {
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        int rst = solution(a);
        System.out.println("rst = " + rst);
    }

    private static final int LEFT = 0, RIGHT = 1;

    private static int solution(int[] a) {
        int[][] dp = new int[2][a.length + 2];

        dp[LEFT][0] = Integer.MAX_VALUE;
        dp[RIGHT][a.length + 1] = Integer.MAX_VALUE;

        for (int leftIndex = 1, rightIndex = a.length; leftIndex <= a.length; leftIndex++, rightIndex--) {
            dp[LEFT][leftIndex] = Math.min(dp[LEFT][leftIndex - 1], a[leftIndex - 1]);
            dp[RIGHT][rightIndex] = Math.min(dp[RIGHT][rightIndex + 1], a[rightIndex - 1]);
        }

        int count = 0;
        for (int index = 1; index <= a.length; index++) {
            if(a[index - 1] < dp[LEFT][index-1] || dp[RIGHT][index + 1] > a[index - 1]) count++;
        }

        return count;
    }
}
