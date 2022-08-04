package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_77485_행렬_테두리_회전하기 {
    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;

        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};

        int[] rst = solution(rows, columns, queries);
        Arrays.stream(rst).forEach(System.out::println);
    }

    private static List<Integer> minList;

    private static int[] solution(int rows, int columns, int[][] queries) {
        int[][] board = new int[rows][columns];
        minList = new ArrayList<>();

        // 배열 초기화
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = (row * columns) + col + 1;
            }
        }

        // 쿼리 실행
        for (int[] query : queries) {
            int[][] copyBoard = copyOf(board, rows, columns);
            board = rotateArray(board, copyBoard, query, rows, columns);
        }

        return minList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[][] copyOf(int[][] board, int rows, int columns) {
        int[][] copyBoard = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }
        return copyBoard;
    }

    private static int[][] rotateArray(int[][] board, int[][] copyBoard, int[] query, int rows, int columns) {
        int min = rows * columns + 1;

        int row1 = query[0] - 1;
        int col1 = query[1] - 1;
        int row2 = query[2] - 1;
        int col2 = query[3] - 1;

        // 회전하기
        for (int row = row1; row <= row2; row++) {
            for (int col = col1; col <= col2; col++) {
                // 테두리 안쪽 영역
                if (row != row1 && row != row2 && col != col1 && col != col2) {
                    copyBoard[row][col] = board[row][col];
                } else {
                    // 오른쪽 이동
                    if (row == row1 && col != col2) {
                        copyBoard[row][col + 1] = board[row][col];
                    }
                    // 아래 이동
                    else if (row != row2 && col == col2) {
                        copyBoard[row + 1][col] = board[row][col];
                    }
                    // 왼쪽 이동
                    else if (row == row2 && col != col1) {
                        copyBoard[row][col - 1] = board[row][col];
                    }
                    // 위로 이동
                    else if (row != row1 && col == col1) {
                        copyBoard[row - 1][col] = board[row][col];
                    }
                    min = Math.min(min, board[row][col]);
                }
            }
        }

        minList.add(min);
        return copyBoard;
    }
}
