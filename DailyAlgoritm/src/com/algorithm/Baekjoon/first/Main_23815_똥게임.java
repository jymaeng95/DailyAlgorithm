package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_23815_똥게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[][] op = new String[N + 1][2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            op[i][0] = st.nextToken();
            op[i][1] = st.nextToken();
        }

        int rst = playDDongGame(N, op);
        if (rst < 0) System.out.println("ddong game");
        else System.out.println(rst);
        br.close();
    }

    private static int playDDongGame(int n, String[][] op) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int temp = 1;

        for (int i = 1; i <= n; i++) {
            String op1 = op[i][0];
            String op2 = op[i][1];
            if (temp > 0)
                temp = getTemp(op1, op2, i, dp, temp);
            if (dp[i - 1] > 0)
                dp[i] = getScore(op1, op2, i, dp);
            if (dp[i] <= 0 && temp <= 0) return -1;
        }
        return Math.max(dp[n], temp);
    }

    private static int getTemp(String op1, String op2, int idx, int[] dp, int temp) {
        char opr1 = op1.charAt(0);
        char opr2 = op2.charAt(0);
        int rst1 = 0, rst2 = 0;

        rst1 = getRst2(op1, temp, opr1, rst1);
        rst2 = getRst2(op2, temp, opr2, rst2);

        return Math.max(dp[idx - 1], Math.max(rst1, rst2));
    }

    private static int getRst2(String op1, int temp, char opr1, int rst1) {
        if (opr1 == '-') rst1 = temp - Integer.parseInt(String.valueOf(op1.charAt(1)));
        else if (opr1 == '+') rst1 = temp + Integer.parseInt(String.valueOf(op1.charAt(1)));
        else if (opr1 == '/') rst1 = temp / Integer.parseInt(String.valueOf(op1.charAt(1)));
        else if (opr1 == '*') rst1 = temp * Integer.parseInt(String.valueOf(op1.charAt(1)));
        return rst1;
    }

    private static int getScore(String op1, String op2, int idx, int[] dp) {
        char opr1 = op1.charAt(0);
        char opr2 = op2.charAt(0);
        int rst1 = 0, rst2 = 0;

        rst1 = getRst(op1, idx, dp, opr1, rst1);
        rst2 = getRst(op2, idx, dp, opr2, rst2);

        return Math.max(rst1, rst2);
    }

    private static int getRst(String op1, int idx, int[] dp, char opr1, int rst1) {
        if (opr1 == '-') rst1 = dp[idx - 1] - Integer.parseInt(String.valueOf(op1.charAt(1)));
        else if (opr1 == '+') rst1 = dp[idx - 1] + Integer.parseInt(String.valueOf(op1.charAt(1)));
        else if (opr1 == '/') rst1 = dp[idx - 1] / Integer.parseInt(String.valueOf(op1.charAt(1)));
        else if (opr1 == '*') rst1 = dp[idx - 1] * Integer.parseInt(String.valueOf(op1.charAt(1)));
        return rst1;
    }
}

