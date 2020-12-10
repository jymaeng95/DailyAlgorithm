package com.algorithm.SWEA.D4;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_2829 {
    private static Set<String> set;
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());
        int[][] board = new int[4][4];
        for (int N = 0; N < TC; N++) {
            set = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++)
                    board[i][j] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(board, i, j);
                }
            }

            bw.write("#" + (N + 1) + " ");
            bw.write(String.valueOf(set.size()));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static void dfs(int[][] board, int i, int j) {
        String s = "";
        dfsUtil(board, i, j, s);
    }

    private static void dfsUtil(int[][] board, int i, int j, String s) {
        if (s.length() == 7) {
            set.add(s);
            return;
        }
        s += board[i][j];
        for (int k = 0; k < DX.length; k++) {
            int nx = i + DX[k];
            int ny = j + DY[k];
            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4)
                dfsUtil(board, nx, ny, s);
        }
    }
}
