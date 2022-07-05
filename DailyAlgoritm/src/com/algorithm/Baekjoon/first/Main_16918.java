package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16918 {
    private static int R, C, N;
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    static class Index {
        private int x;
        private int y;

        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

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

        afterBomb(grid, 1);

        for (String[] arr : grid) {
            for (String s : arr) {
                bw.write(s);
            }
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static void afterBomb(String[][] grid, int time) {
        Queue<Index> que = new LinkedList<>();

        while(time < N) {
            //큐에 존재하던 폭탄 넣어주기
            originalBombQue(que,grid);

            // 빈 곳에 폭탄 채우기
            fillBombInBlank(grid);
            time += 1;
            if(time == N)
                break;

            //폭탄 터뜨리기
            while(!que.isEmpty()){
                Index index = que.poll();
                grid[index.x][index.y] = ".";
                affectFourWay(grid,index);
            }
            time += 1;
        }
    }

    private static void affectFourWay(String[][] grid, Index index) {
        for (int i = 0; i < DX.length; i++) {
            int nx = index.x + DX[i];
            int ny = index.y + DY[i];
            if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                grid[nx][ny] = ".";
            }
        }
    }

    private static void originalBombQue(Queue<Index> que, String[][] grid){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j].equals("O")) {
                    que.offer(new Index(i,j));
                }
            }
        }
    }

    private static void fillBombInBlank(String[][] grid) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(grid[i][j].equals("."))
                    grid[i][j] = "O";
            }
        }
    }
}
