package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1918_후위_표기식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String exp = br.readLine();
        String rst = getSuffix(exp);
        System.out.println(rst);
        br.close();
    }

    private static String getSuffix(String exp) {
        StringBuilder suffix = new StringBuilder();

        Stack<Character> stack = new Stack<>();

        for (char op : exp.toCharArray()) {
            // 알파벳인 경우 그냥 붙여주기
            if (Character.isAlphabetic(op)) suffix.append(op);

            // (
            else if (op == '(') stack.push(op);

            // )
            else if(op == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    suffix.append(stack.pop());
                }
                stack.pop(); //( 없애기
            }
            // 우선 순위 판단해서 높은 우선순위인경우는 그냥 넣어주기
            else {
                if(!stack.isEmpty() && stack.peek() == '(') stack.push(op);
                else {
                    // 우선 순위 판단
                    while (!stack.isEmpty() && (stack.peek() != '(' && findPriority(op, stack.peek()))) {
                        suffix.append(stack.pop());
                    }
                    stack.push(op);
                }
            }
        }
        while(!stack.isEmpty()) {
            if(stack.peek() != '(')
                suffix.append(stack.pop());
        }
        return suffix.toString();
    }

    private static boolean findPriority(char op, Character compare) {
        // *
        if(op == '*') return compare == '/' || compare == '*';
        else if(op == '/') return compare == '*' || compare == '/';
        else if(op == '+') return true;
        return true;
    }
}
