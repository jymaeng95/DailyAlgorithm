package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16456_하와와_대학생쨩_하와이로_가는_거시와요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long rst = travelHawaii(N);
        System.out.println(rst);

        br.close();
    }

    private static long travelHawaii(int n) {
        final long DIVIDE = 1000000009;
        // 섬이 1개이거나 2개인 경우 경우의 수는 1개
        if (n < 3) return 1;

        // 1개의 섬씩 이동하거나 2개의 섬씩 이동하는 경우
        long[][] dp = new long[2][n + 1];
        dp[0][1] = dp[0][2] = 1;

        for (int island = 3; island <= n; island++) {
            // 현재 섬에 이전 섬에서 온 경우 : 이전 섬까지 도착할 수 있는 모든 경우의 수
            // = 섬을 1개씩 이동하거나 2개씩 이동해서 이전섬에 도착한 경우의 수
            dp[0][island] = (dp[0][island - 1] + dp[1][island - 1]) % DIVIDE;

            // 현재 섬에 2개전 섬에서 온 경우 : 2개 전 섬까지 도착할 수 있는 경우의 수 중 섬을 1개씩 이동해 도착한 경우
            // = 2개전 섬을 2개씩 이동해 오는 경우는 이전 섬의 1칸씩 오는 경우를 이미 포함하고 있기 때문
            dp[1][island] = dp[0][island - 2] % DIVIDE;
        }

        return (dp[0][n] + dp[1][n]) % DIVIDE;
    }
}
