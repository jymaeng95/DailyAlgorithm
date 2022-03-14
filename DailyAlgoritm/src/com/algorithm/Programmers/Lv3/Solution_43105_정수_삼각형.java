package com.algorithm.Programmers.Lv3;

public class Solution_43105_정수_삼각형 {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int rst = solution(triangle);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[][] triangle) {
        int length = triangle.length;
        int[][] board = new int[length + 1][length + 1];
        int[][] dp = new int[length + 1][length + 1];

        for(int loop = 1; loop <= length; loop++) {
            for(int idx = 1; idx <= triangle[loop-1].length; idx++) {
                board[loop][idx] = triangle[loop-1][idx-1];
            }
        }
        int count = 0;
        for(int loop = 1; loop <= length; loop++) {
            for(int idx = 1; idx <= length; idx++) {
                dp[loop][idx] = Math.max(dp[loop][idx], Math.max(dp[loop-1][idx], dp[loop-1][idx-1])+board[loop][idx]);
                count = Math.max(count, dp[loop][idx]);
            }
        }
        return count;
    }
}
