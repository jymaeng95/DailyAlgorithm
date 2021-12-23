package com.algorithm.thisiscodingtest.BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P353_Q21_인구_이동 {
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static int N, L, R;

    static class City {
        private int x;
        private int y;
        private int people;

        public City(int x, int y, int people) {
            this.x = x;
            this.y = y;
            this.people = people;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getPeople() {
            return people;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] land = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = movePeople(land);
        bw.write(String.valueOf(rst));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int movePeople(int[][] land) {
        /*
         * 1. (1,1) 부터 인구이동
         * 2. 인구 이동 하면 다시 처음부터 BFS (count ++, move = true)
         * 3. if move false break;
         */

        int day = 0;
        while (true) {
            boolean move = false;
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j, land, visited)) {
                            move = true;
                            break;
                        }
                    }
                }
                if (move) break;
            }
            if (!move) break;
            day++;
        }

        return day;
    }

    private static boolean bfs(int i, int j, int[][] land, boolean[][] visited) {
        Queue<City> queue = new LinkedList<>();
        queue.offer(new City(i, j, land[i][j]));
        int count = 0;
        int people = 0;
        boolean move = false;
        while (!queue.isEmpty()) {
            City city = queue.poll();
            int x = city.getX();
            int y = city.getY();
            if (visited[x][y]) continue;
            visited[x][y] = true;   // 도시 방문처리
            people += city.getPeople();
            count ++;   //도시 개수 카운트
            for (int k = 0; k < 4; k++) {
                int nx = x + DX[k];
                int ny = y + DY[k];
                if(checkBound(nx,ny) && !visited[nx][ny]) {
                    int sub = Math.abs(land[x][y] - land[nx][ny]);
                    if(sub >= L && sub <= R) {
                        queue.offer(new City(nx,ny,land[nx][ny]));
                        move = true;
                    }
                }
            }
            if(!move) visited[x][y] = false;
        }
        if(move) {
            for(int k=0;k<N;k++) {
                for(int z=0;z<N;z++) {
                    if(visited[k][z]) {
                        land[k][z] = people / count;
                    }
                }
            }
        }

        return move;
    }

    private static boolean checkBound(int nx, int ny) {
        return nx >=0 && nx < N && ny >= 0 && ny < N;
    }
}
