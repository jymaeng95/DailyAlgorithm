package com.algorithm.thisiscodingtest.DP;

public class P220_개미_전사 {
    public static void main(String[] args) {
        int N = 4;
        int[] K = {1, 3, 1, 5};

        int rst = antWarrior(N, K);
        System.out.println("rst = " + rst);
    }

    private static int antWarrior(int n, int[] k) {
        int[] dp = new int[n];
        dp[0] = k[0];
        dp[1] = Math.max(k[0], k[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + k[i]);
        }

        return dp[n - 1];
    }


}
