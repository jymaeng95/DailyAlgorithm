package com.algorithm.thisiscodingtest.BFS;

import java.io.*;
import java.util.*;

public class P344_Q17_경쟁적_전염 {
    static class Point {
        private int no;
        private int x;
        private int y;

        public Point(int no, int x, int y) {
            this.no = no;
            this.x = x;
            this.y = y;
        }

        public int getNo() {
            return no;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    private static final int[] DX = {1,-1,0,0};
    private static final int[] DY = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        /*
         * 1. N * N 시험관, K = 1~K번 바이러스 번호
         * 2. 2째줄 - N째줄 시험관 정보
         * 3. S: 몇 초후 , X, Y: 타겟 바이러스 좌표
         */

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] bottle = new int[N][N];

        //시험관 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                bottle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int virus = getVirus(S, X, Y, N, bottle);
        bw.write(String.valueOf(virus));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int getVirus(int s, int x, int y, int n, int[][] bottle) {
        /*
         * 1. 바이러스 정보 큐에 넣기
         * 2. 큐 넣은 큐사이즈만큼 for문 돌리고 초 추가
         * 3. 큐가 비거나 지정한 시간 지나고 특정 좌표 바이러스 번호 출력
         */

        // 1. 바이러스 정보 큐에 넣기
        Queue<Point> queue = new LinkedList<>();
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(bottle[i][j] != 0) {
                    list.add(new Point(bottle[i][j], i,j));
                }
            }
        }
        list.sort(Comparator.comparingInt(o -> o.getNo()));
        // 2. 바이러스 번호별로 BFS 진행
        bfs(s,n,bottle,list);
        return bottle[x - 1][y - 1];
    }

    private static void bfs(int s, int n, int[][] bottle, List<Point> list) {
        Queue<Point> queue = new LinkedList<>(list);
        boolean[][] visited = new boolean[n][n];
        int second = 0;
        while(!queue.isEmpty() && s > second) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                Point point = queue.poll();
                int x = point.getX();
                int y = point.getY();
                int no = point.getNo();

                if(visited[x][y]) continue;
                visited[x][y] = true;
                for(int j=0;j<4;j++) {
                    int nx = x + DX[j];
                    int ny = y + DY[j];
                    if(checkBound(nx,ny,n) && !visited[nx][ny] && bottle[nx][ny] == 0) {
                        bottle[nx][ny] = no;
                        queue.offer(new Point(no, nx,ny));
                    }
                }
            }
            second++;
        }
    }

    private static boolean checkBound(int nx, int ny, int n) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n;
    }
}
