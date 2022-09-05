package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1520_내리막_길 {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = reachEndpoint(map);
        System.out.println(rst);

        br.close();
    }

    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int reachEndpoint(int[][] map) {
        int[][][] visited = new int[N][M][4];
        int[][] dp = new int[N][M];
        dp[0][0] = 1;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int row = point.getRow();
            int col = point.getCol();

            // dp 갱신
            int count = 0;
            for (int direction = 0; direction < 4; direction++) {
                count += visited[row][col][direction];
            }
            dp[row][col] = Math.max(dp[row][col], count);

            // N, M에 도착하는 경우
            if (row == N - 1 && col == M - 1) continue;

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = row + DX[direction];
                int nextCol = col + DY[direction];
                // 경계 벗어나지 않고, 다음 방향 방문횟수가 더 큰 경우에만 넣고, 현재 지도에서 다음 위치 높이가 더 작은 경우만 넣기
                if (checkBoundary(nextRow, nextCol) && visited[nextRow][nextCol][direction] < dp[row][col] && map[row][col] > map[nextRow][nextCol]) {
                    visited[nextRow][nextCol][direction] = dp[row][col];
                    queue.add(new Point(nextRow, nextCol));
                }
            }
        }

        return dp[N - 1][M - 1];
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M;
    }

    static class Point {
        private int row;
        private int col;


        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
