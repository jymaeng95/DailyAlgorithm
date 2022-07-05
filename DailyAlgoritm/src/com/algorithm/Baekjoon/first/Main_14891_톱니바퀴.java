package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int[][] gear = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                gear[i][j] = Integer.parseInt(str[j]);
            }
        }

        int rotate = Integer.parseInt(br.readLine());
        int score = getScore(gear, rotate);

        System.out.println(score);
        br.close();
    }

    private static int getScore(int[][] gear, int rotate) throws IOException {
        for(int rt = 0; rt < rotate; rt++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNo = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            //왼쪽 체크
            checkLeft(gear, gearNo-1, direction);
            checkRight(gear, gearNo-1, direction);
            rotation(gear, gearNo-1, direction);
        }

        int score = 0;
        for(int i=0;i<4;i++) {
            if(gear[i][0] != 0) score += gear[i][0] * Math.pow(2,i);
        }
        return score;
    }

    private static void checkRight(int[][] gear, int gearNo, int direction) {
        if(gearNo == 3)
            return ;

        // 돌아가는 바퀴와 오른쪽 바퀴의 극 확인 후 다른 경우 회전
        if(gear[gearNo][2] != gear[gearNo+1][6]) {
            checkRight(gear, gearNo+1, -direction);
            rotation(gear, gearNo+1, -direction);
        }
    }

    private static void checkLeft(int[][] gear, int gearNo, int direction) {
        // 돌아가는 톱니가 0 인경우 return
        if(gearNo == 0)
            return ;

        // 돌아가는 바퀴와 왼쪽 바퀴의 극 확인 후 다른 경우 회전
        if(gear[gearNo][6] != gear[gearNo-1][2]) {
            checkLeft(gear, gearNo-1, -direction);
            rotation(gear, gearNo-1, -direction);
        }
    }

    private static void rotation(int[][] gear, int gearNo, int direction) {
        // 1인 경우 시계방향 회전
        if(direction == 1) {
            int temp = gear[gearNo][7];
            for(int i=6;i>=0;i--) {
                gear[gearNo][i+1] = gear[gearNo][i];
            }
            gear[gearNo][0] = temp;
        }
        // -1인 경우 반시계 방향 회전
        else {
            int temp = gear[gearNo][0];
            for(int i=0;i<7;i++) {
                gear[gearNo][i] = gear[gearNo][i+1];
            }
            gear[gearNo][7] = temp;
        }
    }

}
