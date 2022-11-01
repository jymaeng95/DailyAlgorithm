package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663_N_Queen {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        availableCount(N);
        System.out.println(count);
        br.close();
    }

    private static int count;

    private static void availableCount(int n) {
        count = 0;
        boolean[][] check = new boolean[n][n];

        putQueen(0, n, check);
    }

    private static final int CHECK = 1, UNCHECK = 0;

    private static void putQueen(int row, int n, boolean[][] check) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (checkingEightDirection(row, col, n, check)) {
                // 8방향 체크
                check[row][col] = true;

                putQueen(row + 1, n, check);

                check[row][col] = false;
            }
        }
    }

    private static boolean checkingEightDirection(int row, int col, int n, boolean[][] check) {
        return leftTop(row - 1, col - 1, n, check) && top(row - 1, col, n, check) && rightTop(row - 1, col + 1, n, check)
                && leftBottom(row + 1, col - 1, n, check ) && bottom(row + 1, col, n, check) && rightBottom(row + 1, col + 1, n, check);
    }

    private static boolean rightBottom(int row, int col, int n, boolean[][] check) {
        if (row >= n || col >= n) return true;

        // check면 그대로 체크
        if (check[row][col]) return false;

        return rightBottom(row + 1, col + 1, n, check);
    }

    private static boolean bottom(int row, int col, int n, boolean[][] check) {
        if (row >= n) return true;

        // check면 그대로 체크
        if (check[row][col]) return false;

        return bottom(row + 1, col, n, check);
    }

    private static boolean leftBottom(int row, int col, int n, boolean[][] check) {
        if (row >= n || col < 0) return true;

        // check면 그대로 체크
        if (check[row][col]) return false;

        return leftBottom(row + 1, col - 1, n, check);
    }

    private static boolean rightTop(int row, int col, int n, boolean[][] check) {
        if (row < 0 || col >= n) return true;

        // check면 그대로 체크
        if (check[row][col]) return false;

        return rightTop(row - 1, col + 1, n, check);
    }

    private static boolean top(int row, int col, int n, boolean[][] check) {
        if (row < 0) return true;

        // check면 그대로 체크
        if (check[row][col]) return false;

        return top(row - 1, col, n, check);
    }

    private static boolean leftTop(int row, int col, int n, boolean[][] check) {
        if (row < 0 || col < 0) return true;

        // check면 그대로 체크
        if(check[row][col]) return false;

        return leftTop(row - 1, col - 1, n, check);
    }
}
