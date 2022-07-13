package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11053_가장_긴_증가하는_부분_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sequence = new int[N + 1];
        for (int index = 1; index <= N; index++) {
            sequence[index] = Integer.parseInt(st.nextToken());
        }

        int rst = getSubSequence(N, sequence);
        System.out.println(rst);

        br.close();
    }

    private static int getSubSequence(int n, int[] sequence) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);

        for (int index = 2; index <= n; index++) {
            for (int start = 1; start < index; start++) {
                if(sequence[index] > sequence[start]) dp[index] = Math.max(dp[index], dp[start] + 1);
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
