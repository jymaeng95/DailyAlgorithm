package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1041 {
    private static long N;
    private static long one, two, three;

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
        long minAF = Math.min(dice[0], dice[5]);
        long minBE = Math.min(dice[1], dice[4]);
        long minCD = Math.min(dice[2], dice[3]);
        System.out.println(minAF);
        System.out.println(minBE);
        System.out.println(minCD);
        // 1,2,3면 구성
        one = Math.min(minAF, Math.min(minBE, minCD));
        two = Math.min(minAF + minBE, Math.min(minAF + minCD, minBE + minCD));
        three = minAF + minBE + minCD;

        long sum = minSum(dice);

        bw.write(String.valueOf(sum));
        br.close();
        bw.close();
    }



    private static long minSum(int[] dice) {
        long sum = 0;
        if (N == 1) {
            long max = Integer.MIN_VALUE;
            for (int i = 0; i < 6; i++) {
                max = Math.max(max,dice[i]);
                sum += dice[i];
            }
            return sum-max;
        }

        /*
         * 3면 : 4개
         * 2면 : 4(n-1) + 4*(n-2)
         * 1면 : 4(n-1)(n-2) + (n-2)(n-2)
         */
        //int * int값을 long에 저장하면 int값을 가지기 때문에 오버플로우 발생
        long oneSide = 4 * (N - 1) * (N - 2) + (N - 2) * (N - 2);
        long secondSide = 4 * (N - 1) + 4 * (N - 2);
        long thirdSide = 4;

        sum += one * oneSide + two * secondSide + three * thirdSide;
        return sum;
    }
}
