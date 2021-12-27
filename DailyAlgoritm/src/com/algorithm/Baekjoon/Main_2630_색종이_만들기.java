package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2630_색종이_만들기 {
    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
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

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static int blue, white;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        int N = Integer.parseInt(br.readLine());
        int[][] colorPaper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                colorPaper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 파란색 색종이, 하얀색 색종이 초기화
        blue = 0;
        white = 0;
        makeColorPaper(0, N, 0, N, colorPaper);
        System.out.println(white);
        System.out.println(blue);
        br.close();
    }

    private static void makeColorPaper(int rowStart, int rowEnd, int colStart, int colEnd, int[][] colorPaper) {
        /*
         * 0. 색종이 사이즈 1인 경우 종류 판단 후 return
         * 1. (0,0) 에서 BFS 진행 ,
         * 2. N*N개인지 판단 맞으면 1인지 0인지 판단 후 blue or white counting
         * 3. 아닌 경우 4분할
         * 4. BFS 시 범위 주의
         */

        // 0. 색종이 사이즈 1인 경우 종류 판단후  return
        if (rowStart == rowEnd && colStart == colEnd) {
            if (colorPaper[rowStart][colStart] == 1) blue++;
            else white++;
            return;
        }

        // 1. (0,0) BFS
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowEnd - rowStart][colEnd - colStart];
        queue.offer(new Point(rowStart, colStart));
        int count = 0;
        int kind = colorPaper[rowStart][colStart];
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.getX();
            int y = point.getY();
            if (visited[x - rowStart][y - colStart]) continue;
            visited[x - rowStart][y - colStart] = true;
            count++;    //색종이 개수
            for (int i = 0; i < 4; i++) {
                int nx = x + DX[i];
                int ny = y + DY[i];
                if (checkBound(nx, ny, rowStart, rowEnd, colStart, colEnd) && !visited[nx - rowStart][ny - colStart] && colorPaper[nx][ny] == kind) {
                    queue.offer(new Point(nx, ny));
                }
            }
        }

        // 2. N*N개인지 판단
        if (count == (rowEnd - rowStart) * (colEnd - colStart)) {
            if (kind == 1) blue++;
            else white++;
            return;
        }

        // 3. 아닌 경우 4 분할
        makeColorPaper(rowStart, (rowStart + rowEnd) / 2, colStart, (colStart + colEnd) / 2, colorPaper);
        makeColorPaper(rowStart, (rowStart + rowEnd) / 2, (colStart + colEnd) / 2, colEnd, colorPaper);
        makeColorPaper((rowStart + rowEnd) / 2, rowEnd, colStart, (colStart+colEnd) / 2, colorPaper);
        makeColorPaper((rowStart + rowEnd) / 2, rowEnd, (colStart + colEnd) / 2, colEnd, colorPaper);
    }

    private static boolean checkBound(int nx, int ny, int rowStart, int rowEnd, int colStart, int colEnd) {
        return nx >= rowStart && nx < rowEnd && ny >= colStart && ny < colEnd;
    }
}
