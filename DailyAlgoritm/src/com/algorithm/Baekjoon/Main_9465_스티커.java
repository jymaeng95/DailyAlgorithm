package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9465_스티커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int loop = 0; loop < T; loop++) {
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][N];

            for (int row = 0; row < 2; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {
                    sticker[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            int rst = getMaxScore(N, sticker);
            System.out.println(rst);
        }

        br.close();
    }

    private static int getMaxScore(int n, int[][] sticker) {
        if (n == 1) return Math.max(sticker[0][0], sticker[1][0]);
        if (n == 2) return Math.max(sticker[0][0] + sticker[1][1], sticker[0][1] + sticker[1][0]);

        // 각 열마다 획득할 수 있는 최대 점수
        int[][] dp = new int[2][n];
        dp[0][0] = sticker[0][0];
        dp[1][0] = sticker[1][0];
        dp[0][1] = dp[1][0] + sticker[0][1];
        dp[1][1] = dp[0][0] + sticker[1][1];

        /**
         * row : 현재 행, col : 현재 열
         * 1. sticker[row][col]을 뜯는 경우 기준
         * 2. dp[0][col-2], dp[1][col-2] 처럼 한 칸 띄워진 경우이므로 두 행의 점수 중 큰 값을 가져와 더해줄 수 있다.
         * 3. dp[prevRow][col - 1] 현재 스티커 기준 이전 열의 대각선에 위치한 점수만 가져올 수 있다.
         * 점화식 : dp[row][col] = Math.max(sticker[row][col] + dp[prevRow][col - 1], Math.max(sticker[row][col] + dp[0][col - 2], sticker[row][col] + dp[1][col - 2]))
         */
        for (int col = 2; col < n; col++) {
            for (int row = 0; row < 2; row++) {
                int prevRow = row == 1 ? 0 : 1;
                dp[row][col] = Math.max(sticker[row][col] + dp[prevRow][col - 1], Math.max(sticker[row][col] + dp[0][col - 2], sticker[row][col] + dp[1][col - 2]));
            }
        }

        return Math.max(dp[0][n - 1], dp[1][n - 1]);
    }
}