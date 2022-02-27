package com.algorithm.Programmers.Lv2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_1844_게임_맵_최단거리 {
    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};

        int solution = solution(maps);
        System.out.println("solution = " + solution);
    }
    static class Point {
        private int x;
        private int y;
        private int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getCount() {
            return count;
        }
    }
    private final static int[] DX = {1,-1,0,0};
    private final static int[] DY = {0,0,1,-1};
    private static int solution(int[][] maps) {
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        int answer = bfs(visited, maps);
        return answer;
    }

    private static int bfs(boolean[][] visited, int[][] maps) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0,1));
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            int curX = point.getX();
            int curY = point.getY();
            int count = point.getCount();
            if(curX == maps.length-1 && curY == maps[0].length-1) {

                return count;
            }
            if(visited[curX][curY]) continue;
            visited[curX][curY] = true;
            for(int loop = 0; loop < 4; loop++) {
                int nx = curX + DX[loop];
                int ny = curY + DY[loop];
                if(checkBound(nx,ny, maps) && !visited[nx][ny]) {
                    queue.add(new Point(nx, ny, count+1));
                }
            }
        }

        return -1;
    }

    private static boolean checkBound(int nx, int ny, int[][] maps) {
        return nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length && maps[nx][ny] != 0;
    }
}
