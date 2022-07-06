package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2146_다리_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] island = new int[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                island[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬의 국가 초기화
        initializeIsland(N, island);

        // 섬의 테두리 확인
        bridge = Integer.MAX_VALUE;
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < N ; col++) {
                if(island[row][col] != 0) {
                    // 다리놓기 시작
                    if (isOutside(row, col, N, island)) {
                        makeBridge(row, col, N, island);
                    }
                }
            }
        }
        System.out.println(bridge);
        br.close();
    }

    private static int bridge;
    private static void makeBridge(int row, int col, int n, int[][] island) {
        boolean[][][] visited = new boolean[n][n][4];
        visited[row][col][0] = visited[row][col][1] = visited[row][col][2] = visited[row][col][3] = true;
        Queue<Country> queue = new LinkedList<>();
        queue.add(new Country(row, col));
        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int loop = 0; loop < size; loop++) {
                Country country = queue.poll();
                int curRow = country.getRow();
                int curCol = country.getCol();
                for(int direction = 0; direction < 4; direction++) {
                    int nextRow = curRow + DX[direction];
                    int nextCol = curCol + DY[direction];
                    if(checkBound(nextRow, nextCol, n) && !visited[nextRow][nextCol][direction] && island[nextRow][nextCol] != island[row][col]) {
                        if(island[nextRow][nextCol] == 0) {
                            visited[nextRow][nextCol][direction] = true;
                            queue.add(new Country(nextRow, nextCol));
                        }
                        // 다른 국가 도착
                        else {
                            bridge = Math.min(bridge, count);
                            return;
                        }
                    }
                }

            }
            count++;
        }
    }

    private static boolean isOutside(int row, int col, int n, int[][] island) {
        boolean outside = false;
        for(int direction = 0; direction < 4; direction++) {
            int nextRow = row + DX[direction];
            int nextCol = col + DY[direction];
            // 테두리 a
            if(checkBound(nextRow, nextCol, n) && island[nextRow][nextCol] == 0) {
                outside = true;
                break;
            }
        }
        return outside;
    }

    static class Country {
        private int row;
        private int col;

        public Country(int row, int col) {
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

    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {1, -1, 0, 0};

    private static void initializeIsland(int n, int[][] island) {
        boolean[][] visited = new boolean[n][n];
        int countryNumber = 1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (!visited[row][col] && island[row][col] == 1) {
                    divideCountry(row, col, n, visited, island, countryNumber);
                    countryNumber++;
                }
            }
        }
    }

    private static void divideCountry(int row, int col, int n, boolean[][] visited, int[][] island, int countryNumber) {
        Queue<Country> queue = new LinkedList<>();
        queue.add(new Country(row, col));
        island[row][col] = countryNumber;
        while (!queue.isEmpty()) {
            Country country = queue.poll();
            int curRow = country.getRow();
            int curCol = country.getCol();
            if (visited[curRow][curCol]) continue;
            visited[curRow][curCol] = true;
            for (int direction = 0; direction < 4; direction++) {
                int nextRow = curRow + DX[direction];
                int nextCol = curCol + DY[direction];
                if(checkBound(nextRow, nextCol, n) && !visited[nextRow][nextCol] && island[nextRow][nextCol] == 1) {
//                    visited[nextRow][nextCol] = true;
                    island[nextRow][nextCol] = countryNumber;
                    queue.add(new Country(nextRow, nextCol));
                }
            }
        }
    }

    private static boolean checkBound(int curRow, int curCol, int n) {
        return curRow >= 0 && curRow < n && curCol >= 0 && curCol < n;
    }

}
