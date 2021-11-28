package com.algorithm.thisiscodingtest.DP;

import java.util.Arrays;

public class P228_효율적인_화폐_구성 {
    public static void main(String[] args) {
        int n = 2;
        int target = 15;
        int[] coins = {2, 3};
        int[] dp = new int[target + 1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        for (int coin : coins) {
            for (int j = coin; j <= target; j++) {
                dp[j] = Math.min(dp[j], dp[j-coin] + 1);
            }
        }
        System.out.println("dp[n] = " + dp[target]);
    }
}
