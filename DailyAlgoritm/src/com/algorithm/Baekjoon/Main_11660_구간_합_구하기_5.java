package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_구간_합_구하기_5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] num = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                num[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] sum = sumArray(N, num);

        for (int test = 1; test <= M; test++) {
            st = new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());

            int rst = sum[endRow][endCol] - (sum[startRow - 1][endCol] + sum[endRow][startCol - 1] - sum[startRow - 1][startCol - 1]);
            System.out.println(rst);
        }

        br.close();
    }

    private static int[][] sumArray(int n, int[][] num) {
        int[][] sum = new int[n + 1][n + 1];
        // 행에 대하여 누적합
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                sum[row][col] = sum[row][col-1] + num[row][col];
            }
        }

        // 열에 대하여 누적합
        for (int col = 1; col <= n; col++) {
            for (int row = 1; row <= n; row++) {
                sum[row][col] += sum[row - 1][col];
            }
        }

        return sum;
    }

}
