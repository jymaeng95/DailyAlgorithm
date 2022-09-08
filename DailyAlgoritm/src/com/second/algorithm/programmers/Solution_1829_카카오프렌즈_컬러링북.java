package com.second.algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_1829_카카오프렌즈_컬러링북 {
    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] picture = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
        int[] rst = solution(m, n, picture);
        System.out.println("rst = " + rst[0]);
        System.out.println("rst = " + rst[1]);
    }

    private static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[m][n];
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col++) {
                if(!visited[row][col] && picture[row][col] != 0) {
                    int colorInformation = getColorInfo(row, col, picture, visited);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, colorInformation);
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private static final int[] DX = {0,0,-1,1};
    private static final int[] DY = {1,-1,0,0};
    private static int getColorInfo(int row, int col, int[][] picture, boolean[][] visited) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));
        visited[row][col] = true;

        int color = picture[row][col];
        int count = 1;
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            int curRow = point.getRow();
            int curCol = point.getCol();

            for(int direction = 0; direction < 4; direction++) {
                int nextRow = curRow + DX[direction];
                int nextCol = curCol + DY[direction];

                if(check(nextRow, nextCol, picture.length, picture[0].length) &&
                        !visited[nextRow][nextCol] && picture[nextRow][nextCol] == color) {
                    visited[nextRow][nextCol] = true;
                    count++;
                    queue.add(new Point(nextRow, nextCol));
                }
            }
        }
        return count;
    }

    private static boolean check(int nextRow, int nextCol, int m, int n) {
        return nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n;
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
