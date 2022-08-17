package com.second.algorithm.programmers;

import java.util.Arrays;

public class Solution_43438_입국심사 {
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};

        long rst = solution(n, times);
        System.out.println("rst = " + rst);
    }

    private static long solution(int n, int[] times) {
        Arrays.sort(times);
        long minTime = times[0];
        long maxTime = (long) times[times.length - 1] * n;

        long rst = maxTime;
        while(minTime < maxTime) {
            long midTime = (minTime + maxTime) / 2;

            // midTime까지 심사할 수 있는 인원수
            long count = 0;
            for (int time : times) {
                count += midTime / time;
            }

            // 인원수가 n명이거나 그보다 많은 경우 시간 줄여서 확인
            if(count >= n) {
                rst = Math.min(rst, midTime);
                maxTime = midTime;
            } else {
                minTime = midTime + 1;
            }
        }
        return rst;
    }
}
