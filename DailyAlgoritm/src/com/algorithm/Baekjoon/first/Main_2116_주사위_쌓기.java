package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2116_주사위_쌓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int diceCount = Integer.parseInt(br.readLine());

        int[][] dices = new int[diceCount][6];
        for (int dice = 0; dice < diceCount; dice++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int number = 0; number < 6; number++) {
                dices[dice][number] = Integer.parseInt(st.nextToken());
            }
        }

        int rst = getMaxArea(diceCount, dices);
        System.out.println(rst);

        br.close();
    }

    private static int area;
    private static int getMaxArea(int diceCount, int[][] dices) {
        area = 0;

        // 1. 첫번째 주사위는 어떤 방향으로 놓아도 상관 없기 때문에 아무 6번 해준다.
        // 2. A <-> F (0 <-> 5), B - D (1 <-> 3) C - E (2 <-> 4)
        for (int number = 0; number < 6; number++) {
            stackDice(dices[0][number],0, 0, diceCount, dices);
        }

        return area;
    }

    private static int[] tops = {5, 3, 4, 1, 2, 0};
    private static void stackDice(int bottom, int maxArea, int curDice, int diceCount, int[][] dices) {
        // 가장 큰 넓이 구하기
        if(curDice == diceCount) {
            area = Math.max(area, maxArea);
            return;
        }

        // 현재 주사위의 아래와 위를 구하기
        int top = 0;
        for (int number = 0; number < 6; number++) {
            if(dices[curDice][number] == bottom) {
                top = dices[curDice][tops[number]];
            }
        }


        // 4 면적 중 가장 큰 면적 구하기
        int curMaxArea = 0;
        for (int number = 0; number < 6; number++) {
            if (dices[curDice][number] != bottom && dices[curDice][number] != top) {
                curMaxArea = Math.max(curMaxArea, dices[curDice][number]);
            }
        }

        // 현재까지의 최대 면적에 4 면적중 가장 큰 면적 더해서 넣어주기
        stackDice(top, maxArea + curMaxArea, curDice + 1, diceCount, dices);
    }
}
