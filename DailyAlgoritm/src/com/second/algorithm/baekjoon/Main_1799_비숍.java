package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1799_비숍 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        boolean[][] push = new boolean[N][N];
        for (int row = 0; row < N; row++) {
            Arrays.fill(push[row], true);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int type = Integer.parseInt(st.nextToken());
                if (type == 0) push[row][col] = false;
                board[row][col] = type;
            }
        }

        int rst = pushBishop(board, push);
        System.out.println(rst);
        br.close();
    }

    private static int maxCount;
    private static final int BISHOP = 2, BLANK = 1, BLOCK = 0;

    private static int pushBishop(int[][] board, boolean[][] push) {
        maxCount = 0;

        gameBishop(0, 0, 0, board, push);
        return maxCount;
    }

    private static void gameBishop(int curRow, int curCol, int count, int[][] board, boolean[][] push) {
        if (curRow == N && curCol == 0) {
            maxCount = Math.max(count, maxCount);
            return;
        }

        // 놓을 수 있는 경우 놓거나 안놓거나
        if (checkBoard(curRow, curCol, board)) {
            board[curRow][curCol] = BISHOP;
            if (curCol == N - 1) {
                gameBishop(curRow + 1, 0, count + 1, board, push);
            } else {
                gameBishop(curRow, curCol + 1, count + 1, board, push);
            }
            board[curRow][curCol] = BLANK;
        }

        if (curCol == N - 1) gameBishop(curRow + 1, 0, count, board, push);
        else gameBishop(curRow, curCol + 1, count, board, push);

    }

    private static boolean checkBoard(int curRow, int curCol, int[][] board) {
        // 놓을 수 없는 경우
        if (board[curRow][curCol] == BLOCK || board[curRow][curCol] == BISHOP) return false;

        int row = curRow;
        int col = curCol;

        while (row >= 0 && col >= 0) {
            if (board[row][col] == BISHOP) return false;
            row--;
            col--;
        }

        row = curRow;
        col = curCol;
        while (row < N && col >= 0) {
            if (board[row][col] == BISHOP) return false;
            row++;
            col--;
        }

        row = curRow;
        col = curCol;
        while (row >= 0 && col < N) {
            if (board[row][col] == BISHOP) return false;
            row--;
            col++;
        }

        row = curRow;
        col = curCol;
        while (row < N && col < N) {
            if (board[row][col] == BISHOP) return false;
            row++;
            col++;
        }
        return true;
    }
}
