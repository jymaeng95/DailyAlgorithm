package com.algorithm.Baekjoon;

import java.io.*;
import java.util.Arrays;

public class Main_4097_수익 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0){
                break;
            }
            int[] revenue = new int[N];

            for(int i =0;i<N;i++){
                revenue[i] = Integer.parseInt(br.readLine());
            }
            int rst = getMaxRevenue(N, revenue);
            bw.write(String.valueOf(rst));
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static int  getMaxRevenue(int n, int[] revenue) {
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = revenue[0];
        int max = Integer.MIN_VALUE;
        for(int i =1; i< dp.length; i++){
            dp[i] = Math.max(dp[i-1]+revenue[i], revenue[i]);
            max = Math.max(dp[i], max);
        }

        return max;
    }
}
