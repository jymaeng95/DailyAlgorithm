package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14620_꽃길 {
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] road = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                road[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = getMinPrice(N, road);
        System.out.println(rst);
        br.close();
    }

    private static int getMinPrice(int n, int[][] road) {
        // 1. 꽃  하나씩 심으면서 상하좌우 확인 check 후 안되는 경우 continue;
        // 2. 세개의 꽃이 만들어지는 경우 비용 더해서 최소확인
        int price = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n * n; i++) {
            if (!checkBoundary(i, n, visited)) continue;
            plantFlower(i, n, visited, road);
            for (int j = i + 1; j < n * n; j++) {
                if (!checkBoundary(j, n, visited)) continue;
                plantFlower(j, n, visited, road);
                for (int k = j + 1; k < n * n; k++) {
                    if (!checkBoundary(k, n, visited)) continue;
                    plantFlower(k, n, visited, road);
                    price = Math.min(price, getPrice(road, visited));
                    deplantFlower(k, n, visited);
                }
                deplantFlower(j, n, visited);
            }
            deplantFlower(i, n, visited);
        }
        return price;
    }

    private static int getPrice(int[][] road, boolean[][] visited) {
        int price = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                if (visited[i][j]) price += road[i][j];
            }
        }
        return price;
    }

    private static void deplantFlower(int i, int n, boolean[][] visited) {
        int x = i / n;
        int y = i % n;
        visited[x][y] = false;
        for (int k = 0; k < 4; k++) {
            int nx = x + DX[k];
            int ny = y + DY[k];
            visited[nx][ny] = false;
        }
    }

    private static boolean checkBoundary(int i, int n, boolean[][] visited) {
        int x = i / n;
        int y = i % n;
        for (int k = 0; k < 4; k++) {
            int nx = x + DX[k];
            int ny = y + DY[k];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny])
                return false;
        }
        return true;
    }

    private static void plantFlower(int i, int n, boolean[][] visited, int[][] road) {
        int x = i / n;
        int y = i % n;
        visited[x][y] = true;
        for (int k = 0; k < 4; k++) {
            int nx = x + DX[k];
            int ny = y + DY[k];
            visited[nx][ny] = true;
        }
    }
}
