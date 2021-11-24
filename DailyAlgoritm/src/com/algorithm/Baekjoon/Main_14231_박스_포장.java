package com.algorithm.Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14231_박스_포장 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] box = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }
        int rst = getCountBoxes(n, box);

        bw.write(String.valueOf(rst));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int getCountBoxes(int n, int[] box) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        dp[0] = 1;
        for (int i = 1; i < box.length; i++) {
            for (int j = 0; j < i; j++) {
                if (box[j] < box[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int max = 0;
        for (int x : dp)
            max = Math.max(max, x);

        return max;
    }
}
