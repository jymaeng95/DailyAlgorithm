package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1937_욕심쟁이_판다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] forest = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                forest[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = pandasPosition(N, forest);
        System.out.println(rst);
        br.close();
    }

    static class Pandas {
        private int row;
        private int col;
        private int move;

        public Pandas(int row, int col, int move) {
            this.row = row;
            this.col = col;
            this.move = move;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getMove() {
            return move;
        }
    }

    private static int count;
    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {1, -1, 0, 0};
    private static int[][] dp;

    private static int pandasPosition(int n, int[][] forest) {
        count = 1;
        dp = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (dp[row][col] == 0) {
                    movePandas(row, col, n, forest);
                }
            }
        }
//
//        for(int row = 0; row < n; row++) {
//            for (int col = 0; col < n; col++) {
//                count = Math.max(dp[row][col],count);
//            }
//        }
        return count;
    }

    private static void movePandas(int row, int col, int n, int[][] forest) {
        Queue<Pandas> queue = new LinkedList<>();
        queue.add(new Pandas(row, col, 1));
        dp[row][col] = 1;
        while (!queue.isEmpty()) {
            Pandas pandas = queue.poll();
            int curRow = pandas.getRow();
            int curCol = pandas.getCol();
            int curCount = pandas.getMove();

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = DX[direction] + curRow;
                int nextCol = DY[direction] + curCol;
                if (checkBound(nextRow, nextCol, n) && forest[nextRow][nextCol] > forest[curRow][curCol]) {
                    if (dp[nextRow][nextCol] < curCount + 1) {
                        dp[nextRow][nextCol] = Math.max(dp[nextRow][nextCol], curCount + 1);
                        queue.add(new Pandas(nextRow, nextCol, curCount + 1));
                        count = Math.max(dp[nextRow][nextCol], count);
                    }
                }
            }
        }
    }

    private static boolean checkBound(int nextRow, int nextCol, int n) {
        return nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n;
    }
}
