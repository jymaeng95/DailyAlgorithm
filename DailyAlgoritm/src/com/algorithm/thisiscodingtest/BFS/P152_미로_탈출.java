package com.algorithm.thisiscodingtest.BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P152_미로_탈출 {
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int[][] map = new int[row][col];
        for(int i=0;i<row;i++){
            String[] split = br.readLine().split("");
            for(int j=0;j<col;j++){
                map[i][j] = Integer.parseInt(split[j]);
            }
        }
        int[][] visited = new int[row][col];

        int count = getEscapeCount(row,col,map,visited);

        bw.write(String.valueOf(count));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getEscapeCount(int row, int col, int[][] map, int[][] visited) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0));
        visited[0][0] = 1;
        while(!queue.isEmpty()){
            Point p = queue.poll();
            if(p.x == row-1 && p.y == col-1){
                break;
            }
            for(int i=0;i<4;i++){
                int nx = dx[i]+p.x;
                int ny = dy[i]+p.y;
                if(nx >=0 && nx < row && ny >= 0 && ny < col && visited[nx][ny] == 0 && map[nx][ny] == 1) {
                    visited[nx][ny] = visited[p.x][p.y] +1;
                    queue.add(new Point(nx,ny));
                }
            }
        }


        return visited[row-1][col-1];
    }

}
