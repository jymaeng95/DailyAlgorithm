package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2151_거울_설치 {
    private static int startRow, startCol, endRow, endCol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] house = new char[N][N];

        boolean first = true;  // 첫 문 인덱스 찾은 경우
        for (int row = 0; row < N; row++) {
            String rowData = br.readLine();
            for (int col = 0; col < N; col++) {
                char data = rowData.charAt(col);
                house[row][col] = data;

                // 문의 시작점과 끝점 찾기
                if (data == '#') {
                    if (first) {
                        startRow = row;
                        startCol = col;
                        first = false;
                    } else {
                        endRow = row;
                        endCol = col;
                    }
                }
            }
        }

        int rst = installMirror(N, house);
        System.out.println(rst);

        br.close();
    }

    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int installMirror(int n, char[][] house) {
        Queue<House> queue = new LinkedList<>();

        // 3차원 배열로 선언해 방향별로 개수 카운트
        int[][][] visited = new int[n][n][4];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                Arrays.fill(visited[row][col], n * n);
            }
        }
        // 방향 별로 시작점에 체크후 넣어주기
        for (int direction = 0; direction < 4; direction++) {
            queue.add(new House(startRow, startCol, direction, 0));
            visited[startRow][startCol][direction] = 0;
        }

        int mirrorCount = n * n;
        while (!queue.isEmpty()) {
            House housePosition = queue.poll();
            int row = housePosition.getRow();
            int col = housePosition.getCol();
            int direction = housePosition.getDirection();

            // 최초로 도달하는 경우 break
            if (row == endRow && col == endCol) {
                mirrorCount = Math.min(mirrorCount, housePosition.getInstallCount());
                continue;
            }

            int nextRow = row + DX[direction];
            int nextCol = col + DY[direction];
            if (checkBoundary(nextRow, nextCol, n) && house[nextRow][nextCol] != '*') {

                // 거울을 설치하는 경우 (현재 방향 다음 위치 최소 설치 거울 개수 보다 현재까지 설치한 거울 개수에 다음 위치에 거울 한개 더 설치한 경우가 작거나 같다면 해당 경로로 가도됨)
                if (visited[nextRow][nextCol][direction] >= housePosition.getInstallCount() + 1 && house[nextRow][nextCol] == '!') {
                    visited[nextRow][nextCol][direction] = housePosition.getInstallCount() + 1;

                    // 45도 방향 설치는 2가지 (↘ ↗) 따라서 큐에 두개 넣어주기
                    if (direction == LEFT || direction == RIGHT) {
                        queue.add(new House(nextRow, nextCol, UP, housePosition.getInstallCount() + 1));
                        queue.add(new House(nextRow, nextCol, DOWN, housePosition.getInstallCount() + 1));
                    } else {
                        queue.add(new House(nextRow, nextCol, LEFT, housePosition.getInstallCount() + 1));
                        queue.add(new House(nextRow, nextCol, RIGHT, housePosition.getInstallCount() + 1));
                    }
                }
                // ., #,  !인 경우에 그대로 큐에 넣기 ( !의 경우 설치 안하는 경우도 있기 때문)
                if (visited[nextRow][nextCol][direction] >= housePosition.getInstallCount()) {
                    visited[nextRow][nextCol][direction] = housePosition.getInstallCount();
                    queue.add(new House(nextRow, nextCol, direction, housePosition.getInstallCount()));
                }
            }
        }

        return mirrorCount;
    }

    private static boolean checkBoundary(int nextRow, int nextCol, int n) {
        return nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n;
    }

    static class House {
        private int row;
        private int col;
        private int direction;
        private int installCount;

        public House(int row, int col, int direction, int installCount) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.installCount = installCount;
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

        public int getInstallCount() {
            return installCount;
        }
    }
}
