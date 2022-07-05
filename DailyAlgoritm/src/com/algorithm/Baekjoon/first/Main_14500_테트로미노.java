package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500_테트로미노 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] paper = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                paper[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = getMaxSum(N, M, paper);
        System.out.println(rst);
        br.close();
    }

    static class Type {
        private int type;
        private int row;
        private int col;

        public Type(int type, int row, int col) {
            this.type = type;
            this.row = row;
            this.col = col;
        }

        public int getType() {
            return type;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    private static final Type[] types = {new Type(0,3,0),new Type(1, 0, 3), new Type(2, 1, 1), new Type(3, 1, 2), new Type(4, 2, 1)};
    private static final int[][] thirdType = {{1, 0, 1, 2}, {0, 0, 0, 2}, {0, 0, 1, 2}, {0, 2, 1, 0}, {1, 0, 1, 1}, {1, 1, 1, 2}, {0, 0, 0, 1}, {0, 1, 0, 2}};
    private static final int[][] fourthType = {{0,1, 1, 1}, {1, 1, 2, 1}, {1, 0, 2, 0}, {0, 0, 1, 0}, {0, 0, 2, 0}, {0, 1, 2, 1}, {0, 0, 2, 1}, {0, 1, 2, 0}};
    private static int max;

    private static int getMaxSum(int n, int m, int[][] paper) {
        max = 0;
        for (Type type : types) {
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    if (checkBound(row, col, n, m, type)) {
                        tetromino(row, col, n, m, paper, type);
                    }
                }
            }
        }
        return max;
    }

    private static boolean checkBound(int row, int col, int n, int m, Type type) {
        return row + type.getRow() >= 0 && row + type.getRow() < n && col + type.getCol() >= 0 && col + type.getCol() < m;
    }

    private static void tetromino(int row, int col, int n, int m, int[][] paper, Type type) {
        int sum = 0;
        for (int paperRow = row; paperRow <= row + type.getRow(); paperRow++) {
            for (int paperCol = col; paperCol <= col + type.getCol(); paperCol++) {
                sum += paper[paperRow][paperCol];
            }
        }

        if (type.getType() == 3) {
            for (int[] remove : thirdType) {
                int removeSum = paper[row + remove[0]][col + remove[1]] + paper[row + remove[2]][col + remove[3]];
                max = Math.max(max, sum - removeSum);
            }
        } else if (type.getType() == 4) {
            for (int[] remove : fourthType) {
                int removeSum = paper[row + remove[0]][col + remove[1]] + paper[row + remove[2]][col + remove[3]];
                max = Math.max(max, sum - removeSum);
            }
        } else {
            max = Math.max(max, sum);
        }

    }
}
