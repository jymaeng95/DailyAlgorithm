package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1937_욕심쟁이_판다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] forest = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                forest[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = pandasPosition(N, forest);
        System.out.println(rst);
        br.close();
    }

    private static int count;
    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {1, -1, 0, 0};
    private static int[][] dp;

    private static int pandasPosition(int n, int[][] forest) {
        count = 1;
        dp = new int[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                movePandas(row, col, n, forest);
            }
        }

        return count;
    }

    private static int movePandas(int row, int col, int n, int[][] forest) {
        if(dp[row][col] != 0) return dp[row][col];

        dp[row][col] = 1;
        for (int direction = 0; direction < 4; direction++) {
            int nextRow = DX[direction] + row;
            int nextCol = DY[direction] + col;

            if (checkBound(nextRow, nextCol, n) && forest[row][col] < forest[nextRow][nextCol]) {
                dp[row][col] = Math.max(dp[row][col], movePandas(nextRow, nextCol, n, forest)+1);
                count = Math.max(count, dp[row][col]);
            }
        }

        return dp[row][col];
    }


    private static boolean checkBound(int nextRow, int nextCol, int n) {
        return nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n;
    }
}
