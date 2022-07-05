package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7562 {
    /*
    체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다.
    나이트가 이동하려고 하는 칸이 주어진다. 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
     */
    static class Knight {
        private int x;
        private int y;

        public Knight(int x, int y) {
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

    public static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2,};
    public static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());
        for (int i = 0; i < TC; i++) {
            int N = Integer.parseInt(br.readLine());
            boolean[][] visited = new boolean[N][N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            Knight startKnight = new Knight(startX, startY);

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            Knight endKnight = new Knight(endX, endY);

            if (startKnight.getX() == endKnight.getX() && startKnight.getY() == endKnight.getY()) {
                bw.write(String.valueOf(0));
                bw.newLine();
                continue;
            }
            int count = bfs(startKnight, endKnight, N, visited);
            bw.write(String.valueOf(count));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static int bfs(Knight startKnight, Knight endKnight, int N, boolean[][] visited) {
        Queue<Knight> que = new LinkedList<>();
        que.offer(startKnight);
        int count = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Knight knight = que.poll();
                if (visited[knight.getX()][knight.getY()])
                    continue;
                visited[knight.getX()][knight.getY()] = true;
                for (int j = 0; j < dx.length; j++) {
                    int nx = knight.getX() + dx[j];
                    int ny = knight.getY() + dy[j];
                    if (nx == endKnight.getX() && ny == endKnight.getY())
                        return count + 1;
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N)
                        que.offer(new Knight(nx, ny));
                }
            }
            count++;
        }
        return count;
    }
}
