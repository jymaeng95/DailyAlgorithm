package com.second.algorithm.softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Test_비트맵 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] original = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                original[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] rst = compactImage(N, M, original);
        for (int row = 0; row < rst.length; row++) {
            for (int col = 0; col < rst[row].length; col++) {
                System.out.print(rst[row][col] + " ");
            }
            System.out.println();
        }

        br.close();
    }

    private static int[][] compactImage(int n, int m, int[][] original) {
        int[][] compact = new int[n / 2][m / 2];
        for (int row = 0, compactRow = 0; row < n; row += 2, compactRow++) {
            for (int col = 0, compactCol = 0; col < m; col += 2, compactCol++) {
                int sum = original[row][col] + original[row + 1][col + 1] + original[row][col + 1] + original[row + 1][col];
                long avg = Math.round((double) sum / 4);
                compact[compactRow][compactCol] = (int) avg;
            }
        }

        return compact;
    }
}
/**
 * 8 10
 * 252 123 65 18 19 253 13 231 123 132
 * 123 35 14 14 17 13 154 234 141 161
 * 41 15 138 253 255 199 13 124 41 17
 * 23 16 193 233 233 121 14 125 31 124
 * 1 18 231 233 233 141 15 234 53 66
 * 44 15 241 231 155 231 18 189 35 31
 * 55 14 13 171 123 15 16 156 123 81
 * 67 12 14 1 1 17 192 151 141 91
 */
