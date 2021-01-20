package com.algorithm.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main_16234_인구_이동 {
    /*
    첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
    둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)
    인구 이동이 발생하는 횟수가 2,000번 보다 작거나 같은 입력만 주어진다.
     */
    private static int N, L, R, sum, cnt;
    private static int[][] people;
    private static boolean[][] visited;
    private static int[] DX = {1, -1, 0, 0};
    private static int[] DY = {0, 0, 1, -1};

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
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        people = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        while (true) {
            cnt = 0;
            sum = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j])
                        dfs(i, j);
                }
            }
            if(cnt < 1)
                break;
            int change = sum / cnt;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j])
                        people[i][j] = change;
                    System.out.print(people[i][j]+" ");
                }
                System.out.println();
            }
            count++;
        }
//
//        int count = 0;
//        while(true) {
//            visited = new boolean[N][N];
//            sum = 0;
//            cnt = 0;
//            int flag = 0;
//            for(int i=0;i<N;i++){
//                for(int j =0;j<N;j++){
//                    if(!visited[i][j]) {
//                        flag+= bfs(i, j);
//
//                    }
//                }
//            }
//            if(flag < 1)
//                break;
//            count++;
//        }

//        while(true){
//            visited = new boolean[N][N];
//        }
        bw.write(String.valueOf(count));
        br.close();
        bw.close();
    }

    private static void dfs(int i, int j) {
        if (visited[i][j])
            return;
        sum += people[i][j];

        for (int k = 0; k < DX.length; k++) {
            int nx = i + DX[k];
            int ny = j + DY[k];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                int cap = Math.abs(people[nx][ny] - people[i][j]);
                if (cap >= L && cap <= R) {
                    visited[i][j] = true;
                    cnt ++;
                    dfs(nx,ny);
                }
            }
        }

    }

//    private static int bfs(int x, int y) {
//        Queue<Index> que = new LinkedList<>();
//        que.offer(new Index(x,y));
//
//        while(!que.isEmpty()){
//            Index idx  = que.poll();
//            if(visited[idx.x][idx.y]) continue;
//            visited[idx.x][idx.y] = true;
//            for(int i=0;i<DX.length;i++){
//                int nx = idx.x + DX[i];
//                int ny = idx.y + DY[i];
//                if(nx >=0 && nx < N && ny >=0 && ny<N && !visited[nx][ny]) {
//                    int cap = Math.abs(people[nx][ny] - people[idx.x][idx.y]);
//                    if(cap >= L && cap <=R){
//                        cnt ++;
//                        sum += people[nx][ny];
//                        que.offer(new Index(nx,ny));
//                    }
//                }
//            }
//        }
//
//        if(count > 0) {
//            int change = sum / count;
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (visited[i][j])
//                        people[i][j] = change;
//                    System.out.print(people[i][j]+" ");
//                }
//                System.out.println();
//            }
//            return 1;
//        }
//        return 0;
//    }


}
