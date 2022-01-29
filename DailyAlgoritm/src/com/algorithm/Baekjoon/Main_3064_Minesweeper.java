package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_3064_Minesweeper {
    private static final int[] DX = {-1,-1,-1,0,0,1,1,1};
    private static final int[] DY = {-1,0,1,-1,1,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int T = 0; T <TC; T++) {
            int N = Integer.parseInt(br.readLine());

            String[][] board = new String[N][N];
            for (int i = 0; i < N; i++) {
                String[] split = br.readLine().split("");
                System.arraycopy(split, 0, board[i], 0, N);
            }

            int rst = mineSweeper(N, board);
            System.out.println(rst);
        }
        br.close();
    }

    private static int mineSweeper(int n, String[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 숫자인 경우
                if(!board[i][j].equals("#") && !board[i][j].equals("*") && !board[i][j].equals("X")) {
                    int mine = Integer.parseInt(board[i][j]);
                    checkMine(i,j,n,board,mine);
                }
            }
        }

        int count =0;
        for(String[] bdr :  board) {
            for(String bd : bdr) {
                if(bd.equals("#") || bd.equals("*")) count++;
            }
        }
        return count;
    }

    private static void checkMine(int x, int y, int n, String[][] board, int mine) {
        int mineCount = 0;
        for(int i=0;i<8;i++) {
            int nx = DX[i] + x;
            int ny = DY[i] + y;
            if(nx >= 0 && nx <n && ny >= 0 && ny < n) {
                if(board[nx][ny].equals("*")) mineCount++;
            }
        }

        for(int i=0;i<8;i++) {
            int nx= DX[i] + x;
            int ny =DY[i] + y;
            if(nx >= 0 && nx <n && ny >= 0 && ny < n && (mine == mineCount) && board[nx][ny].equals("#")) {
                board[nx][ny] = "X";
            }
            else if(nx >= 0 && nx < n && ny >= 0 && ny < n && (mine != mineCount) && board[nx][ny].equals("#")) {
                board[nx][ny] = "*";
            }
        }
    }
}
