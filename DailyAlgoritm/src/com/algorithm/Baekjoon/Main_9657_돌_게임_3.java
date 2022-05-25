package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9657_돌_게임_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] dp = new String[N + 1];

        String rst = stoneGame(N, dp);
        System.out.println(rst);

        br.close();
    }

    private static String stoneGame(int n, String[] dp) {
        if(n == 2) return "CY";
        if(n == 1 || n == 3 || n == 4) return "SK";

        dp[1] = dp[3] = dp[4] = "SK";
        dp[2] = "CY";

        for (int stone = 5; stone <= n; stone++) {
            if (dp[stone - 1].equals("CY")|| dp[stone - 3].equals("CY") || dp[stone - 4].equals("CY")) dp[stone] = "SK";
            else dp[stone] = "CY";
        }

        return dp[n];
    }
}
