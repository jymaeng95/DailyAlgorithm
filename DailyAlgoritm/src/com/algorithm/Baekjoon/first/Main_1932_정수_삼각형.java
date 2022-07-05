package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1932_정수_삼각형 {
    private static int[][] arr;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                if(st.hasMoreTokens()){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        int rst = intTriangle(N);
        bw.write(String.valueOf(rst));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int intTriangle(int n) {
        dp[0][0] = arr[0][0];

        int max = arr[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if(j == 0){
                    dp[i][j]= dp[i-1][j]+arr[i][j];
                    max = Math.max(max, dp[i][j]);
                    continue;
                }
                if (j == i){
                    dp[i][j] = dp[i-1][j-1] + arr[i][j];
                    max = Math.max(max, dp[i][j]);
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j] + arr[i][j], dp[i-1][j-1] +arr[i][j]);
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}
