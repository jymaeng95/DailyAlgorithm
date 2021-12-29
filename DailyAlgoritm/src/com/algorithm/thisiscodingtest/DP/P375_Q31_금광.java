package com.algorithm.thisiscodingtest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P375_Q31_금광 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[][] cave = new int[N + 2][M];
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k < M; k++) {
                    cave[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int rst = getMaxGold(N, M, cave);
            System.out.println("rst = " + rst);
        }


        br.close();
    }

    private static int getMaxGold(int n, int m, int[][] cave) {
        int[][] dp = new int[n + 2][m];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = cave[i][0];
            max = Math.max(max, dp[i][0]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[j][i] = Math.max(dp[j - 1][i - 1] + cave[j][i], Math.max(dp[j][i - 1] + cave[j][i], dp[j + 1][i - 1] + cave[j][i]));
                max = Math.max(max, dp[j][i]);
            }
        }

        return max;
    }
}
