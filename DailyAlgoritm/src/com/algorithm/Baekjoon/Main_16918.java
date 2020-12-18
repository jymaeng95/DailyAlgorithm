package com.algorithm.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main_16918 {
    private static int R, C, N;
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        String[][] grid = new String[R][C];

        for (int i = 0; i < R; i++) {
            String[] arrBomb = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                grid[i][j] = arrBomb[j];
            }
        }

        afterBomb(grid);


        for (String[] arr : grid) {
            for (String s : arr) {
                bw.write(s);
            }
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static void afterBomb(String[][] grid) {
        boolean[][] visited = new boolean[R][C];
        N = (N > 1) ? (N % 4) : N;
        switch (N) {
            case 1:
            case 3:
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        grid[i][j] = "O";
                    }
                }
                break;
            case 2:
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (grid[i][j].equals("O") && !visited[i][j]) {
                            visited[i][j] = true;
                            affectFourWay(visited, grid, i, j);
                        }
                    }
                }

                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (grid[i][j].equals(".")) {
                            grid[i][j] = "O";
                            continue;
                        }
                        grid[i][j] = ".";
                    }
                }
                break;
            default :
                break;
        }
    }

    private static void affectFourWay(boolean[][] visited, String[][] grid, int i, int j) {
        for (int k = 0; k < DX.length; k++) {
            int nx = i + DX[k];
            int ny = j + DY[k];
            if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny] && grid[nx][ny].equals(".")) {
                visited[nx][ny] = true;
                grid[nx][ny] = "O";
            }
        }
    }
}
