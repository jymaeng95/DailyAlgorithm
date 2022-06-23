package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2502_떡_먹는_호랑이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] rsts = giveRiceCake(D, K);
        for (int rst: rsts) {
            System.out.println(rst);
        }

        br.close();
    }

    private static int[] giveRiceCake(int d, int k) {
        // 1일 a개 , 2일 b개, 3일 a + b개, 4일 a + 2b개 ... -> 이걸 dpA, dpB로 개수 세워 점화식 세움
        int[] dpA = new int[d + 1];
        int[] dpB = new int[d + 1];

        dpA[1] = 1;
        dpB[2] = 1;

        // 점화식 구하기
        for (int day = 3; day <= d; day++) {
            dpA[day] = dpA[day - 1] + dpA[day - 2];
            dpB[day] = dpB[day - 1] + dpB[day - 2];
        }

        int firstRiceCake = 1;
        int secondRiceCake = 0;
        // 첫날 준 떡과 둘째 날 준 떡 구하기
        while (true) {
            // k - (첫번째 날 준 떡 * 현재일차에 첫째날 떡을 몇번준 건지 계산) = 남은 떡
            int remain = k - (firstRiceCake * dpA[d]);

            // 남은 떡이 둘째 날 준 떡을 준 횟수로 나눠떨어지면 둘째날 준 떡의 개수를 구할 수 있다.
            if (remain % dpB[d] == 0) {
                secondRiceCake = remain / dpB[d];
                break;
            }
            firstRiceCake++;
        }

        return new int[]{firstRiceCake, secondRiceCake};
    }
}

