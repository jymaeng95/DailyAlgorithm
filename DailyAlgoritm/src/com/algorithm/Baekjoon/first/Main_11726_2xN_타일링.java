package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11726_2xN_타일링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int rst = solution(N);
        System.out.println(rst);
        br.close();
    }

    private static int solution(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int count = 3; count <= n; count++) {
            dp[count] = (dp[count-1] + dp[count-2]) % 10007;
        }
        return dp[n];
    }
}
