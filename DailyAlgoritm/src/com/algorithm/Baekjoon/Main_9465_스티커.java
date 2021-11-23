package com.algorithm.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/* 다시 풀기 */
public class Main_9465_스티커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][N];

            for(int k=0; k<2; k++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    sticker[k][j] = Integer.parseInt(st.nextToken());
                }
            }

            int rst = getMaxValue(sticker, N);
            bw.write(String.valueOf(rst));
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getMaxValue(int[][] sticker, int N) {
        int up = 0;
        int down = 0;

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                up += sticker[0][i];
                down += sticker[1][i];
            } else {
                up += sticker[1][i];
                down += sticker[0][i];
            }
        }
        return Math.max(up,down);
    }
}
