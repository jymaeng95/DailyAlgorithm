package com.second.algorithm.interview.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution_777_Set_Matrix_Zeroes_Worst {
    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(matrix);
    }

    private static void setZeroes(int[][] matrix) {
        List<Point> zeroPosition = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0)
                    zeroPosition.add(new Point(row, col));
            }
        }

        makeZero(matrix, zeroPosition);
    }

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static void makeZero(int[][] matrix, List<Point> zeroPosition) {
        for (Point point : zeroPosition) {
            int row = point.row;
            int col = point.col;

            for (int direction = 0; direction < 4; direction++) {
                move(row, col, direction, matrix);
            }
        }
    }

    private static void move(int row, int col, int direction, int[][] matrix) {
        int nextRow = row + DX[direction];
        int nextCol = col + DY[direction];
        if (!checkBoundary(nextRow, nextCol, matrix.length, matrix[0].length)) return;

        matrix[nextRow][nextCol] = 0;
        move(nextRow, nextCol, direction, matrix);
    }

    private static boolean checkBoundary(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
