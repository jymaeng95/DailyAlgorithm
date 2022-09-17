package com.second.algorithm.interview.leetcode;

public class Solution_777_Set_Matrix_Zeroes {
    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(matrix);
    }

    private static void setZeroes(int[][] matrix) {
        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[0].length; c++) {
                if(matrix[r][c] == 0) {
                    // 현재 행 혹은 열을 체크 안했다면 0으로 바꿔야함을 체크
                    makeZero(r, c, matrix);
                }
            }
        }

        // 바꾼 값들 0으로 치환
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                if(matrix[row][col] == (int) 1e9) matrix[row][col] = 0;
            }
        }
    }
    private static void makeZero(int row, int col, int[][] matrix) {
        // 행을 동일한 값으로 바꿔주기
        for(int r = 0; r < matrix.length; r++) {
            if(matrix[r][col] != 0) matrix[r][col] = (int) 1e9;
        }

        // 0이 아닌 열을 동일한 값으로 바꿔주기
        for(int c = 0; c < matrix[0].length; c++) {
            if(matrix[row][c] != 0) matrix[row][c] = (int) 1e9;
        }
    }
}
