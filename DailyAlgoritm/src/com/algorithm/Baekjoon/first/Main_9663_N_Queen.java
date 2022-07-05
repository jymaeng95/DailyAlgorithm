package com.algorithm.Baekjoon.first;

import java.io.*;

public class Main_9663_N_Queen {
    private static int N;
    private static boolean[] visitedColumn;
    private static boolean[] visitedCrossUp;
    private static boolean[] visitedCrossDown;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        count = 0;
        visitedCrossDown = new boolean[2 * N - 1];
        visitedCrossUp = new boolean[2 * N - 1];
        visitedColumn = new boolean[N];
        NQueen(0);

        bw.write(String.valueOf(count));
        br.close();
        bw.close();
    }

    private static void NQueen(int cnt) {
        if (cnt == N) {
            count++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visitedColumn[i] || visitedCrossUp[i + cnt] || visitedCrossDown[cnt - i + N - 1])
                continue;
            visitedColumn[i] = true;
            visitedCrossUp[i + cnt] = true;
            visitedCrossDown[cnt - i + N - 1] = true;
            NQueen(cnt + 1);
            visitedColumn[i] = false;
            visitedCrossUp[i + cnt] = false;
            visitedCrossDown[cnt - i + N - 1] = false;
        }
    }
}
