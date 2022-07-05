package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1034_램프_시간초과 {
    private static int N, M, K, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] lamp = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                lamp[i][j] = Integer.parseInt(str[j]);
            }
        }
        K = Integer.parseInt(br.readLine());
        count = 0;
        getTurnOnLamp(lamp);

        System.out.println(count);

        br.close();
    }

    private static void getTurnOnLamp(int[][] lamp) {
        /*
         * 1. K의 짝,홀 판단
         * 2. M에 대한 boolean 조합
         * 3. level == 열인 경우 2번의 조합으로 true 개수로 짝홀 판단 후 1번과 동일 한지 확인
         * 4. 1번과 동일 시 true 인 경우 컬럼 뒤집어주기
         * 5. 전체 켜진 행 개수 판단
         */
        boolean isOdd = K % 2 == 0; // 1. K의 짝, 홀 판단
        boolean[] visited = new boolean[M];

        switchLamp(0, 0, visited, isOdd, lamp);

    }

    private static void switchLamp(int start, int level, boolean[] visited, boolean isOdd, int[][] lamp) {
        if (level == M) {
            // 3. level == 열 인 경우 2번의 조합 true 개수로 짝홀 판단
            int check = 0;
            for (boolean b : visited) {
                if (b) check++;
            }
            // 짝홀 판단 후 1번과 동일 시 실제 스위치 클릭
            if ((check % 2 == 0) == isOdd) {
                doSwitchClick(visited, lamp);
            }
            return;
        }
        visited[start] = true;
        switchLamp(start + 1, level + 1, visited, isOdd, lamp);
        visited[start] = false;
        switchLamp(start + 1, level + 1, visited, isOdd, lamp);
    }

    private static void doSwitchClick(boolean[] visited, int[][] lamp) {
        // 4. true 인 경우 컬럼 뒤집기
        for (int i = 0; i < M; i++) {
            if (visited[i]) {
                for (int j = 0; j < N; j++) {
                    if (lamp[j][i] == 0) lamp[j][i] = 1;
                    else lamp[j][i] = 0;
                }
            }
        }

        // 5. 전체 켜진 행 개수 판단
        int rowCnt = 0;
        for (int i = 0; i < N; i++) {
            boolean check = true;
            for (int j = 0; j < M; j++) {
                if (lamp[i][j] != 1) {
                    check = false;
                    break;
                }
            }
            if (check) rowCnt++;
        }

        // 6. 램프 배열 다른 조합 위해 돌려 놓기
        for (int i = 0; i < M; i++) {
            if (visited[i]) {
                for (int j = 0; j < N; j++) {
                    if (lamp[j][i] == 0) lamp[j][i] = 1;
                    else lamp[j][i] = 0;
                }
            }
        }

        // 7. 램프 개수 저장
        count = Math.max(count, rowCnt);
    }
}
