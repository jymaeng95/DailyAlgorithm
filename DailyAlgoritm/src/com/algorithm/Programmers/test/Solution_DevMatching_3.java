package com.algorithm.Programmers.test;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_DevMatching_3 {
    public static void main(String[] args) {
        int rows = 9;
        int cols = 7;
        int[][] lands = {{2, 2}, {2, 3}, {2, 5}, {3, 2}, {3, 4}, {3, 5}, {3, 6}, {4, 3}, {4, 6}, {5, 2},
                {5, 5}, {6, 2}, {6, 3}, {6, 4}, {6, 6}, {7, 2}, {7, 6}, {8, 3}, {8, 4}, {8, 5}};

        int[] rst = solution(rows, cols, lands);
        for (int i : rst) {
            System.out.println(i);
        }
    }

    private static int[] solution(int rows, int columns, int[][] lands) {
        int[][] map = new int[rows][columns];

        // 땅은 1 물은 0
        for (int[] land : lands) {
            map[land[0] - 1][land[1] - 1] = 1;
        }

        // 바깥 테두리로 한번 탐색
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[rows][columns];
        bfs(0, 0, lands, visited, map);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (!visited[row][col] && map[row][col] == 0) {
                    bfs(row, col, lands, visited, map);
                }
            }
        }
        if(min == Integer.MAX_VALUE) min = -1;
        if(max == Integer.MIN_VALUE) max = -1;
        return new int[]{min, max};
    }

    private static int[] DX = {1, -1, 0, 0};
    private static int[] DY = {0, 0, -1, 1};
    private static int min, max;
    private static void bfs(int row, int col, int[][] lands, boolean[][] visited, int[][] map) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));

        boolean ocean = false;
        if(row == 0 && col == 0) ocean = true;

        int count = 0;
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            int curRow = point.getRow();
            int curCol = point.getCol();

            if(visited[curRow][curCol]) continue;
            visited[curRow][curCol] = true;
            count++;

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = curRow + DX[direction];
                int nextCol = curCol + DY[direction];

                if(checkBound(nextRow, nextCol, visited) && map[nextRow][nextCol] == 0) {
                    queue.add(new Point(nextRow, nextCol));
                }
            }
        }

        if(!ocean) {
            min = Math.min(count, min);
            max = Math.max(count, max);
        }
    }

    private static boolean checkBound(int nextRow, int nextCol, boolean[][] visited) {
        return nextRow >= 0 && nextRow < visited.length && nextCol >= 0 && nextCol < visited[0].length;
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
