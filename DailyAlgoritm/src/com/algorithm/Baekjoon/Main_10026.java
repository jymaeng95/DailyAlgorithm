package com.algorithm.Baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main_10026 {
    /*
     * 적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1)
     * 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)
     * 그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
     */
    static class RGB {
        private int x;
        private int y;
        private String color;

        public RGB(int x, int y, String color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String getColor() {
            return color;
        }
    }

    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[][] district = new String[N][N];

        boolean[][] visited = new boolean[N][N];
        boolean[][] colorVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] splitColor = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                district[i][j] = splitColor[j];
            }
        }

        int notColorWeakness = 0;
        int colorWeakness = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    notColorWeak(N, visited, district, new RGB(i, j, district[i][j]));
                    notColorWeakness++;
                }
                if (!colorVisited[i][j]) {
                    colorWeak(N, colorVisited, district, new RGB(i, j, district[i][j]));
                    colorWeakness++;
                }
            }
        }

        bw.write(String.valueOf(notColorWeakness));
        bw.write(" ");
        bw.write(String.valueOf(colorWeakness));
        bw.newLine();

        br.close();
        bw.close();
    }

    private static void notColorWeak(int N, boolean[][] visited, String[][] district, RGB rgb) {
        Queue<RGB> que = new LinkedList<>();
        que.offer(rgb);
        while (!que.isEmpty()) {
            RGB color = que.poll();
            if (visited[color.getX()][color.getY()])
                continue;
            visited[color.getX()][color.getY()] = true;
            for (int i = 0; i < DX.length; i++) {
                int nx = color.getX() + DX[i];
                int ny = color.getY() + DY[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && district[nx][ny].equals(color.getColor())) {
                    que.offer(new RGB(nx, ny, district[nx][ny]));
                }
            }
        }
    }

    private static void colorWeak(int N, boolean[][] colorVisited, String[][] district, RGB rgb) {
        Queue<RGB> que = new LinkedList<>();
        que.offer(rgb);
        while (!que.isEmpty()) {
            RGB color = que.poll();
            String rgbColor = color.getColor();
            if (colorVisited[color.getX()][color.getY()]) {
                continue;
            }
            colorVisited[color.getX()][color.getY()] = true;
            for (int i = 0; i < DX.length; i++) {
                int nx = color.getX() + DX[i];
                int ny = color.getY() + DY[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !colorVisited[nx][ny]) {

                    if (rgbColor.equals("R") || rgbColor.equals("G")) {
                        if (!district[nx][ny].equals("B")) {
                            que.offer(new RGB(nx, ny, district[nx][ny]));
                        }
                        continue;
                    }
                    if(rgbColor.equals(district[nx][ny]))
                        que.offer(new RGB(nx, ny, district[nx][ny]));
                }
            }
        }
    }

}
