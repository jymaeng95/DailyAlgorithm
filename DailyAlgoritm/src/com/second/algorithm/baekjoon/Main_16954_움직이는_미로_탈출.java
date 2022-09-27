package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_16954_움직이는_미로_탈출 {
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[8][8];
        for (int row = 0; row < 8; row++) {
            char[] arr = br.readLine().toCharArray();
            for (int col = 0; col < 8; col++) {
                board[row][col] = arr[col];
            }
        }

        int rst = reachTop();
        System.out.println(rst);
        br.close();
    }


    private static final int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};

    private static int reachTop() {
        /**
         * 1. 캐릭터 최초 왼쪽 아래
         * 2. 캐릭터 오른쪽 위에 도달하기
         * 3. 캐릭터가 움직이고 벽이 내려옴
         * 4. 8방향 이동 및 서있기 가능
         */

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(7, 0));

        boolean[][][] visited = new boolean[8][8][8];
        visited[7][0][0] = visited[7][0][3] = visited[7][0][5] = visited[7][0][6] = visited[7][0][7] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // 현재 큐의 사이즈는 1초동안 움직인 곳의 모음
            for (int move = 0; move < size; move++) {
                Point point = queue.poll();
                int row = point.getRow();
                int col = point.getCol();

                // 만약 도달했다면 return true;
                if (row == 0 && col == 7) return 1;

                // 현재 위치가 벽이 아니라면 방향 확인
                if (board[row][col] == '.') {
                    queue.add(new Point(row, col));
                    for (int direction = 0; direction < 8; direction++) {
                        int nextRow = row + DX[direction];
                        int nextCol = col + DY[direction];

                        if (checkBoundary(nextRow, nextCol) && !visited[nextRow][nextCol][direction] && board[nextRow][nextCol] == '.') {
                            visited[nextRow][nextCol][direction] = true;
                            queue.add(new Point(nextRow, nextCol));
                        }
                    }
                }
            }
            // 벽 내려주기
            for (int boardCol = 0; boardCol < 8; boardCol++) {
                for (int boardRow = 7; boardRow > 0; boardRow--) {
                    board[boardRow][boardCol] = board[boardRow - 1][boardCol];
                }
                board[0][boardCol] = '.';
            }
        }


        return 0;
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < 8 && nextCol >= 0 && nextCol < 8;
    }

    static class Point {
        private int row;
        private int col;

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
