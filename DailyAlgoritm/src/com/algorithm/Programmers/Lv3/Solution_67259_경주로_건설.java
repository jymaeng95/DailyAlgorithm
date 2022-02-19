package com.algorithm.Programmers.Lv3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_67259_경주로_건설 {
    public static void main(String[] args) {
//        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}};
//        int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
//        int[][] board = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
//        int[][] board = {{0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0}, {0, 0, 1, 0, 0, 0}, {1, 0, 0, 1, 0, 1}, {0, 1, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0}};
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 1, 1, 1, 1, 1, 0}, {1, 0, 0, 1, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 1}, {1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1, 0}};
        int solution = solution(board);
        System.out.println("solution = " + solution);
    }

    static class Point {
        private int x;
        private int y;
        private int cost;
        private int direction;

        public Point(int x, int y, int cost, int direction) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.direction = direction;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getCost() {
            return cost;
        }

        public int getDirection() {
            return direction;
        }
    }

    private static boolean[][][] visited;
    private static int[][] costBoard;
    private static final int[] DX = {1, -1, 0, 0};     //상 하 우 좌
    private static final int[] DY = {0, 0, 1, -1};

    private static int solution(int[][] board) {

        costBoard = new int[board.length][board.length];
        for(int loop = 0; loop < board.length;loop++) {
            Arrays.fill(costBoard[loop],Integer.MAX_VALUE);
        }
        int cost = bfs(0,0,board);
        return cost;
    }

    private static int bfs(int x, int y, int[][] board) {
        visited = new boolean[board.length][board.length][4];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y, -500, -1));
        visited[x][y][0] = visited[x][y][1] = visited[x][y][2] = visited[x][y][3] = true;
        int costs = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.getX() == board.length - 1 && cur.getY() == board.length - 1) {
                costs = Math.min(costs, cur.getCost());
            }
            for (int direction = 0; direction < 4; direction++) {
                int nx = cur.getX() + DX[direction];
                int ny = cur.getY() + DY[direction];
                if (checkBound(nx, ny, board)) {
                    int cost = cur.getCost() + 100;
                    if (cur.getDirection() != direction) cost += 500;
                    if(!visited[nx][ny][direction] || costBoard[nx][ny] >= cost) {
                        visited[nx][ny][direction] = true;
                        costBoard[nx][ny] = cost;
                        queue.add(new Point(nx, ny, cost, direction));
                    }
                }
            }
        }
        return costs;
    }

    private static boolean checkBound(int nx, int ny, int[][] board) {
        return nx >= 0 && nx < board.length && ny >= 0 && ny < board.length && board[nx][ny] == 0;
    }
}
