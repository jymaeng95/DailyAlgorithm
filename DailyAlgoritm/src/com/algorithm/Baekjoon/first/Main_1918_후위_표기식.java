package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1918_후위_표기식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        String rst = makePostfix(expression);
        System.out.println(rst);
        br.close();
    }

    private static String makePostfix(String expression) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        for (char exp : expression.toCharArray()) {
            if (Character.isLetter(exp)) {
                postfix.append(exp);
            } else {
                if (!stack.isEmpty()) {
                    // 현재 괄호 여는 경우 스택 넣기
                    if (exp == '(') stack.push(exp);
                        // 현재 인덱스 괄호 닫는 경우 여는 괄호 까지 스택 팝해서 붙히기
                    else if (exp == ')') {
                        while (!stack.isEmpty() && stack.peek() != '(') {
                            postfix.append(stack.pop());
                        }
                        // 여는 괄호일때 버리기
                        if (stack.peek() == '(') stack.pop();
                    }
                    // 연산자면 우선순위 비교
                    else {
                        // 우선순위가 높을 때까지 팝해서 넣기 (여는 괄호 인경우 그냥 붙히기)
                        while (!stack.isEmpty() && stack.peek() != '(' && checkPriority(stack.peek(), exp)) {
                            postfix.append(stack.pop());
                        }

                        stack.push(exp);

                    }
                } else {
                    stack.push(exp);
                }
            }

        }
        // 남은 연산자 붙히기
        while (!stack.isEmpty()) postfix.append(stack.pop());

        return postfix.toString();
    }

    private static boolean checkPriority(char operation, char exp) {
        int weightOperation = 0, weightExp = 0;
        if (operation == '*' || operation == '/') weightOperation = 1;
        if (exp == '*' || exp == '/') weightExp = 1;

        return weightOperation >= weightExp;
    }
}
