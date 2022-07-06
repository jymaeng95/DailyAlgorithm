package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_17212_달나라_토끼를_위한_구매대금_지불_도우미 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int rst = countCoin(N);
        System.out.println(rst);

        br.close();
    }

    private static int countCoin(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 100001);
        int[] coins = {1, 2, 5, 7};
        if (n == 0) return 0;
        if (n == 1 || n == 2 || n == 5 || n == 7) return 1;
        if (n >= 7) dp[7] = 1;
        if (n >= 5) dp[5] = 1;
        if (n >= 2) dp[2] = 1;
        if (n >= 1) dp[1] = 1;
        for (int coin : coins) {
            for (int price = 0; price <= n; price++) {
                if (price >= coin) {
                    dp[price] = Math.min(dp[price], dp[price - coin] + 1);
                }
            }
        }

        return dp[n];
    }
}
