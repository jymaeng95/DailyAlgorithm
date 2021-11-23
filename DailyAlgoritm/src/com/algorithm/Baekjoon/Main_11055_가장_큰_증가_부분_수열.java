package com.algorithm.Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main_11055_가장_큰_증가_부분_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int rst = getMaxInSubSeq(N, arr);
        bw.write(String.valueOf(rst));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int getMaxInSubSeq(int n, int[] arr) {
        int[] dp = new int[n];
        int rst = 0;
        for (int i = 0; i < arr.length; i++) {
            int max = 0;
            int idx = i;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] > max) {
                    max = Math.max(max, dp[j]);
                    idx = j;
                }
            }
            dp[i] = dp[idx] + arr[i];

        }

        for (int x : dp)
            rst = Math.max(x,rst);
        return rst;
    }


}
