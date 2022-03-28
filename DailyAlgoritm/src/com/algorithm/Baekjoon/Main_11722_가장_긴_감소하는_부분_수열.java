package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11722_가장_긴_감소하는_부분_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            sequence[index] = Integer.parseInt(st.nextToken());
        }

        int rst = solution(N, sequence);
        System.out.println(rst);

        br.close();
    }

    private static int solution(int n, int[] sequence) {
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int rst = 1;

        for(int target = 0; target < n; target++) {
            for(int index = target+1; index < n; index++) {
                if(sequence[target] > sequence[index]) dp[index] = Math.max(dp[index], dp[target] + 1);
            }
            rst = Math.max(rst, dp[target]);
        }

        return rst;
    }
}
