package com.algorithm.thisiscodingtest.DFS;

public class P355_Q22_블록_이동하기 {
    private static int second;

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        int rst = solution(board);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[][] board) {
        boolean[][] visited = new boolean[board.length][board.length];
        visited[0][0] = true;
        visited[0][1] = true;
        // 최초 3가지
        dfs(0, 0, 1, board, visited, "R"); //방향 최초 가로

        return 0;
    }

    private static void dfs(int time, int headX, int headY, int[][] board, boolean[][] visited, String direction) {
        if (headX == board.length - 1 && headY == board.length - 1) {
            second = Math.min(second, time);
            return;
        }
        if (checkBound(headX, headY, board, direction)) {
            if (direction.equals("R")) {
                visited[headX][headY+1] = true;
                dfs(time+1, headX, headY+1, board, visited,direction);
                visited[headX][headY+1] = false;

            }
            else if(direction.equals("L")) {

            }
            else if(direction.equals("D")) {

            }
            else {

            }
        }
    }

    private static boolean checkBound(int headX, int headY, int[][] board, String direction) {
        if (direction.equals("R"))
            return headX >= 0 && headX < board.length && headY + 1 >= 0 && headY + 1 < board.length;
        else if (direction.equals("L"))
            return headX >= 0 && headX < board.length && headY - 1 >= 0 && headY - 1 < board.length;
        else if (direction.equals("D"))
            return headX + 1 >= 0 && headX < board.length && headY >= 0 && headY < board.length;

        return headX - 1 >= 0 && headX - 1 < board.length && headY >= 0 && headY < board.length;
    }


}
