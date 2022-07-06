package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1520_내리막_길 {
    private static int N,M;
    private static int[][] map;
    private static final int[] DX = {0,0,-1,1};
    private static final int[] DY = {-1,1,0,0};
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];
        for(int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                dp[row][col] = -1;
            }
        }

        int rst = dfs(0,0);
        System.out.println(rst);
        br.close();
    }


    private static int dfs(int x, int y) {
        if(x == N-1 && y == M-1 ) {
            return 1;
        }

        if(dp[x][y] >= 0) return dp[x][y];

        dp[x][y] = 0;
        for(int idx = 0; idx < 4; idx++) {
            int nextX = x + DX[idx];
            int nextY = y + DY[idx];
            if(checkBound(nextX, nextY) && map[x][y] > map[nextX][nextY]) {
                dp[x][y] += dfs(nextX, nextY);
            }
        }

        return dp[x][y];
    }

    private static boolean checkBound(int nextX, int nextY) {
        return nextX >= 0 && nextX < N && nextY >= 0 && nextY < M;
    }
}
