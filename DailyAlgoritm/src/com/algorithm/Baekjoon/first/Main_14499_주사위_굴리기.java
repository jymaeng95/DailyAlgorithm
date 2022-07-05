package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499_주사위_굴리기 {
    private static int N,M,K;
    private static int[] command;
    private static int[][] map;
    private static final int[] DX = {0,0,-1,1};
    private static final int[] DY = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        command = new int[K];
        for(int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int loop = 0; loop < K; loop++) {
            command[loop] = Integer.parseInt(st.nextToken());
        }

        moveDice(x,y);

        br.close();
    }

    private static void moveDice(int x, int y) {
        int[] dice = new int[6];

        // 처음 시작지점 값 주사위 바닥에 찍고 바닥 0으로 바꾸기 (주사위가 어차피 0으로 시작이므로 바닥이 0이던 아니던 상관없음)
        dice[2] = map[x][y];
        map[x][y] = 0 ;

        for(int cmd : command) {
            if(checkBound(cmd,x,y)) {
                x += DX[cmd-1];
                y += DY[cmd-1];
                move(cmd, x, y, dice);
                System.out.println(dice[5]);
            }
        }

    }

    private static void move(int cmd, int x, int y, int[] dice) {
        /*
         * map[row][col] == 0 -> map[row][col] = dice[2];
         * map[row][col] != 0 -> dice[2] = map[row][col] && map[row][col] = 0;
         *
         * 1. 동 : 1->5, 5->3, 3->2, 2->1
         * 2. 서 : 1->2, 2->3, 3->5, 5->1
         * 3. 북 : 0->2, 2->4, 4->5, 5->0
         * 4. 남 : 0->5, 5->4, 4->2, 2->0
         */
        if(cmd == 1) {
            int temp = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = temp;
        }
        else if(cmd == 2) {
            int temp = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[3];
            dice[3] = temp;
        }
        else if(cmd == 3) {
            int temp = dice[2];
            dice[2] = dice[0];
            dice[0] = dice[5];
            dice[5] = dice[4];
            dice[4] = temp;
        }
        else {
            int temp = dice[5];
            dice[5] = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[4];
            dice[4] = temp;
        }

        if (map[x][y] != 0) {
            dice[2] = map[x][y];
            map[x][y] = 0;
        }
        else map[x][y] = dice[2];
    }

    private static boolean checkBound(int cmd, int x, int y) {
        if(cmd == 1) return y+1 >= 0 && y+1 < M;
        if(cmd == 2) return y-1 >= 0 && y-1 < M;
        if(cmd == 3) return x-1 >= 0 && x-1 < N;
        return x+1 >= 0 && x+1 < N;
    }
}
