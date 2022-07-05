package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1912_연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sequence = new int[N+1];

        for(int index = 1; index <= N; index++) {
            sequence[index] = Integer.parseInt(st.nextToken());
        }

        int rst = getMaxSum(sequence, N);
        System.out.println(rst);
        br.close();
    }

    private static int getMaxSum(int[] sequence, int n) {
        int[] dp = new int[n + 1];
        dp[1] = sequence[1];

        int max = Integer.MIN_VALUE;
        for (int index = 1; index <= n; index++) {
            dp[index] = Math.max(sequence[index], Math.max(sequence[index] + dp[index - 1], sequence[index] + sequence[index - 1]));
            max = Math.max(dp[index], max);
        }

        return max;
    }
}
