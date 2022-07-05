package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2579_계단_오르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N + 1];

        for (int index = 1; index <= N; index++) {
            stairs[index] = Integer.parseInt(br.readLine());
        }
        int rst = stairs[1];
        if (N > 1) {
            rst = getStairs(N, stairs);
        }
        System.out.println(rst);

        br.close();
    }

    private static int getStairs(int n, int[] stairs) {
        // 0 1 3  3 5 6-> 4 마다 DP
        // 0 2 3, 3 4 6 ,
        int[] dp = new int[n + 1];
        dp[1] = stairs[1];
        dp[2] = Math.max(stairs[2], stairs[2] + dp[1]);

        for (int index = 3; index <= n; index++) {
            dp[index] = stairs[index] + Math.max(stairs[index - 1] + dp[index - 3], dp[index - 2]);
        }

        return dp[n];
    }
}
