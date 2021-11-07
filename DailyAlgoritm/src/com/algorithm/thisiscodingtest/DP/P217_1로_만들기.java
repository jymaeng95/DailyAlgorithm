package com.algorithm.thisiscodingtest.DP;

public class P217_1로_만들기 {
    public static void main(String[] args) {
        int x = 26;

        int rst = makeOne(x);
        System.out.println("rst = " + rst);
    }

    private static int makeOne(int x) {
        int[] dp = new int[x + 1];
        for (int i = 2; i <= x; i++) {
            dp[i] = dp[i - 1] + 1;
            if (x % 5 == 0) {
                dp[i] = Math.min(dp[x / 5] + 1, dp[i]);
            }
            if (x % 3 == 0) {
                dp[i] = Math.min(dp[x / 3] + 1, dp[i]);
            }
            if (x % 2 == 0) {
                dp[i] = Math.min(dp[x / 2] + 1, dp[i]);
            }
        }
        return dp[x];
    }
}
