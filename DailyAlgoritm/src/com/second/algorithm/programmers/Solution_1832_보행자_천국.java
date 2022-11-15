package com.second.algorithm.programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_1832_보행자_천국 {
    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] city_map = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};

        int rst = solution(m, n, city_map);
        System.out.println("rst = " + rst);
    }

    private static final int MOD = 20170805;
    private static final int INIT = -1, UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private static final int AVAILABLE = 0, FORBIDDEN = 1, STRAIGHT = 2;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int solution(int m, int n, int[][] cityMap) {
        int[][][] check = new int[m][n][4];

        Arrays.fill(check[0][0], 1);
        Queue<Intersection> queue = new LinkedList<>();
        queue.add(new Intersection(0, 0, -1));

        while (!queue.isEmpty()) {
            Intersection intersection = queue.poll();
            int row = intersection.getRow();
            int col = intersection.getCol();

            // 마지막에 도착한 경우
            if (row != m - 1 || col != n - 1) {
                // 현재 위치가 0인 경우
                if (cityMap[row][col] == AVAILABLE) {
                    for (int direction = 0; direction < 4; direction++) {
                        int nextRow = row + DX[direction];
                        int nextCol = col + DY[direction];

                        if (stayRoad(nextRow, nextCol, m, n) && cityMap[nextRow][nextCol] != FORBIDDEN && check[nextRow][nextCol][direction] == 0) {
                            // 현재 위치가 시작점인 경우 1로 고정
                            if (intersection.getDirection() == INIT)
                                check[nextRow][nextCol][direction] = 1;
                            // 현재 위치가 아닌 경우 다음 위치는 현재 위치의 모든 방향에서 갈 수 있다.
                            else
                                check[nextRow][nextCol][direction] = (check[row][col][UP] + check[row][col][DOWN] + check[row][col][LEFT] + check[row][col][RIGHT]) % MOD;
                            queue.add(new Intersection(nextRow, nextCol, direction));
                        }
                    }
                }
                // 현재 위치가 2인 경우
                else if (cityMap[row][col] == STRAIGHT) {
                    int direction = intersection.getDirection();
                    int nextRow = row + DX[direction];
                    int nextCol = col + DY[direction];

                    // 진행 방향에 대해서만 가능
                    if (stayRoad(nextRow, nextCol, m, n) && cityMap[nextRow][nextCol] != FORBIDDEN && check[nextRow][nextCol][direction] == 0) {
                        // 현재 위치가 시작점인 경우 1로 고정
                        if (intersection.getDirection() == INIT)
                            check[nextRow][nextCol][direction] = 1;
                        // 다음 위치는 현재 진행 방향만 가능
                        else
                            check[nextRow][nextCol][direction] = check[row][col][direction] % MOD;
                        queue.add(new Intersection(nextRow, nextCol, direction));
                    }
                }
            }
        }

        return (check[m - 1][n - 1][UP] + check[m - 1][n - 1][DOWN] + check[m - 1][n - 1][LEFT] + check[m - 1][n - 1][RIGHT]) % MOD;
    }

    private static boolean stayRoad(int nextRow, int nextCol, int m, int n) {
        return nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n;
    }

    private static class Intersection {
        private final int row;
        private final int col;
        private final int direction;

        public Intersection(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getDirection() {
            return direction;
        }
    }
}
