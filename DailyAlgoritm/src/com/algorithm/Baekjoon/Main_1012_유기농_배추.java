package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1012_유기농_배추 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int cabbageCount = Integer.parseInt(st.nextToken());

            // 배추 위치 읽기
            int[][] cabbage = new int[row][col];
            for(int count = 0; count < cabbageCount; count++) {
                st = new StringTokenizer(br.readLine());
                int cabbageRow = Integer.parseInt(st.nextToken());
                int cabbageCol = Integer.parseInt(st.nextToken());

                cabbage[cabbageRow][cabbageCol] = 1;
            }

            // 지렁이 구하기
            int rst = getWormCount(row, col, cabbage);
            System.out.println(rst);
        }

        br.close();
    }

    private static int getWormCount(int row, int col, int[][] cabbage) {
        boolean[][] visited = new boolean[row][col];
        int count = 0;

        for(int cabbageRow = 0; cabbageRow < row; cabbageRow++) {
            for(int cabbageCol = 0; cabbageCol < col; cabbageCol++) {
                // 방문하지 않고 배추가 심어져 있는 곳 탐색
                if(!visited[cabbageRow][cabbageCol] && cabbage[cabbageRow][cabbageCol] == 1) {
                    moveWorm(cabbageRow, cabbageCol, row, col, cabbage, visited);
                    count++;
                }
            }
        }

        return count;
    }

    private static final int[] DX = {0, 0, 1, -1};
    private static final int[] DY = {1, -1, 0, 0};
    private static void moveWorm(int cabbageRow, int cabbageCol, int row, int col, int[][] cabbage, boolean[][] visited) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(cabbageRow, cabbageCol));
        visited[cabbageRow][cabbageCol] = true;

        while(!queue.isEmpty()) {
            Point curPoint = queue.poll();

            for(int direction = 0; direction < 3; direction++) {
                int nextRow = curPoint.getRow() + DX[direction];
                int nextCol = curPoint.getCol() + DY[direction];

                if (checkBound(nextRow, nextCol, row, col) && !visited[nextRow][nextCol] && cabbage[nextRow][nextCol] == 1) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new Point(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean checkBound(int nextRow, int nextCol, int row, int col) {
        return nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col;
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
