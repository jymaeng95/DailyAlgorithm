package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프_옮기기_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                house[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = movePipe(N, house);
        System.out.println(rst);
        br.close();
    }

    private static final int HORIZON = 0, VERTICAL = 1,  CROSS = 2;

    private static int movePipe(int n, int[][] house) {
        int[][][] dp = new int[n][n][3];
        dp[0][0][0] = dp[0][1][0] = 1;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // 첫번째는 파이프가 놓여 있기 때문에 첫번째 행은 1부터 시작
                if(row == 0 && col == 0) continue;
                // 가로
                if (checkBound(HORIZON, row, col, house, n)) {
                    int nextRow = row + DX[HORIZON];
                    int nextCol = col + DY[HORIZON];
                    // 이전에 대각선이나 가로로 들어온 경우에만 현재 파이프가 가로로 들어올 수 있음
                    dp[nextRow][nextCol][HORIZON] = dp[row][col][HORIZON] + dp[row][col][CROSS];
                }
                // 세로
                if (checkBound(VERTICAL, row, col, house, n)) {
                    int nextRow = row + DX[VERTICAL];
                    int nextCol = col + DY[VERTICAL];
                    // 이전에 대가건이나 세로로 들어온 경우에만 현재 파이프가 세로로 들어오는 것이 가능
                    dp[nextRow][nextCol][VERTICAL] = dp[row][col][VERTICAL] + dp[row][col][CROSS];
                }
                // 대각선
                if (checkBound(CROSS, row, col, house, n)) {
                    // 모든 방향에서 들어오는 것이 가능
                    int nextRow = row + DX[CROSS];
                    int nextCol = col + DY[CROSS];
                    dp[nextRow][nextCol][CROSS] = dp[row][col][HORIZON] + dp[row][col][VERTICAL] + dp[row][col][CROSS];
                }
            }
        }

        return dp[n-1][n-1][HORIZON] + dp[n-1][n-1][VERTICAL] + dp[n-1][n-1][CROSS];
    }

    private static final int[] DX = {0, 1, 1};
    private static final int[] DY = {1, 0, 1};

    private static boolean checkBound(int type, int row, int col, int[][] house, int n) {
        if (type != CROSS) {
            int nextRow = row + DX[type];
            int nextCol = col + DY[type];

            return nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n && house[nextRow][nextCol] != 1;
        }

        // 대각선 이동 경우 범위 판단
        for (int direction = 0; direction < 3; direction++) {
            int nextRow = row + DX[direction];
            int nextCol = col + DY[direction];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || house[nextRow][nextCol] == 1)
                return false;
        }
        return true;
    }
}
