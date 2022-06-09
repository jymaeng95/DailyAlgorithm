package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6087_레이저_통신 {
    private static int startRow, startCol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        String[][] map = new String[N][M];
        for (int row = 0; row < N; row++) {
            String[] split = br.readLine().split("");
            for (int col = 0; col < M; col++) {
                if (split[col].equals("C")) {
                    startRow = row;
                    startCol = col;
                }
                map[row][col] = split[col];
            }
        }

        int rst = settingMirror(N, M, map);
        System.out.println(rst);

        br.close();
    }

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    private static int settingMirror(int n, int m, String[][] map) {
//        PriorityQueue<Road> pq = new PriorityQueue<>();
        Queue<Road> pq = new LinkedList<>();
        int[][] mirror = new int[n][m];
        for (int row = 0; row < n; row++) {
            Arrays.fill(mirror[row], Integer.MAX_VALUE);
        }

        pq.add(new Road(startRow, startCol, -1, 0));
        mirror[startRow][startCol] = 0;

        int mirrorCount = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Road curRoad = pq.poll();

            // C에 도착하고 ,시작 행 열이 아닌 경우
            if (map[curRoad.getRow()][curRoad.getCol()].equals("C") && curRoad.getRow() != startRow && curRoad.getCol() != startCol) {
                mirrorCount = Math.min(mirrorCount, mirror[curRoad.getRow()][curRoad.getCol()]);
                continue;
            }

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = curRoad.getRow() + DX[direction];
                int nextCol = curRoad.getCol() + DY[direction];

                if (checkBound(nextRow, nextCol, n, m) && !map[nextRow][nextCol].equals("*")) {
                    // 최초 방향이 아니고, 이전 방향과 현재 방향이 다르면
                    if (curRoad.getDirection() != -1 && direction != curRoad.getDirection() && mirror[nextRow][nextCol] >= mirror[curRoad.getRow()][curRoad.getCol()] + 1) {
                        // 거울 개수 한 개 추가
                        mirror[nextRow][nextCol] = mirror[curRoad.getRow()][curRoad.getCol()] + 1;
                        pq.offer(new Road(nextRow, nextCol, direction, mirror[nextRow][nextCol]));
                    }
                    else if ((curRoad.getDirection() == -1 || direction == curRoad.getDirection()) && mirror[nextRow][nextCol] >= mirror[curRoad.getRow()][curRoad.getCol()]) {
                        mirror[nextRow][nextCol] = mirror[curRoad.getRow()][curRoad.getCol()];
                        pq.offer(new Road(nextRow, nextCol, direction, mirror[nextRow][nextCol]));
                    }
                }
            }
        }

        return mirrorCount;
    }

    private static boolean checkBound(int nextRow, int nextCol, int n, int m) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < m;
    }

    static class Road implements Comparable<Road> {
        private int row;
        private int col;
        private int direction;
        private int mirror;

        public Road(int row, int col, int direction, int mirror) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.mirror = mirror;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getDirection() {
            return direction;
        }

        public int getMirror() {
            return mirror;
        }

        @Override
        public int compareTo(Road o) {
            return Integer.compare(this.mirror, o.mirror);
        }
    }
}
