package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13565_침투 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 배열 초기화
        int[][] fabric = new int[N][M];
        for (int row = 0; row < N; row++) {
            String[] split = br.readLine().split("");
            for (int col = 0; col < M; col++) {
                fabric[row][col] = Integer.parseInt(split[col]);
            }
        }

        String rst = checkPercolate(N, M, fabric);
        System.out.println(rst);

        br.close();
    }

    private static boolean end;
    private static final int[] DX = {0, 0, -1, 1};
    private static final int[] DY = {1, -1, 0, 0};

    private static String checkPercolate(int n, int m, int[][] fabric) {
        // 1행 1열부터 모든 열에 대해 DFS 탐색 시작
        boolean[][] visited = new boolean[n][m];
        end = false;
        for (int col = 0; col < m; col++) {
            // 방문하지 않았고, 0인경우 전류 침투 가능
            if (!visited[0][col] && fabric[0][col] == 0) {
                visited[0][col] = true;
                percolate(0, col, n, m, fabric, visited);
                if (end) return "YES";
            }
        }
        return "NO";
    }

    private static void percolate(int row, int col, int n, int m, int[][] fabric, boolean[][] visited) {
        // 탈출 조건 (현재 행이 마지막 행인 경우)
        if (row == n - 1) {
            end = true;
            return;
        }

        for (int direction = 0; direction < 4; direction++) {
            int nextRow = row + DX[direction];
            int nextCol = col + DY[direction];

            if (checkBound(nextRow, nextCol, n, m) && !visited[nextRow][nextCol] && fabric[nextRow][nextCol] == 0) {
                visited[nextRow][nextCol] = true;
                percolate(nextRow, nextCol, n, m, fabric, visited);
            }
        }
    }

    private static boolean checkBound(int nextRow, int nextCol, int n, int m) {
        return nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m;
    }
}
