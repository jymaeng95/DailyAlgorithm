package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int[][] gear = new int[4][8];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), "");
            for (int j = 0; j < 8; j++) {
                gear[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rotate = Integer.parseInt(br.readLine());
        int score = getScore(gear, rotate);

        System.out.println(score);
        br.close();
    }

    private static int getScore(int[][] gear, int rotate) throws IOException {
        int leftTwo = 6, leftThree = 6, leftFour = 6;
        int rightOne = 2, rightTwo = 2, rightThree = 2;
        int[] left = new int[3];
        int[] right = new int[3];
        Arrays.fill(left, 6);
        Arrays.fill(right, 2);
        for (int count = 0; count < rotate; count++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNo = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            boolean[] rotation = new boolean[4];

            // 회전 전에 맞 닿은 극 확인
            checkRotation(left, right,gear, rotation);
            rotation[gearNo -1] = true;

            checkLeftRotation(gearNo, rotation, gear,left,right,direction);
            //왼쪽 체크
            for(int i=gearNo-1; i>0; i--) {
                if(rotation[i-1]) {
                    checkLeftRotation(i);
                    direction
                }
                else break;
            }

            // 오른쪽 체크
            for(int i=gearNo+1; i<=4; i++) {
                if(rotation[i-1])
                    checkRightRotation(i-1);
                else break;
            }


//            checkRotation(left, right, gearNo, rotation);
        }
    }

    private static void checkLeftRotation(int gearNo, boolean[] rotation, int[][] gear, int[] left, int[] right, int direction) {
        for(int i=gearNo-1; i>0;i--) {
            if(rotation[i-1]) {
                if(direction == 1)
            }
        }
    }

    private static void checkRotation(int[] left, int[] right, int[][] gear,boolean[] rotation) {
        for(int i=0;i<3;i++) {
            if(gear[left[i]] == gear[right[i]]) {
                rotation[i] = false;
                rotation[i+1] = false;
            }
            else {
                rotation[i] = true;
                rotation[i+1] = true;
            }
        }
    }
    /*private static void checkRotation(int[] left, int[] right, int gearNo, boolean[] rotation) {
        if (gearNo == 1) {
            // 1번 오른쪽, 2번 왼쪽 /  2번 오른쪽 3번 왼쪽 / 3번 오른쪽 4번 왼쪽

        } else if (gearNo == 2) {
            // 2번 왼쪽 , 1번 오른족 / 2번 오른쪽 3번 왼쪽 / 3번 오른쪽 4번 왼쪽
        } else if (gearNo == 3) {
            // 3번 오른쪽, 4번 왼쪽, 3번 왼쪽, 2번 오른쪽, 2번 왼쪽 , 1번 오른쪽
        } else {
            // 4번 왼쪽, 3번 오른쪽, 3번 왼쪽 2번 오른쪽, 2번 오니쪽, 1번 오른쪽
        }
    }*/
}
