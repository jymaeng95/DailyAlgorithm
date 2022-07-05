package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3079_입국심사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] time = new int[N];
        for (int table = 0; table < N; table++) {
            time[table] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(time);
        long rst = passportControl(N, M, time);
        System.out.println(rst);

        br.close();
    }

    private static long passportControl(int n, int m, int[] time) {
        long max = (long) time[n - 1] * m;
        long min = 1;
        long minTime = max;
        while(min <= max) {
            long count = 0;
            long mid = (max + min) / 2;
            for (int table = 0; table < n; table++) {
                count += mid / time[table];
            }
            if (count >= m) {
                minTime = Math.min(minTime, mid);
                max = mid - 1;
            }
            else min = mid + 1;
        }
        return minTime;
    }
}
