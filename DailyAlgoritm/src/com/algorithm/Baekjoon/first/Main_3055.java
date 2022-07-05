package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055 {
    private static int R, C;
    private static boolean[][] visited;
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static Queue<Index> queue;

    static class Index {
        private int x;
        private int y;
        private int count;
        private String who;

        public Index(int x, int y, int count, String who) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.who = who;
        }

        public Index() {
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C];

        String[][] map = new String[R][C];

        for (int i = 0; i < R; i++) {
            String[] sp = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                map[i][j] = sp[j];
            }
        }

        //큐에 넣기 (물, 고슴도치 순서)
        Index hedgehog = new Index();
        queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j].equals("*"))
                    queue.offer(new Index(i, j, 0, map[i][j]));
                if (map[i][j].equals("S"))
                    hedgehog = new Index(i, j,0, map[i][j]);
            }
        }
        queue.offer(hedgehog);

        int count = bfs(map);
        String answer = (count > 0 ) ? String.valueOf(count): "KAKTUS";
        bw.write(answer);
        br.close();
        bw.close();
    }

    private static int bfs(String[][] map) {
        while (!queue.isEmpty()) {
            Index index = queue.poll();
            if (visited[index.x][index.y])
                continue;
            visited[index.x][index.y] = true;

            // 물의 경우 돌과 비버 굴 제외하고 이동 가능
            if (index.who.equals("*")) {
                for (int i = 0; i < DX.length; i++) {
                    int nx = DX[i] + index.x;
                    int ny = DY[i] + index.y;
                    if (nx >= 0 && nx < R && ny >= 0 && ny < C && !map[nx][ny].equals("X") && !map[nx][ny].equals("D")) {
                        map[nx][ny] = "*";
                        queue.offer(new Index(nx, ny, 0, map[nx][ny]));
                    }
                }
            } else {
                // 고슴도치의 경우 물이 아직 닿지 않은 부분(.)과 비버 굴만 이동 가능
                // 또한 방문한 곳은 이동 불가능
                for (int i = 0; i < DX.length; i++) {
                    int nx = DX[i] + index.x;
                    int ny = DY[i] + index.y;

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C && (map[nx][ny].equals(".") || map[nx][ny].equals("D")) && !visited[nx][ny]) {
                        if (map[nx][ny].equals("D"))
                            return index.count+1;
                        map[nx][ny] = "S";
                        queue.offer(new Index(nx, ny, index.count+1,map[nx][ny]));
                    }
                }
            }
        }
        return 0;
    }
}
