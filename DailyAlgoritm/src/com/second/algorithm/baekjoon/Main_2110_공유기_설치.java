package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110_공유기_설치 {
    private static int N, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[] houses = new int[N];
        for (int index = 0; index < N; index++) {
            houses[index] = Integer.parseInt(br.readLine());
        }

        long rst = installMachine(houses);
        System.out.println(rst);

        br.close();
    }

    private static long installMachine(int[] houses) {
        Arrays.sort(houses);
        long min = 1;
        long max = houses[N - 1];
        long minLength = min;
        while (min <= max) {
            long mid = (min + max) / 2;

            int count = 1;
            int install = 0;
            for (int index = 1; index < N; index++) {
                if (houses[install] + mid <= houses[index]) {
                    count ++;
                    install = index;
                }
            }

            // 개수가 적은 경우 길이를 줄여야한다.
            if (count < C) {
                max = mid - 1;
            } else {
                minLength = Math.max(mid, minLength);
                min = mid + 1;
            }
        }

        return minLength;
    }
}

