package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14502_연구소_최적화 {
    private static int N, M;
    private static int[][] map;
    private static final int EMPTY = 0, WALL = 1, VIRUS = 2;
    private static List<Point> emptyList, virusList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열
        map = new int[N][M];    // 지도

        emptyList = new ArrayList<>();
        virusList = new ArrayList<>();

        // 지도 초기화
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int type = Integer.parseInt(st.nextToken());
                map[row][col] = type;
                if (type == EMPTY) emptyList.add(new Point(row, col));
                if (type == VIRUS) virusList.add(new Point(row, col));
            }
        }

        int rst = getSafeAreas();
        System.out.println(rst);

        br.close();
    }

    private static int getSafeAreas() {
        int safeArea = 0;

        // 벽 설치하기
        for (int first = 0; first < emptyList.size(); first++) {
            for (int second = first + 1; second < emptyList.size(); second++) {
                for (int third = second + 1; third < emptyList.size(); third++) {
                    Point firstWall = emptyList.get(first);
                    Point secondWall = emptyList.get(second);
                    Point thirdWall = emptyList.get(third);

                    // 벽을 설치한 이후의 배열을 새로 사용하기위한 복사
                    int[][] buildMap = copyMap();
                    buildMap[firstWall.getRow()][firstWall.getCol()] = WALL;
                    buildMap[secondWall.getRow()][secondWall.getCol()] = WALL;
                    buildMap[thirdWall.getRow()][thirdWall.getCol()] = WALL;

                    // 바이러스 퍼뜨리기
                    boolean[][] spread = new boolean[N][M];
                    for (Point virus : virusList) {
                        spreadVirus(virus, spread, buildMap);
                    }

                    // 안전 영역 구하기
                    safeArea = Math.max(safeArea, checkSafeArea(buildMap));
                }
            }
        }

        return safeArea;
    }

    private static int checkSafeArea(int[][] buildMap) {
        int count = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (buildMap[row][col] == EMPTY) count++;
            }
        }
        return count;
    }

    private static final int[] DX = {0, 0, 1, -1};
    private static final int[] DY = {1, -1, 0, 0};

    private static void spreadVirus(Point virus, boolean[][] spread, int[][] buildMap) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(virus);

        while (!queue.isEmpty()) {
            Point virusPoint = queue.poll();
            int row = virusPoint.getRow();
            int col = virusPoint.getCol();

            if (spread[row][col]) continue;
            spread[row][col] = true;

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = row + DX[direction];
                int nextCol = col + DY[direction];

                if (checkBoundary(nextRow, nextCol) && !spread[nextRow][nextCol] && buildMap[nextRow][nextCol] == EMPTY) {
                    buildMap[nextRow][nextCol] = VIRUS;
                    queue.add(new Point(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean checkBoundary(int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M;
    }

    // 배열 복사
    private static int[][] copyMap() {
        int[][] buildMap = new int[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                buildMap[row][col] = map[row][col];
            }
        }
        return buildMap;
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
