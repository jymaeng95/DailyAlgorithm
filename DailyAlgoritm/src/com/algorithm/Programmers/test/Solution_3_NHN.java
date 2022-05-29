package com.algorithm.Programmers.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_3_NHN {
    public static void main(String[] args) {
        String[] maze = {"AAAAA", "AABBB", "CAEFG", "AAEFF"};
        String[] queries = {"1 1 1 5 AF", "1 1 4 5 AF", "2 1 4 5 FAE", "1 5 4 5 ABF", "1 1 4 1 A"};

        int[] rst = solution(maze, queries);
        for (int r : rst) {
            System.out.println("rst = " + r);
        }
    }

    private static int[] solution(String[] maze, String[] queries) {
        int[] answer = new int[queries.length];

        String[][] mazes = new String[maze.length][maze[0].length()];

        // 새로운 미로 만들기 (2차원)
        for (int row = 0; row < mazes.length; row++) {
            String[] split = maze[row].split("");
            for (int col = 0; col < mazes[row].length; col++) {
                mazes[row][col] = split[col];
            }
        }

        // 쿼리 실행
        for (int query = 0; query < queries.length; query++) {
            // 쿼리 파싱
            StringTokenizer st = new StringTokenizer(queries[query]);
            int startRow = Integer.parseInt(st.nextToken()) - 1;
            int startCol = Integer.parseInt(st.nextToken()) - 1;
            int endRow = Integer.parseInt(st.nextToken()) - 1;
            int endCol = Integer.parseInt(st.nextToken()) - 1;
            String available = st.nextToken();

            // bfs
            // 정답 넣기
            answer[query] = escapeMaze(startRow, startCol, endRow, endCol, available, mazes);
        }

        return answer;
    }

    private static final int[] DX = {0, 0, 1, -1};
    private static final int[] DY = {1, -1, 0, 0};

    private static int escapeMaze(int startRow, int startCol, int endRow, int endCol, String available, String[][] mazes) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[mazes.length][mazes[0].length][4];
        visited[startRow][startCol][0] = visited[startRow][startCol][1] = visited[startRow][startCol][2] = visited[startRow][startCol][3] = true;

        queue.add(new Point(startRow, startCol, 1));

        int move = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Point curPoint = queue.poll();
            int curRow = curPoint.getRow();
            int curCol = curPoint.getCol();
            int curMove = curPoint.getMove();

            if (curRow == endRow && curCol == endCol) {
                move = Math.min(move, curMove);
                continue;
            }

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = curRow + DX[direction];
                int nextCol = curCol + DY[direction];

                if (checkBound(nextRow, nextCol, mazes.length, mazes[0].length) && available.contains(mazes[nextRow][nextCol]) && !visited[nextRow][nextCol][direction]) {
                    visited[nextRow][nextCol][direction] = true;
                    queue.add(new Point(nextRow, nextCol, curMove + 1));
                }
            }
        }

        return move == Integer.MAX_VALUE ? -1 : move;
    }

    private static boolean checkBound(int nextRow, int nextCol, int row, int col) {
        return nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col;
    }

    static class Point {
        private int row;
        private int col;
        private int move;

        public Point(int row, int col, int move) {
            this.row = row;
            this.col = col;
            this.move = move;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getMove() {
            return move;
        }
    }
}
