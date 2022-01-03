package com.algorithm.thisiscodingtest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P380_Q34_병사_배치하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] soldier = new int[N];
        for (int i = 0; i < N; i++) {
            soldier[i] = Integer.parseInt(st.nextToken());
        }
        int rst = getOutSoldier(soldier, N);
        System.out.println(rst);
        br.close();
    }

    private static int getOutSoldier(int[] soldier, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < i; j++) {
                if (soldier[j] > soldier[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return soldier.length - max;
    }
}
