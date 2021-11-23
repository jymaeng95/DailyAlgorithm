package com.algorithm.Baekjoon;

import java.io.*;

public class Main_2156_포도주_시식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] wine = new int[N];
        for (int i = 0; i < N; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        int rst = getMaxWine(N, wine);
        bw.write(String.valueOf(rst));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getMaxWine(int n, int[] wine) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = wine[0];
        dp[2] = wine[0] + wine[1];

        for(int i = 3; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-2] + wine[i-1], Math.max(wine[i-1] + wine[i-2], dp[i-3] + wine[i-2] + wine[i-1]));
        }

        int max = 0;
        for(int x : dp) {
            max = Math.max(max, x);
        }

        return max;
    }
}
