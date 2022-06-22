package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1309_동물원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long rst = settingLion(N);
        System.out.println(rst);

        br.close();
    }

    private static long settingLion(int n) {
        // XX -> 3 , XO -> 2, OX -> 2
        long[][] dp = new long[3][n+1];

        dp[0][1] = 1; // XO
        dp[1][1] = 1; // XX
        dp[2][1] = 1; // OX

        for (int size = 2; size <= n; size++) {
            // XO -> XX와 OX인 경우에 사자 배치 가능
            dp[0][size] = (dp[1][size - 1] + dp[2][size - 1]) % 9901;

            // XX -> 모든 경우에 사자 배치 안할 수 있음
            dp[1][size] = (dp[0][size - 1] + dp[1][size - 1] + dp[2][size - 1]) % 9901;

            // OX -> XX와 XO인 경우에 사자 배치 가능
            dp[2][size] = (dp[0][size - 1] + dp[1][size - 1]) % 9901;
        }
        return (dp[0][n] + dp[1][n] + dp[2][n]) % 9901;
    }
}
