package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206_벽_부수고_이동하기 {
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int row = 0; row < N; row++) {
            String[] split = br.readLine().split("");
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(split[col]);
            }
        }

        int rst = move(map);
        System.out.println(rst);

        br.close();
    }

    private static int[] DX = {-1, 1, 0, 0};
    private static int[] DY = {0, 0, -1, 1};

    private static int move(int[][] map) {
        int count = Integer.MAX_VALUE;
        boolean[][][] check = new boolean[N][M][2];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, false, 1));
        Arrays.fill(check[0][0], true);
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int row = point.getRow();
            int col = point.getCol();

            if(count <= point.getCount()) continue;

            if (row == N - 1 && col == M - 1) {
                count = point.getCount();
                break;
            }

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = row + DX[direction];
                int nextCol = col + DY[direction];

                // 경계 확인
                if (checkBoundary(nextRow, nextCol)) {
                    // 현재 벽을 부수지 않은 상태인 경우
                    if (!point.isBrokenWall()) {
                        if(!check[nextRow][nextCol][1] && map[nextRow][nextCol] == 1) {
                            check[nextRow][nextCol][1] = true;
                            queue.add(new Point(nextRow, nextCol, true, point.getCount() + 1));
                        }
                        else if(!check[nextRow][nextCol][0] && map[nextRow][nextCol] == 0){
                            check[nextRow][nextCol][0] = true;
                            queue.add(new Point(nextRow, nextCol, false, point.getCount() + 1));
                        }
                    }
                    // 부순 상태인 경우
                    else {
                        if(!check[nextRow][nextCol][1] && map[nextRow][nextCol] == 0) {
                            check[nextRow][nextCol][1] = true;
                            queue.add(new Point(nextRow, nextCol, true, point.getCount() + 1));
                        }

                    }
                }
            }
        }

        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M;
    }

    static class Point {
        private final int row;
        private final int col;
        private final boolean brokenWall;
        private final int count;

        public Point(int row, int col, boolean brokenWall, int count) {
            this.row = row;
            this.col = col;
            this.brokenWall = brokenWall;
            this.count = count;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public boolean isBrokenWall() {
            return brokenWall;
        }

        public int getCount() {
            return count;
        }
    }
}