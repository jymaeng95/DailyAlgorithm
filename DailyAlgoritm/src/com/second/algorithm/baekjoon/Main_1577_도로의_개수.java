package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1577_도로의_개수 {
    private static int N, M;
    private static final int DISTRICT = -1;
    private static final int RIGHT = 0, DOWN = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long[][][] map = new long[M + 2][N + 2][2]; // 좌표이기 때문에 한칸 늘린다.
        int K = Integer.parseInt(br.readLine());

        for (int district = 0; district < K; district++) {
            st = new StringTokenizer(br.readLine());
            int startCol = Integer.parseInt(st.nextToken());
            int startRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());

            if (startRow == endRow) {
                if (endCol > startCol) map[endRow + 1][endCol + 1][RIGHT] = DISTRICT;
                else map[startRow + 1][startCol + 1][RIGHT] = DISTRICT;
            } else {
                if (endRow > startRow) map[endRow + 1][endCol + 1][DOWN] = DISTRICT;
                else map[startRow + 1][startCol + 1][DOWN] = DISTRICT;
            }
        }

        long rst = goSchool(map);
        System.out.println(rst);

        br.close();
    }

    private static long goSchool(long[][][] map) {
        long[][] dp = new long[M + 2][N + 2];
        dp[1][1] = 1;

        for (int row = 1; row <= M + 1; row++) {
            for (int col = 1; col <= N + 1; col++) {
                // 첫 위치는 경로가 있다.
                if (row == 1 && col == 1) continue;
                // 오른쪽, 아래 방향 체크
                for (int direction = 0; direction < 2; direction++) {
                    // 현재 방향이 공사중이 아니라면
                    if (map[row][col][direction] != DISTRICT) {
                        if (direction == RIGHT) dp[row][col] += dp[row][col - 1];
                        else dp[row][col] += dp[row - 1][col];
                    }
                }
            }
        }
        return dp[M + 1][N + 1];
    }
}
