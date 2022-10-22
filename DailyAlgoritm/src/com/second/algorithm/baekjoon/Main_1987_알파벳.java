package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1987_알파벳 {
    private static int R, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] board = new char[R][C];
        for (int row = 0; row < R; row++) {
            initBoard(row, br.readLine(), board);
        }

        boolean[] check = new boolean[26];
        count = 0;
        move(0, 0, check, board, 0);

        System.out.println(count);
        br.close();
    }

    private static int count;
    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {-1, 1, 0, 0};
    private static void move(int row, int col, boolean[] check, char[][] board, int visitCount) {
        if(check[board[row][col] - 'A']) {
            count = Math.max(count, visitCount);

            return;
        }

        int index = board[row][col] - 'A';
        check[index] = true;
        for (int direction = 0; direction < 4; direction++) {
            int nextRow = row + DX[direction];
            int nextCol = col + DY[direction];

            if (checkBoundary(nextRow, nextCol)) {
                move(nextRow, nextCol, check, board, visitCount + 1);
            }
        }
        check[index] = false;
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C;
    }

    private static void initBoard(int row, String line, char[][] board) {
        for (int col = 0; col < line.length(); col++) {
            board[row][col] = line.charAt(col);
        }
    }
}
