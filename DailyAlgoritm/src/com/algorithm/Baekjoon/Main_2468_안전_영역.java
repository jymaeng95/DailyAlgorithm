package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2468_안전_영역 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] region = new int[N][N];

        int maxHeight = 0;
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int height = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, height);
                region[row][col] = height;
            }
        }

        int rst = maxSafeArea(maxHeight, N, region);
        System.out.println(rst);
        br.close();
    }

    private static int maxSafeArea(int maxHeight, int n, int[][] region) {
        int maxSafeArea = 0;
        // 높이 1부터 최대 높이까지 완탐 진행
        for (int height = 0; height <= maxHeight; height++) {
            boolean[][] visited = new boolean[n][n];
            int safeArea = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (!visited[row][col] && region[row][col] > height) {
                        getSafeArea(row, col, visited, n, height, region);
                        safeArea++;
                    }
                }
            }
            maxSafeArea = Math.max(safeArea, maxSafeArea);
        }
        return maxSafeArea;
    }

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    private static void getSafeArea(int row, int col, boolean[][] visited, int n, int height, int[][] region) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));
        while (!queue.isEmpty()) {
            Point curPoint = queue.poll();
            int curRow = curPoint.getRow();
            int curCol = curPoint.getCol();

            if (visited[curRow][curCol]) continue;
            visited[curRow][curCol] = true;
            for (int direction = 0; direction < 4; direction++) {
                int nextRow = curRow + DX[direction];
                int nextCol = curCol + DY[direction];

                if (checkBound(nextRow, nextCol, n) && !visited[nextRow][nextCol] && region[nextRow][nextCol] > height) {
                    queue.add(new Point(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean checkBound(int nextRow, int nextCol, int n) {
        return nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n;
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
