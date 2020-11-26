package com.algorithm.Baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4963 {
    /*
     *한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다.
     *두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다.
     *지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.
     */
    static class Index {
        private int x;
        private int y;

        Index(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            //마지막 입력
            if (width == 0 && height == 0)
                break;

            int[][] map = new int[height][width];
            boolean[][] visited = new boolean[height][width];
            int count = 0;

            //지도 입력
            for (int i = 0; i < height; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < width; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        bfs(visited, map, i, j,width,height);
                        count++;
                    }
                }
            }
            bw.write(String.valueOf(count));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static void bfs(boolean[][] visited, int[][] map, int i, int j,int width, int height) {
        Queue<Index> que = new LinkedList<>();
        que.offer(new Index(i, j));
        while (!que.isEmpty()) {
            Index idx = que.poll();
            if (visited[idx.getX()][idx.getY()])
                continue;
            visited[idx.getX()][idx.getY()] = true;
            int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
            for (int k = 0; k < dx.length; k++) {
                int nx = idx.getX() + dx[k];
                int ny = idx.getY() + dy[k];
                if (nx >= 0 && nx < height && ny >= 0 && ny < width && map[nx][ny] == 1 && !visited[nx][ny]) {
                    que.offer(new Index(nx, ny));
                }
            }
        }
    }

}
