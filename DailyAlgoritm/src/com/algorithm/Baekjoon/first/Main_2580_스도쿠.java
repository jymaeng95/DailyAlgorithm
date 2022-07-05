package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2580_스도쿠 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[9][9];
        for (int row = 0; row < 9; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 9; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        getSudoku(board, 0,0);

        br.close();
    }

    private static void getSudoku(int[][] board, int row, int col) {
        if (row > 8) {
            for(int rowIdx = 0; rowIdx< 9; rowIdx++) {
                for(int colIdx =0; colIdx < 9; colIdx++) {
                    System.out.print(board[rowIdx][colIdx]+" ");
                }
                System.out.println();
            }
            System.exit(0);
            return;
        }
        if(board[row][col] == 0){
            for (int val = 1; val <= 9; val++) {
                if (checkNumber(val, board, row, col)) {
                    board[row][col] = val;
                    if (col == 8) getSudoku(board, row + 1, 0);
                    else getSudoku(board, row, col + 1);
                    board[row][col] = 0;
                }
            }
            return;
        }
        if(col == 8) getSudoku(board, row+1, 0);
        else getSudoku(board, row, col+1);
    }

    private static boolean checkNumber(int val, int[][] board, int row, int col) {
        // 1. 가로비교
        for (int oneCol = 0; oneCol < 9; oneCol++) {
            if (val == board[row][oneCol]) return false;
        }

        // 2. 세로비교
        for (int oneRow = 0; oneRow < 9; oneRow++) {
            if (val == board[oneRow][col]) return false;
        }

        // 3. 3*3비교
        for (int oneRow = row / 3 * 3; oneRow <= row / 3 * 3 + 2; oneRow++) {
            for (int oneCol = col / 3 * 3; oneCol <= col / 3 * 3 + 2; oneCol++) {
                if (val == board[oneRow][oneCol]) return false;
            }
        }
        return true;
    }
}
