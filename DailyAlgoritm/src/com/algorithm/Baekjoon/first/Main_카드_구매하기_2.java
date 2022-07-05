package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_카드_구매하기_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] card = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        int rst = getMinPrice(N, card);
        bw.write(String.valueOf(rst));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getMinPrice(int n, int[] card) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = card[0];
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j]+dp[j]);
            }
            dp[i] = Math.min(card[i-1], dp[i]);
        }

        return dp[n];
    }


}
