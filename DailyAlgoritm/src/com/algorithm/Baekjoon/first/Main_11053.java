package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.StringTokenizer;

public class Main_11053 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] seq = new int[len];

        for(int i=0;i<seq.length;i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int count = LIS(seq);
        bw.write(String.valueOf(count));
        br.close();
        bw.close();
    }

    private static int LIS(int[] seq) {
        int max = 0;
        int[] dp = new int[seq.length];
        dp[0] = 1;
        for(int i=1;i<seq.length;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(seq[i] > seq[j] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;
            }
        }
        for(int i=0;i<dp.length;i++){

            max = Math.max(max,dp[i]);
        }
        return max;
    }

}
