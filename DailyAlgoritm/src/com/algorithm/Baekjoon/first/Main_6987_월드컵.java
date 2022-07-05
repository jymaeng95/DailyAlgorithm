package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6987_월드컵 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 0; tc < 4; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[][] result = new int[6][3];

            for (int i = 0; i < 6; i++) {
                result[i][0] = Integer.parseInt(st.nextToken());
                result[i][1] = Integer.parseInt(st.nextToken());
                result[i][2] = Integer.parseInt(st.nextToken());
            }

            int[][] win = new int[6][6];
            int[][] draw = new int[6][6];
            int[][] lose = new int[6][6];

            // 승 무 패
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (i != j) {
                        win[i][j] = 1;
                        lose[i][j] = -1;
                    }
                }
            }

            int rst = verifyWorldCup(result, win, draw, lose);
        }

        br.close();
    }

    private static int verifyWorldCup(int[][] result, int[][] win, int[][] draw, int[][] lose) {
//        boolean[]
        for (int i = 0; i < 6 * 6; i++) {
            for (int j = i + 1; j < 6 * 6; j++) {
                for (int k = j + 1; k < 6 * 6; k++) {
                    return 0;
                }
            }
        }

        return 0;
    }
}
