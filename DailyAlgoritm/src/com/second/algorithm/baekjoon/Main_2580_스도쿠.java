package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2580_스도쿠 {
    private static final int BLANK = 0;
    private static List<Blank> blanks;
    private static boolean[][] rowCheck;
    private static boolean[][] colCheck;
    private static boolean[][] squareCheck;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] board = new int[9][9];
        rowCheck = new boolean[9][10];
        colCheck = new boolean[9][10];
        squareCheck = new boolean[9][10];

        blanks = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 9; col++) {
                int number = Integer.parseInt(st.nextToken());
                board[row][col] = number;

                // 숫자 모르는 경우 넣기
                if(number == BLANK) blanks.add(new Blank(row, col));
                else {
                    rowCheck[row][number] = true;
                    colCheck[col][number] = true;
                    squareCheck[square(row, col)][number] = true;
                }
            }
        }
        br.close();

        // 스도쿠 실행
        sudoku(0, board);
    }

    private static void sudoku(int index, int[][] board) {
        if(index == blanks.size()) {
            print(board);

            System.exit(0);
        }

        Blank blank = blanks.get(index);
        int row = blank.getRow();
        int col = blank.getCol();
        for (int number = 1; number <= 9; number++) {
            // 1에서 9까지 넣어보고 체크
            if (availableNumber(number, row, col)) {
                rowCheck[row][number] = true;
                colCheck[col][number] = true;
                squareCheck[square(row, col)][number] = true;

                board[row][col] = number;
                sudoku(index + 1, board);

                rowCheck[row][number] = false;
                colCheck[col][number] = false;
                squareCheck[square(row, col)][number] = false;

            }
        }
    }

    private static boolean availableNumber(int number, int row, int col) {
        return !rowCheck[row][number] && !colCheck[col][number] && !squareCheck[square(row, col)][number];
    }

    private static void print(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static int square(int row, int col) {
        return  (row / 3) * 3 + (col / 3);
    }

    static class Blank {
        private final int row;
        private final int col;

        public Blank(int row, int col) {
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
