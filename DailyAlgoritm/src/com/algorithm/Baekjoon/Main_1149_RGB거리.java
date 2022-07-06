package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1149_RGB거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] rgb = new int[N][3];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 3; col++) {
                rgb[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = getMinRGB(N, rgb);
        System.out.println(rst);

        br.close();
    }

    private static int getMinRGB(int n, int[][] rgb) {
        int[][] dp = new int[n][3];
        for(int row = 0; row < n; row++) {
            Arrays.fill(dp[row], Integer.MAX_VALUE);
        }

        /**
         * R : 이전 g,b + 현재 r
         * G : 이전 r,b + 현재 g
         * B : 이전 r,g + 현재 b
         */
        dp[0] = rgb[0];
        int rst = Integer.MAX_VALUE;
        for(int row = 1; row < n; row++) {
            // R
            dp[row][0] = rgb[row][0] + Math.min(dp[row - 1][1], dp[row - 1][2]);
            dp[row][1] = rgb[row][1] + Math.min(dp[row - 1][0], dp[row - 1][2]);
            dp[row][2] = rgb[row][2] + Math.min(dp[row - 1][0], dp[row - 1][1]);

            rst = Math.min(dp[row][0], Math.min(dp[row][1], dp[row][2]));
        }

        return rst;
    }
}
