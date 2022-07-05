package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1034_램프 {
    private static int N, M, K;

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

        int count = getSwitchClick(lamp);

        System.out.println(count);

        br.close();
    }

    private static int getSwitchClick(int[][] lamp) {

        boolean[] check = new boolean[N];
        for (int i = 0; i < N; i++) {
            int cnt =0 ;
            for (int j = 0; j < M; j++) {
                if (lamp[i][j] == 0) {
                    cnt++;
                }
            }
            // 0의 개수 짝홀 판단 == K의 짝홀 판단, 0인 컬럼 개수가 클릭 회수보다 적으면 바꿀 수 있음
            if ((cnt % 2 == K % 2) && cnt <= K) {
                check[i] = true;
            }
        }

        // true 인 행 하고 동일한 행 count ++;
        int count = 0;
        for (int i = 0; i < N; i++) {
            int rowCnt = 0;
            if (check[i]) {
                for (int j = 0; j < N; j++) {
                    boolean flag = true;
                    for (int k = 0; k < M; k++) {
                        /*
                         * ex ) check 행이 바꿀 수 있는 경우
                         * check 행 : 1010 3행 :1010 인경우 동일하기 때문에 행 개수 증가
                         */
                        if (lamp[i][k] != lamp[j][k]) {
                            flag = false;
                        }
                    }
                    if (flag) rowCnt++;
                }
            }
            count = Math.max(count, rowCnt);
        }

        return count;
    }
}
