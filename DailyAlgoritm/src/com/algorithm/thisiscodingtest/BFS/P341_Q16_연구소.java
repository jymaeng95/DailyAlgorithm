package com.algorithm.thisiscodingtest.BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P341_Q16_연구소 {
    static class Point {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] DX = {1,-1,0,0};
    private static final int[] DY = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        /*
         * 1. 0 부분에 벽 3개를 다 채워줌 (완탐)
         * 2. 넣고나서 BFS 진행 (바이러스로 시작)
         * 3. 마지막 0 개수 확인후 max값 리턴
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] lab = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = protectVirus(N, M, lab);
        bw.write(String.valueOf(rst));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int protectVirus(int n, int m, int[][] lab) {
        // 1. 0 부분에 벽 3개 채우기
        int protectCount = 0;
        for (int i = 0; i < n * m; i++) {
            if(lab[i/m][i%m] != 0) continue;
            lab[i/m][i%m] = 1;
            for (int j = i + 1; j < n * m; j++) {
                if(lab[j/m][j%m] != 0) continue;
                lab[j/m][j%m] = 1;
                for (int k = j + 1; k<n * m; k++ ){
                    if(lab[k/m][k%m] != 0) continue;

                    lab[k/m][k%m] = 1;
                    // 2. visited 배열 만들고 바이러스로 BFS 진행
                    protectCount = Math.max(protectCount, getCount(n,m, lab));

                    // 3. 벽 세운곳 없애기
                    lab[k/m][k%m] = 0;
                }
                lab[j/m][j%m] = 0;
            }
            lab[i/m][i%m] = 0;
        }
        return protectCount;
    }

    private static int getCount(int n, int m, int[][] lab) {
        boolean[][] visited = new boolean[n][m];
        for(int i =0;i<n;i++) {
            for(int j =0; j<m;j++) {
                if(!visited[i][j] && lab[i][j] == 2) {
                    bfs(i,j,lab,visited,n,m);
                }
            }
        }

        int count = 0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(!visited[i][j] && lab[i][j] ==  0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(int i, int j, int[][] lab, boolean[][] visited, int n, int m) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i,j));
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.getX();
            int y = point.getY();
            if(visited[x][y]) continue;;
            visited[x][y] = true;
            for(int k=0;k<4;k++) {
                int nx = x + DX[k];
                int ny = y + DY[k];
                if(checkBound(nx,ny,n,m) && !visited[nx][ny] && lab[nx][ny] == 0) {
                    queue.offer(new Point(nx,ny));
                }
            }
        }
    }

    private static boolean checkBound(int nx, int ny, int n, int m) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }
}
