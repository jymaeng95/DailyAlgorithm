package com.algorithm.thisiscodingtest.BFS;

import java.io.*;
import java.util.StringTokenizer;

public class P351_Q20_감시_피하기 {
    public static void main(String[] args) throws IOException {
        /*
         * 1. N: 복도 크기 ,S : student, T : Teacher, O : Obstacle
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[][] aisle = new String[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                aisle[i][j] = st.nextToken();
            }
        }
        String rst = avoidTeacher(aisle, N);
        bw.write(rst);
        br.close();
        bw.close();
    }

    private static String avoidTeacher(String[][] aisle, int n) {
        /*
         * 1. T -> 상하좌우 감시 가능 / 방해물 있을 시 감시 못함
         * 2. 장애물 3개를 X 위치에 한개씩 설치 후 완탐 -> 이후 장애물 제거
         * 3. 3개 설치한 경우 상하좌우로 DFS
         * 3-1. DFS에서 벽에 부딪히거나 방해물에 부딪히는 경우 return yes 이외 false
         */

        for (int i = 0; i < n * n; i++) {
            if (!aisle[i / n][i % n].equals("X")) continue;
            aisle[i / n][i % n] = "O";
            for (int j = i + 1; j < n * n; j++) {
                if (!aisle[j / n][j % n].equals("X")) continue;
                aisle[j / n][j % n] = "O";
                for (int k = j + 1; k < n * n; k++) {
                    if (!aisle[k / n][k % n].equals("X")) continue;
                    aisle[k / n][k % n] = "O";
                    boolean[][] visited = new boolean[n][n];
                    if (manage(aisle, visited, n)) return "YES";
                    aisle[k / n][k % n] = "X";
                }
                aisle[j / n][j % n] = "X";
            }
            aisle[i / n][i % n] = "X";
        }

        return "NO";
    }

    private static boolean manage(String[][] aisle, boolean[][] visited, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && aisle[i][j].equals("T")) {
                    visited[i][j] = true;
                    if (!dfs_left(i, j, n, aisle)) return false;
                    if (!dfs_right(i, j, n, aisle)) return false;
                    if (!dfs_up(i, j, n, aisle)) return false;
                    if (!dfs_down(i, j, n, aisle)) return false;
                }
            }
        }
        return true;
    }

    private static boolean dfs_down(int i, int j, int n, String[][] aisle) {
        if (checkBound(i, j, n) || aisle[i][j].equals("O")) return true;
        if (aisle[i][j].equals("S")) return false;
        return dfs_down(i + 1, j, n, aisle);
    }

    private static boolean dfs_up(int i, int j, int n, String[][] aisle) {
        if (checkBound(i, j, n) || aisle[i][j].equals("O")) return true;
        if (aisle[i][j].equals("S")) return false;
        return dfs_up(i - 1, j, n, aisle);
    }

    private static boolean dfs_right(int i, int j, int n, String[][] aisle) {
        if (checkBound(i, j, n) || aisle[i][j].equals("O")) return true;
        if (aisle[i][j].equals("S")) return false;
        return dfs_right(i, j + 1, n, aisle);
    }

    private static boolean dfs_left(int i, int j, int n, String[][] aisle) {
        if (checkBound(i, j, n) || aisle[i][j].equals("O")) return true;
        if (aisle[i][j].equals("S")) return false;
        return dfs_left(i, j - 1, n, aisle);
    }

    private static boolean checkBound(int i, int j, int n) {
        return i < 0 || i >= n || j < 0 || j >= n;
    }
}
