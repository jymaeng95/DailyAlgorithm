package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토 {
    private static int M,N;
    private final static int NOT_RIPE = 0, TOMATO = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        Queue<Point> queue = new LinkedList<>();
        int[][] storage = new int[N][M];
        boolean allRipe = true;

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int type = Integer.parseInt(st.nextToken());
                storage[row][col] = type;

                // 타입이 1인 경우 토마토
                if (type == TOMATO) {
                    queue.add(new Point(row, col));
                }

                if(type == NOT_RIPE) allRipe = false;
            }
        }

        if(allRipe) System.out.println(0);
        else {
            int day = dayOfRipeTomato(queue, storage);
            System.out.println(day);
        }

        br.close();
    }

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};
    private static int dayOfRipeTomato(Queue<Point> queue, int[][] storage) {
        boolean[][] check = new boolean[N][M];
        int day = 0;

        while (!queue.isEmpty() && isNotRipe(storage)) {
            int size = queue.size();
            for (int loop = 0; loop < size; loop++) {
                Point point = queue.poll();
                int row = point.getRow();
                int col = point.getCol();

                if(check[row][col]) continue;
                check[row][col] = true;

                for (int direction = 0; direction < 4; direction++) {
                    int nextRow = row + DX[direction];
                    int nextCol = col + DY[direction];

                    if(checkBoundary(nextRow, nextCol) && storage[nextRow][nextCol] == NOT_RIPE) {
                        storage[nextRow][nextCol] = TOMATO;
                        queue.add(new Point(nextRow, nextCol));
                    }
                }
            }
            day++;
        }

        return isNotRipe(storage) ? -1 : day;
    }

    private static boolean isNotRipe(int[][] storage) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if(storage[row][col] == NOT_RIPE) return true;
            }
        }
        return false;
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < N && nextCol >= 0 && nextCol < M;
    }

    static class Point {
        private final int row;
        private final int col;

        public Point(int row, int col) {
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
