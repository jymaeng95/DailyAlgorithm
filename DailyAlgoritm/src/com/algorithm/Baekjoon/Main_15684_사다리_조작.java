package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15684_사다리_조작 {
    private static int N, M, H;
    private static final int RIGHT = 1, LEFT = 2;
    private static int ladderCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int[][] ladder = new int[H + 1][N + 1];
        for (int rowLine = 0; rowLine < M; rowLine++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            // 사다리 이어주기 height 높이에서 start -> end로 이어주기 (1 : 왼->오, 2,
            ladder[height][start] = RIGHT;
            ladder[height][start + 1] = LEFT;
        }

        ladderCount = Integer.MAX_VALUE;
        manipulateLadder(ladder, 0);

        if(ladderCount == Integer.MAX_VALUE) ladderCount = -1;
        System.out.println(ladderCount);

        br.close();
    }

    private static void manipulateLadder(int[][] ladder, int count) {
        if(count > 3) return;

        // 사다리가 끝까지 자기 자신에 도착하는지 확인
        if (reachLadder(ladder)) {
            ladderCount = Math.min(ladderCount, count);

            return;
        }

        for (int height = 1; height <= H; height++) {
            // 마지막에서 오른쪽으로 놓는 경우는 없으므로
            for (int col = 1; col < N; col++) {
                if (ladder[height][col - 1] == 0 && ladder[height][col + 1] == 0) {
                    ladder[height][col] = RIGHT;
                    ladder[height][col + 1] = LEFT;
                    manipulateLadder(ladder, count + 1);
                    ladder[height][col] = 0;
                    ladder[height][col + 1] = 0;
                }
            }
        }
    }

    private static boolean reachLadder(int[][] ladder) {
        for (int col = 1; col <= N; col++) {
            int pos = col;
            for (int height = 1; height <= H; height++) {
                if (ladder[height][pos] != 0) {
                    if (ladder[height][pos] == RIGHT) pos += 1;
                    else pos -= 1;
                }
            }
            // 자신의 시작 위치에 도착 안하는 경우
            if (pos != col) return false;
        }
        return true;
    }
}
