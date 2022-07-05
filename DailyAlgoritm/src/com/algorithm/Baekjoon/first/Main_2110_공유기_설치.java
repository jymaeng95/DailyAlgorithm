package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110_공유기_설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] location = new int[N];
        for (int house = 0; house < N; house++) {
            location[house] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(location);


        long rst = ipTimeLength(N, M, location);
        System.out.println(rst);
        br.close();
    }

    private static long ipTimeLength(int n, int m, int[] location) {
        long leftLength = 1;
        long rightLength = location[n - 1];
        long minLength = 1;

        while (leftLength <= rightLength) {
            long settingLength = (rightLength + leftLength) / 2;

            // 인접 공유기 거리의 최대가 settingLength일 때 공유기 몇개가 설치되는 지
            long firstHouse = location[0];
            // 가장 왼쪽 집은 설치했다고 가정
            int setting = 1;
            for (int house = 1; house < n; house++) {
                // 집간의 인접 거리가 settingLength보다 크거나 같은 경우 최대 인접거리가 settingLength인 경우에 설치가 가능한 것
                long length = location[house] - firstHouse;
                if (length >= settingLength) {
                    setting++;
                    firstHouse = location[house];
                }
            }

            // 현재 세팅한 공유기보다 최대 인접거리가 클 수 있음 (공유기 줄이기)
            if(setting >= m) {
                minLength = Math.max(minLength, settingLength);
                leftLength = settingLength + 1;
            }
            else
                rightLength = settingLength - 1;
        }

        return minLength;
    }
}
