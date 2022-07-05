package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206_벽_부수고_이동하기 {
    static class Point {
        private int x;
        private int y;
        private int wall;
        private int count;

        public Point(int x, int y, int wall, int count) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.count = count;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWall() {
            return wall;
        }

        public int getCount() {
            return count;
        }
    }
    private static final int[] DX = {1,-1,0,0};
    private static final int[] DY = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for(int row = 0; row < N; row++) {
            String[] arr = br.readLine().split("");
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(arr[col]);
            }
        }
        
        int rst = getMinRoad(N,M,map);
        System.out.println(rst);
        br.close();
    }

    public Main_2206_벽_부수고_이동하기() {
    }

    private static int getMinRoad(int n, int m, int[][] map) {
        boolean[][][] visited = new boolean[2][n][m];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0,0,1));
        int count = -1;
        while(!queue.isEmpty()){
            Point point = queue.poll();
            int x = point.getX();
            int y = point.getY();
            int wall = point.getWall();
            int pCount = point.getCount();
            if(x == n-1 && y == m-1){
                count = pCount;
                break;
            }
            for(int loop = 0; loop < 4; loop ++) {
                int nx = DX[loop] + x;
                int ny = DY[loop] + y;
                if(checkBound(nx,ny,n,m)) {
                    if(map[nx][ny] != 1 && !visited[wall][nx][ny]) {
                        visited[wall][nx][ny] = true;
                        queue.add(new Point(nx, ny, wall,pCount+1));
                    }
                    else if(map[nx][ny] == 1 && wall == 0 && !visited[1][nx][ny]) {
                        visited[wall][nx][ny] = true;
                        queue.add(new Point(nx, ny,1,pCount+1));
                    }
                }
            }

        }
        return count;
    }

    private static boolean checkBound(int nx, int ny, int n, int m) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }
}
