package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1451_직사각형으로_나누기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] square = new int[N + 1][M + 1];
        for (int row = 1; row <= N; row++) {
            String[] split = br.readLine().split("");
            for (int col = 1; col <= M; col++) {
                square[row][col] = Integer.parseInt(split[col - 1]);
            }
        }

        int[][] sum = sumArray(N, M, square);
        long rst = getMaxSquare(N, M, sum);
        System.out.println(rst);

        br.close();
    }

    private static long getMaxSquare(int n, int m, int[][] sum) {
        // 가로인 경우 (가로줄 2개)
        long rst = onlyRow(n, m, sum);

        // 세로인 경우 (세로줄 2개)
        rst = Math.max(rst, onlyCol(n, m, sum));

        // 가로 세로인 경우 (가로줄 세로줄)
        return Math.max(rst, rowAndCol(n, m, sum));
    }

    private static long rowAndCol(int n, int m, int[][] sum) {
        long area = 0;
        for (int row = 1; row <= n - 1; row++) {
            for (int col = 1; col <= m - 1; col++) {
                // 위가 2개
                area = Math.max(area, (calcArea(sum, 1, 1, row, m) * calcArea(sum, row + 1, 1, n, col) * calcArea(sum, row + 1, col + 1, n, m)));

                // 아래가 2개
                area = Math.max(area, (calcArea(sum, row + 1, 1, n, m) * calcArea(sum, 1, 1, row, col) * calcArea(sum, 1, col + 1, row, m)));

                // 왼쪽이 2개
                area = Math.max(area, (calcArea(sum, 1, 1, n, col) * calcArea(sum, 1, col + 1, row, m) * calcArea(sum, row + 1, col + 1, n, m)));

                // 오른쪽이 2개
                area = Math.max(area, (calcArea(sum, 1, col + 1, n, m) * calcArea(sum, 1, 1, row, col) * calcArea(sum, row + 1, 1, n, col)));
            }
        }
        return area;
    }


    private static long onlyCol(int n, int m, int[][] sum) {
        long area = 0;
        for (int firstCol = 1; firstCol <= m - 2; firstCol++) {
            for (int secondCol = firstCol + 1; secondCol <= m - 1; secondCol++) {
                area = Math.max(area, (calcArea(sum, 1, 1, n, firstCol) * calcArea(sum, 1, firstCol + 1, n, secondCol) * calcArea(sum, 1, secondCol + 1, n, m)));
            }
        }

        return area;
    }

    private static long onlyRow(int n, int m, int[][] sum) {
        long area = 0;
        for (int firstRow = 1; firstRow <= n - 2; firstRow++) {
            for (int secondRow = firstRow + 1; secondRow <= n - 1; secondRow++) {
                area = Math.max(area, (calcArea(sum, 1, 1, firstRow, m) * calcArea(sum, firstRow + 1, 1, secondRow, m) * calcArea(sum, secondRow + 1, 1, n, m)));
            }
        }

        return area;
    }

    private static long calcArea(int[][] sum, int x1, int y1, int x2, int y2) {
        return sum[x2][y2] - (sum[x1 - 1][y2] + sum[x2][y1 - 1] - sum[x1 - 1][y1 - 1]);
    }

    private static int[][] sumArray(int n, int m, int[][] square) {
        int[][] sum = new int[n + 1][m + 1];
        // 행에 대하여 누적합
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                sum[row][col] = sum[row][col - 1] + square[row][col];
            }
        }

        // 열에 대하여 누적합
        for (int col = 1; col <= m; col++) {
            for (int row = 1; row <= n; row++) {
                sum[row][col] += sum[row - 1][col];
            }
        }

        return sum;
    }
}
