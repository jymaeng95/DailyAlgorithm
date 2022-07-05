package com.algorithm.Baekjoon.first;

import java.io.*;

public class Main_2156_포도주_시식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] wine = new int[N + 1];
        for (int index = 1; index <= N; index++) {
            wine[index] = Integer.parseInt(br.readLine());
        }
        int maxWine = wine[1];
        if (N > 1) {
            maxWine = getMaxWine(N, wine);
        }

        System.out.println(maxWine);

        br.close();
    }

    private static int getMaxWine(int n, int[] wine) {
        int[] dp = new int[n + 1];
        dp[1] = wine[1];
        dp[2] = wine[1] + wine[2];

        int max = Math.max(dp[1], dp[2]);
        for (int index = 3; index <= n; index++) {
            dp[index] = Math.max(dp[index-1],wine[index] + Math.max(wine[index - 1] + dp[index - 3], dp[index - 2]));
            max = Math.max(dp[index], max);
        }

        return max;
    }
}
