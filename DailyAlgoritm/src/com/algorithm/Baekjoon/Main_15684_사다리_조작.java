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


        finish = false;
        for (int count = 0; count <= 3; count++) {
            ladderCount = count;
            manipulateLadder(ladder, 0);
            if(finish) break;
        }

        System.out.println(finish ? ladderCount : -1);

        br.close();
    }

    private static boolean finish;
    private static void manipulateLadder(int[][] ladder, int count) {
        if(finish) return;
        if(count == ladderCount) {
            if(reachLadder(ladder)) finish = true;
            return;
        }

        for (int height = 1; height <= H; height++) {
            // 마지막에서 오른쪽으로 놓는 경우는 없으므로
            for (int col = 1; col < N; col++) {
                // 왼쪽 0, 오른쪽 0, 현재 0인 경우만 사다리 조작 가능
                if (ladder[height][col] == 0 && ladder[height][col + 1] == 0 ) {
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
                if(ladder[height][pos] == RIGHT) pos++;
                else if(ladder[height][pos] == LEFT) pos--;
            }
            if(pos != col) return false;
        }

        return true;
    }

    private static boolean reachLadder2(int[][] ladder, int start, int height, int end) {
        // 높이가 넘어가면 체크
        if (height > H)
            return start == end;


        // 사다리 체크
        if (ladder[height][start] == RIGHT) return reachLadder2(ladder, start + 1, height + 1, end);
        else if (ladder[height][start] == LEFT) return reachLadder2(ladder, start - 1, height + 1, end);
        else return reachLadder2(ladder, start, height + 1, end);
    }
}
