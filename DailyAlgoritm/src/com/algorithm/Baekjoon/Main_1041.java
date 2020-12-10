package com.algorithm.Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1041 {
    private static int N;
    private static int one, two, three;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[] dice = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        //마주보는 면의 인덱스의 합 5이므로 마주보는 면 중 최소값을 고르기
        int minAF = Math.min(dice[0], dice[5]);
        int minBE = Math.min(dice[1], dice[4]);
        int minCD = Math.min(dice[2], dice[3]);

        // 1,2,3면 구성
        one = Math.min(minAF, Math.min(minBE, minCD));
        two = Math.min(minAF + minBE, Math.min(minAF + minCD, minBE + minCD));
        three = minAF + minBE + minCD;

        Arrays.sort(dice);
        long sum = minSum(dice);

        bw.write(String.valueOf(sum));
        br.close();
        bw.close();
    }

    private static long minSum(int[] dice) {
        long sum = 0L;
        if (N == 1) {
            for (int i = 0; i < 5; i++)
                sum += dice[i];
            return sum;
        }

        /*
         * 3면 : 4개
         * 2면 : 4(n-1) + 4*(n-2)
         * 1면 : 4(n-1)(n-2) + (n-2)(n-2)
         */
        long oneSide = (4 * (N - 1) * (N - 2)) + ((N - 2) * (N - 2));
        long secondSide = 4 * (N - 1) + 4 * (N - 2);
        long thirdSide = 4;

        sum += one * oneSide + two * secondSide + three * thirdSide;
        return sum;
    }
}
