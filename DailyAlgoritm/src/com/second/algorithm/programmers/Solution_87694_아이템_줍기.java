package com.second.algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_87694_아이템_줍기 {
    public static void main(String[] args) {
        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;

//        int[][] rectangle = {{1, 1, 5, 7}};
//        int characterX = 1;
//        int characterY = 1;
//        int itemX = 4;
//        int itemY = 7;

        int rst = solution(rectangle, characterX, characterY, itemX, itemY);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] board = initBoard(rectangle);

        int move = search(rectangle, board, characterX * 2, characterY * 2, itemX * 2, itemY * 2);

        return move;
    }

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int search(int[][] rectangle, int[][] board, int characterX, int characterY, int itemX, int itemY) {
        boolean[][] check = new boolean[102][102];
        if (itemX == characterX && itemY == characterY) return 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(characterX, characterY, 0));
        check[characterX][characterY] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int curX = point.getX();
            int curY = point.getY();

            if (curX == itemX && curY == itemY) {
                return point.getMove() / 2;
            }

            for (int direction = 0; direction < 4; direction++) {
                int nextX = curX + DX[direction];
                int nextY = curY + DY[direction];

                if (inTheBoard(nextX, nextY) && !inRectangle(rectangle, nextX, nextY)
                        && board[nextX][nextY] == 1 && !check[nextX][nextY]) {
                    check[nextX][nextY] = true;
                    queue.add(new Point(nextX, nextY, point.getMove() + 1));
                }
            }
        }

        return -1;
    }

    private static boolean inRectangle(int[][] rectangle, int nextX, int nextY) {
        for (int[] square : rectangle) {
            int x1 = square[0] * 2;
            int y1 = square[1] * 2;
            int x2 = square[2] * 2;
            int y2 = square[3] * 2;

            if (x1 < nextX && nextX < x2 && y1 < nextY && nextY < y2) return true;
        }
        return false;
    }

    private static boolean inTheBoard(int nextX, int nextY) {
        return nextX >= 0 && nextX <= 101 && nextY >= 0 && nextY <= 101;
    }

    private static int[][] initBoard(int[][] rectangle) {
        int[][] board = new int[102][102];
        for (int[] point : rectangle) {
            int x1 = point[0] * 2;
            int y1 = point[1] * 2;
            int x2 = point[2] * 2;
            int y2 = point[3] * 2;

            // 위아래 넣기
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    board[x][y] = 1;
                }
            }
        }

        return board;
    }

    private static class Point {
        private final int x;
        private final int y;
        private final int move;

        public Point(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getMove() {
            return move;
        }
    }
}
