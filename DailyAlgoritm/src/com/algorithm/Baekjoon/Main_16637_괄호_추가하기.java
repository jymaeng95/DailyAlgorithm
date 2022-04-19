package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16637_괄호_추가하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N / 2 + 1];
        char[] op = new char[N / 2];

        char[] exp = br.readLine().toCharArray();
        for (int index = 0; index < N; index++) {
            if (index % 2 == 0) num[index / 2] = Integer.parseInt(String.valueOf(exp[index]));
            else op[index / 2] = exp[index];
        }

        int rst = getMaxResult(N, num, op);
        System.out.println(rst);

        br.close();
    }


    private static int getMaxResult(int n, int[] num, char[] op) {
        result = Integer.MIN_VALUE;

        makeExp(0, 0, num, op);
        return result;
    }

    private static int result;

    private static void makeExp(int output, int index, int[] num, char[] op) {
        // 연산자 모두 사용한 경우
        if (index >= op.length) {
            result = Math.max(result, output);
            return;
        }

        // 괄호 계산 하는 경우
        int rst = calc(num[index], op[index], num[index + 1]);
        makeExp(rst, index + 2, num, op);

        // 괄호 계산 하지 않는 경우

    }

    private static int calc(int prev, char operation, int post) {
        if (operation == '+') return prev + post;
        if (operation == '-') return prev - post;
        if (operation == '*') return prev * post;
    }
}
