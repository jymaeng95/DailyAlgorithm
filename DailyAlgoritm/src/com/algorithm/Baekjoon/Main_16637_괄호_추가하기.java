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

        int rst = getMaxResult(num, op);
        System.out.println(rst);

        br.close();
    }


    private static int getMaxResult(int[] num, char[] op) {
        result = Integer.MIN_VALUE;

        makeExp(num[0], 0, num, op);
        return result;
    }

    private static int result;

    private static void makeExp(int output, int index, int[] num, char[] op) {
        // 연산자 모두 사용한 경우
        if (index >= op.length) {
            // 최대값 갱신
            result = Math.max(result, output);
            return;
        }

        // 괄호 계산 하는 경우
        // 숫자 배열이 넘어가지 않도록
        if (index + 2 < num.length) {
            // 현재 숫자 배열 인덱스보다 다음 두개의 숫자의 괄호 계산
            int rst = calc(num[index + 1], op[index + 1], num[index + 2]);
            // 현재까지의 ouput 과 다음 두개 계산 값 계산
            makeExp(calc(output, op[index], rst), index + 2, num, op);
        }
        // 괄호 계산 하지 않는 경우
        // 현재까지의 output과 다음 숫자 계산
        int rst = calc(output, op[index], num[index+1]);
        makeExp(rst, index + 1, num ,op);
    }

    private static int calc(int prev, char operation, int post) {
        if (operation == '+') return prev + post;
        if (operation == '-') return prev - post;
        return prev * post;
    }
}
