package com.algorithm.Baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_치즈 {
    private static int R, C;
    private static int[][] cheese;
    private static boolean[][] visited;
    private static final int[] DX = {1,-1,0,0};
    private static final int[] DY = {0,0,1,-1};

    static class Index {
        private int x;
        private int y;

        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cheese = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //없어질때까지 회수
        int loop_count = 0;
        int cheeseCount = 0;
        while(true){
            int count = 0;
            visited = new boolean[R][C];
            // 구멍체크
            checkCheese(0,0);

            // 공기와 닿은 치즈
            for(int i =0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(cheese[i][j] == 1){
                        attatchAir(i,j);
                        count++;
                    }
                }
            }

            if(count < 1)
                break;
            cheeseCount = count;
            loop_count++;
        }
        bw.write(String.valueOf(loop_count));
        bw.newLine();
        bw.write(String.valueOf(cheeseCount));
        br.close();
        bw.close();

    }

    private static void checkCheese(int i, int j) {
        Queue<Index> que = new LinkedList<>();
        que.offer(new Index(i,j));
        while(!que.isEmpty()){
            Index idx = que.poll();
            if(visited[idx.x][idx.y]) continue;
            visited[idx.x][idx.y] = true;
            for(int k =0;k<DX.length;k++) {
                int nx = idx.x + DX[k];
                int ny = idx.y + DY[k];
                if(nx >= 0 && nx < R && ny >= 0 && ny < C && cheese[nx][ny] == 0 && !visited[nx][ny]){
                    que.offer(new Index(nx,ny));
                }
            }
        }
    }

    private static void attatchAir(int i, int j) {
        for(int k =0;k<DX.length;k++){
            int nx = i + DX[k];
            int ny = j + DY[k];
            if(nx >= 0 && nx < R && ny >= 0 && ny < C && cheese[nx][ny] == 0 && visited[nx][ny]){
                cheese[i][j] = 0;
                visited[i][j] = false;
            }
        }
    }
}
