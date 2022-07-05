package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1463_1로_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int rst = makeOne(N);
        System.out.println(rst);

        br.close();
    }

    private static int makeOne(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;

        for (int loop = n; loop >= 1; loop--) {
            dp[loop - 1] = Math.min(dp[loop - 1], dp[loop] + 1);
            if(loop % 2 == 0) dp[loop / 2] = Math.min(dp[loop / 2], dp[loop] + 1);
            if(loop % 3 == 0) dp[loop / 3] = Math.min(dp[loop / 3], dp[loop] + 1);
        }

        return dp[1];
    }
}
