package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7569_토마토 {
    private static int N, M, H;
    private static final int TOMATO = 1, BLANK = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H  = Integer.parseInt(st.nextToken());

        Queue<Tomato> queue = new LinkedList<>();

        boolean allRipe = true;
        int[][][] box = new int[H][N][M];
        for (int height = 0; height < H; height++) {
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < M; col++) {
                    int type = Integer.parseInt(st.nextToken());
                    box[height][row][col] = type;

                    if (type == TOMATO) queue.add(new Tomato(height, row, col));
                    if (type == BLANK) allRipe = false;
                }
            }
        }

        if(!allRipe) System.out.println(getDayRipeTomato(box, queue));
        else System.out.println(0);

        br.close();
    }

    private static final int[] DH = {-1, 1, 0, 0, 0, 0};
    private static final int[] DX = {0, 0, -1, 1, 0, 0};
    private static final int[] DY = {0, 0, 0, 0, -1, 1};
    private static int getDayRipeTomato(int[][][] box, Queue<Tomato> queue) {
        boolean[][][] check = new boolean[H][N][M];

        int day = 0;
        while (!queue.isEmpty() && !checkAllRipe(box)) {
            int size = queue.size();
            for (int loop = 0; loop < size; loop++) {
                Tomato tomato = queue.poll();
                int height = tomato.getHeight();
                int row = tomato.getRow();
                int col = tomato.getCol();

                if(check[height][row][col]) continue;
                check[height][row][col] = true;

                for (int direction = 0; direction < 6; direction++) {
                    int nextHeight = height + DH[direction];
                    int nextRow = row + DX[direction];
                    int nextCol = col + DY[direction];

                    if(checkBoundary(nextHeight, nextRow, nextCol) && box[nextHeight][nextRow][nextCol] == BLANK)  {
                        box[nextHeight][nextRow][nextCol] = TOMATO;
                        queue.add(new Tomato(nextHeight, nextRow, nextCol));
                    }
                }
            }
            day++;
        }

        return checkAllRipe(box) ? day : -1;
    }

    private static boolean checkBoundary(int nextHeight, int nextRow, int nextCol) {
        return nextHeight >= 0 && nextHeight < H && nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M;
    }

    private static boolean checkAllRipe(int[][][] box) {
        for (int height = 0; height < H; height++) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if(box[height][row][col] ==  BLANK) return false;
                }
            }
        }
        return true;
    }

    static class Tomato {
        private final int height;
        private final int row;
        private final int col;

        public Tomato(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }

        public int getHeight() {
            return height;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
