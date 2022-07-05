package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.*;

public class Main_2178 {
    static class Index {
        int x;
        int y;
        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] maze = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        // Maze에 숫자 넣기
        for(int i=0;i<N;i++){
            String[] isMove = br.readLine().trim().split("");
            for (int j=0;j<M;j++){
                maze[i][j] = Integer.parseInt(isMove[j]);
            }
        }
        int count = bfs(new Index(0,0),maze,visited,N,M);
        bw.write(String.valueOf(count));
        br.close();
        bw.close();
    }

    private static int bfs(Index index, int[][] maze, boolean[][] visited, int N, int M) {
        Queue<Index> que = new LinkedList<>();
        int[][] visitCount = new int[N][M];
        que.offer(index);
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        while(!que.isEmpty()){
            Index idx = que.poll();
            if(visited[idx.x][idx.y])
                continue;
            visited[idx.x][idx.y] = true;
            for(int i=0;i<dx.length;i++){
                int nx = dx[i] + idx.x;
                int ny = dy[i] + idx.y;
                if(nx < N && nx >= 0 && ny < M && ny >= 0 && maze[nx][ny]==1 && visitCount[nx][ny] == 0){
                    que.offer(new Index(nx,ny));
                    visitCount[nx][ny] = visitCount[idx.x][idx.y] + 1;
                }
            }
        }

        return visitCount[N-1][M-1] + 1;
    }
}
