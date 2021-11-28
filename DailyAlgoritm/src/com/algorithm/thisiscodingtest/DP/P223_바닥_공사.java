package com.algorithm.thisiscodingtest.DP;

public class P223_바닥_공사 {
    public static void main(String[] args) {
        int N = 6;
        int[] dp = new int[N];

        dp[0] = 1;
        dp[1] = 3;
        for(int i=2; i<dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2] * 2;
        }

        System.out.println("dp[5] = " + dp[5]);
    }
}
