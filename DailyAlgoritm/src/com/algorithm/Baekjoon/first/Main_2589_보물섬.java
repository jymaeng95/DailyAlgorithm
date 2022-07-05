package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2589_보물섬 {
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[][] treasure = new String[N][M];
        for(int row = 0; row < N; row++) {
            String[] split = br.readLine().split("");
            for(int col = 0; col < M; col++) {
                treasure[row][col] = split[col];
            }
        }

        int rst = getTreasure(N, M, treasure);
        System.out.println(rst);
        br.close();
    }

    private static final int[] DX = {0, 0, 1, -1};
    private static final int[] DY = {1, -1, 0, 0};
    private static int time;
    private static int getTreasure(int n, int m, String[][] treasure) {
        time = 0;
        for(int row =0; row < n; row ++) {
            for(int col =0; col < m; col++) {
                if (treasure[row][col].equals("L")) {
                    nextTreasure(row, col, n, m, treasure);
                }
            }
        }
        return time;
    }

    private static void nextTreasure(int row, int col, int n, int m, String[][] treasure) {
        boolean[][] visited = new boolean[n][m];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));
        int hour = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int index = 0; index < size; index++) {
                Point point = queue.poll();
                int curRow = point.getRow();
                int curCol = point.getCol();
                if(visited[curRow][curCol]) continue;
                visited[curRow][curCol] = true;
                for(int direction = 0; direction < 4; direction++) {
                    int nextRow = curRow + DX[direction];
                    int nextCol = curCol + DY[direction];
                    if (checkBound(nextRow, nextCol, n, m) && !visited[nextRow][nextCol] && treasure[nextRow][nextCol].equals("L")) {
                        queue.add(new Point(nextRow, nextCol));
                    }
                }
            }
            hour++;
        }

        time = Math.max(time, hour-1);
    }

    private static boolean checkBound(int nextRow, int nextCol, int n, int m) {
        return nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m;
    }
}
