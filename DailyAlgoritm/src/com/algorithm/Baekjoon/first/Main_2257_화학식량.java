package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_2257_화학식량 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        int rst = chemicalAmount(exp);
        System.out.println(rst);
        br.close();
    }

    private static int chemicalAmount(String exp) {
        Stack<String> stack = new Stack<>();
        int sum = 0;

        for (char chemical : exp.toCharArray()) {
            // 화학식 변경
            if (chemical == 'H') stack.push("1");
            else if (chemical == 'C') stack.push("12");
            else if (chemical == 'O') stack.push("16");
            else if (chemical == '(') stack.push("(");
            // 숫자인 경우 가장 위의 스택에 숫자 곱한 후 넣어주기
            else if (!stack.isEmpty() && Character.isDigit(chemical)) {
                int combination = Integer.parseInt(stack.pop()) * (chemical - '0');
                stack.push(String.valueOf(combination));
            }
            // 괄호가 닫히는 경우 괄호 열린 곳까지 더해서 열린 괄호 지우고 더한 값 넣어주기
            else if (chemical == ')') {
                int combination = 0;
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    combination += Integer.parseInt(stack.pop());
                }
                stack.pop(); // (없애기
                stack.push(String.valueOf(combination));
            }
        }

        while (!stack.isEmpty()) {
            sum += Integer.parseInt(stack.pop());
        }

        return sum;
    }
}
