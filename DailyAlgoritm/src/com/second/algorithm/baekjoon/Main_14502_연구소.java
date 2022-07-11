package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_연구소 {
    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열
        map = new int[N][M];    // 지도

        // 지도 초기화
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = getSafeAreas();
        System.out.println(rst);

        br.close();
    }

    private static int safeArea;

    private static int getSafeAreas() {
        buildWall(0);

        return safeArea;
    }

    private static final int VIRUS = 2, WALL = 1, EMPTY = 0;

    private static void buildWall(int wall) {
        // 벽이 3개 설치된 경우
        if (wall == 3) {
            // 배열을 복사한다.
            int[][] buildMap = copyArray();
            // 먼저 바이러스를 퍼뜨린다.
            spreadVirus(buildMap);

            // 남은 안전 영역을 체크한다.
            checkSafeArea(buildMap);
            return;
        }

        // 빈 공간에 벽을 설치한다.
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (map[row][col] == EMPTY) {
                    map[row][col] = WALL;
                    buildWall(wall + 1);
                    map[row][col] = EMPTY;
                }
            }
        }
    }

    // 안전 영역의 개수를 체크한다.
    private static void checkSafeArea(int[][] buildMap) {
        int count = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (buildMap[row][col] == EMPTY)
                    count++;
            }
        }

        safeArea = Math.max(safeArea, count);
    }

    // 벽을 설치한 배열을 컨트롤 하기 위해서 배열을 복사한다.
    private static int[][] copyArray() {
        int[][] buildMap = new int[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                buildMap[row][col] = map[row][col];
            }
        }

        return buildMap;
    }

    // 바이러스를 퍼뜨린다. (BFS) 이용
    private static void spreadVirus(int[][] buildMap) {
        boolean[][] visited = new boolean[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (!visited[row][col] && buildMap[row][col] == VIRUS) {
                    bfs(row, col, visited, buildMap);
                }
            }
        }
    }

    private static final int[] DX = {0, 0, 1, -1};
    private static final int[] DY = {1, -1, 0, 0};
    private static void bfs(int row, int col, boolean[][] visited, int[][] buildMap) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int curRow = point.getRow();
            int curCol = point.getCol();

            if (visited[curRow][curCol]) continue;
            visited[curRow][curCol] = true;

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = curRow + DX[direction];
                int nextCol = curCol + DY[direction];

                if (checkBoundary(nextRow, nextCol) && !visited[nextRow][nextCol] && buildMap[nextRow][nextCol] == EMPTY) {
                    buildMap[nextRow][nextCol] = VIRUS;
                    queue.add(new Point(nextRow, nextCol));
                }
            }
        }
    }

    // 지도의 경계 체크
    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M;
    }

    static class Point {
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
