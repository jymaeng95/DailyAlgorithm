package com.algorithm.Programmers.Lv3;

import java.util.Deque;
import java.util.LinkedList;

public class Solution_67259_경주로_건설 {
    public static void main(String[] args) {
//        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}};
//        int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
//        int[][] board = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
        int[][] board = {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
        int solution = solution(board);
        System.out.println("solution = " + solution);
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static boolean[][] visited;
    private static int costs;
    private static Deque<Point> deque;

    private static int solution(int[][] board) {
        costs = Integer.MAX_VALUE;
        visited = new boolean[board.length][board.length];
        deque = new LinkedList<>();
        dfs(0, 0, 0, 0, board);

        // 직선 100원 코너 500원
        // 덱 사용?
        return costs;
    }

    private static void dfs(int x, int y, int road, int corner, int[][] board) {
        if (!checkBound(x, y, board) || visited[x][y]) return;
        if (x == board.length - 1 && y == board.length - 1) {
            deque.add(new Point(x, y));
            if (deque.size() > 3) deque.pollFirst();
            if (deque.size() == 3 && checkCorner(x, y)) corner += 1;
            int cost = (road * 100) + (corner * 500);
            costs = Math.min(cost, costs);
            return;
        }
        Point p = null;
        deque.add(new Point(x, y));
        if (deque.size() > 3) p = deque.pollFirst();
        if (deque.size() == 3 && checkCorner(x, y)) corner += 1;
        visited[x][y] = true;
        dfs(x, y + 1, road + 1, corner, board);
        visited[x][y] = false;
        if (deque.size() == 3 && checkCorner(x, y)) corner -= 1;
        if (p != null) deque.addFirst(p);
        deque.pollLast();

        p= null;
        deque.add(new Point(x, y));
        if (deque.size() > 3) p = deque.pollFirst();
        if (deque.size() == 3 && checkCorner(x, y)) corner += 1;
        visited[x][y] = true;
        dfs(x + 1, y, road + 1, corner, board);
        visited[x][y] = false;
        if (deque.size() == 3 && checkCorner(x, y)) corner -= 1;
        if (p != null) deque.addFirst(p);
        deque.pollLast();

        p = null;
        deque.add(new Point(x, y));
        if (deque.size() > 3) p = deque.pollFirst();
        if (deque.size() == 3 && checkCorner(x, y)) corner += 1;
        visited[x][y] = true;
        dfs(x - 1, y, road + 1, corner, board);
        visited[x][y] = false;
        if (deque.size() == 3 && checkCorner(x, y)) corner -= 1;
        if (p != null) deque.addFirst(p);
        deque.pollLast();

        p = null;
        deque.add(new Point(x, y));
        if (deque.size() > 3) p = deque.pollFirst();
        if (deque.size() == 3 && checkCorner(x, y)) corner += 1;
        visited[x][y] = true;
        dfs(x, y - 1, road + 1, corner, board);
        visited[x][y] = false;
        if (deque.size() == 3 && checkCorner(x, y)) corner -= 1;
        if (p != null) deque.addFirst(p);
        deque.pollLast();
    }

    private static boolean checkBound(int nx, int ny, int[][] board) {
        return nx >= 0 && nx < board.length && ny >= 0 && ny < board.length && board[nx][ny] == 0;
    }

    private static boolean checkCorner(int x, int y) {
        Point first = deque.peekFirst();
        Point last = deque.peekLast();
        return Math.abs(first.getX() - last.getX()) == 1 && Math.abs(first.getY() - last.getY()) == 1;
    }
}
