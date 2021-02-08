package com.algorithm.SWEA.D3;

import java.io.*;

public class Solution_11315_오목_판정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[][] board = new String[N][N];
            for (int j = 0; j < N; j++) {
                String[] data = br.readLine().split("");
                for (int k = 0; k < N; k++) {
                    board[j][k] = data[k];
                }
            }
            boolean search = false;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (board[j][k].equals("o")) {
                        search = search(j, k, board, N);
                    }
                    if (search)
                        break;
                }
                if (search) {
                    bw.write("#" + i + " YES");
                    break;
                }
            }
            if (!search)
                bw.write("#" + i + " NO");
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    private static boolean search(int j, int k, String[][] board, int N) {
        //아래
        int down = 1;
        for (int i = 1; i < 5; i++) {
            int nx = j + i;
            int ny = k;
            if (!check(nx, ny, N, board)) {
                break;
            }
            down++;
        }

        //오른쪽
        int right = 1;
        for (int i = 1; i < 5; i++) {
            int nx = j;
            int ny = k + i;
            if (!check(nx, ny, N, board)) {
                break;
            }
            right++;
        }
        //대각선 오른쪽아래
        int downRight = 1;
        for (int i = 1; i < 5; i++) {
            int nx = j + i;
            int ny = k + i;
            if (!check(nx, ny, N, board)) {
                break;
            }
            downRight++;
        }
        //대각선 왼쪽아래
        int downLeft = 1;
        for (int i = 1; i < 5; i++) {
            int nx = j + i;
            int ny = k - i;
            if (!check(nx, ny, N, board)) {
                break;
            }
            downLeft++;
        }

        //육목 체크

        if (down == 5) {
            if (j + down >= N)
                return true;
            if (j + down < N && !board[j + down][k].equals("o")) {
                return true;
            }
        }

        if (right == 5) {
            if (k + right >= N)
                return true;
            if (k + right < N && !board[j][k + right].equals("o")) {
                return true;
            }
        }

        if (downRight == 5) {
            if (k + downRight >= N || j + downRight >= N)
                return true;
            if (k + downRight < N && j + downRight < N && !board[j + downRight][k + downRight].equals("o"))
                return true;
        }

        if (downLeft == 5) {
            if (k - downLeft < 0 || j + downLeft >= N)
                return true;
            if (j + downLeft < N && !board[j + downLeft][k - downLeft].equals("o"))
                return true;
        }
        return false;
    }

    private static boolean check(int nx, int ny, int N, String[][] board) {
        return nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny].equals("o");
    }
}

