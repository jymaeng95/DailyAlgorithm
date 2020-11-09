package com.algorithm.Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2718 {
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
        int count2 = dfs(new Index(0,0),maze,visited,N,M);
        bw.write(String.valueOf(count2));
        br.close();
        bw.close();
    }

    public static int dfs(Index idx, int[][] maze, boolean[][] visited, int N, int M) {
        List<Integer> count = new ArrayList<>();
        int start = 0;
        dfsUtil(idx,maze,visited,count,start,N,M);
        int minCount = count.get(0);
        for(int i=1;i<count.size();i++)
            minCount = Math.min(minCount,count.get(i));
        return minCount;
    }
    public static void dfsUtil(Index idx, int[][] maze, boolean[][] visited, List<Integer> count,int start, int N, int M){
        int i = idx.x;
        int j = idx.y;
        System.out.println(i+ " "+j+" "+start);
        if(i==N-1 && j == M-1)
            count.add(start);
        if(visited[i][j]) return;

        visited[i][j] = true;
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        for(int k=0;k<dx.length;k++){
            int nx = dx[k] + i;
            int ny = dy[k] + j;

            if(nx<N && nx>=0 && ny<M && ny>=0 && maze[nx][ny] == 1){
                dfsUtil(new Index(nx,ny),maze,visited,count,start+1,N,M);
            }
        }

    }


}
