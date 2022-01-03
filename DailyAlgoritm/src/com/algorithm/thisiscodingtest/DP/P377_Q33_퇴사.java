package com.algorithm.thisiscodingtest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P377_Q33_퇴사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N];
        int[] pay = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }

        int rst = getMaxPay(N, time,pay);
        System.out.println(rst);
        br.close();
    }

    private static int getMaxPay(int n, int[] time, int[] pay) {
        int[] dp = new int[n+1];
        int max =0;
        for(int i=0;i<n;i++) {
            if(i+time[i] <= n) {
                dp[i+time[i]] = Math.max(dp[i+time[i]], dp[i] + pay[i]);
            }
            dp[i+1] = Math.max(dp[i+1], dp[i]);
        }
        return dp[n];
    }
}

