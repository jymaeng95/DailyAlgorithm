package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

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

    private static Stack<String> exp;

    private static int getMaxResult(int n, int[] num, char[] op) {
        result = Integer.MIN_VALUE;
        exp = new Stack<>();

        exp.add(String.valueOf(num[0]));
        makeExp(0, num, op);
        return result;
    }

    private static int result;

    private static void makeExp(int index, int[] num, char[] op) {
        if (index == op.length) {
            if (exp.size() == 1) result = Math.max(result, Integer.parseInt(exp.peek()));
            else calculate();

            return;
        }

        // stack top 과 index op, index+1 num 계산 후 stack에 넣기
        if (op[index] == '+') {
            int top = Integer.parseInt(exp.pop());
            exp.push(String.valueOf(top + num[index + 1]));
            makeExp(index + 1, num, op);
            // 백트래킹으로 계산 반대로
            exp.push(String.valueOf(Integer.parseInt(exp.pop()) - num[index + 1]));
        } else if (op[index] == '-') {
            int top = Integer.parseInt(exp.pop());
            exp.push(String.valueOf(top - num[index + 1]));
            makeExp(index + 1, num, op);
            // 백트래킹으로 계산 반대로
            exp.push(String.valueOf(Integer.parseInt(exp.pop()) + num[index + 1]));
        } else {
            int top = Integer.parseInt(exp.pop());
            exp.push(String.valueOf(top * num[index + 1]));
            makeExp(index + 1, num, op);
            // 백트래킹으로 계산 반대로
            if(num[index+1] != 0)
                exp.push(String.valueOf(Integer.parseInt(exp.pop()) / num[index + 1]));
        }

        // 수식 그대로 넣기
        exp.push(String.valueOf(op[index]));
        exp.push(String.valueOf(num[index + 1]));
        makeExp(index + 1, num, op);
        exp.pop();
        exp.pop();
    }

    private static void calculate() {
        int rst = Integer.parseInt(exp.get(0));
        for (int index = 1; index < exp.size(); index += 2) {
            if (exp.get(index).equals("+")) rst += Integer.parseInt(exp.get(index + 1));
            if (exp.get(index).equals("-")) rst -= Integer.parseInt(exp.get(index + 1));
            if (exp.get(index).equals("*")) rst *= Integer.parseInt(exp.get(index + 1));
        }

        result = Math.max(result, rst);
    }
}
