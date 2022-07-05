package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9012_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int loop = 0; loop < N; loop++) {
            String ps = br.readLine();
            if(checkVPS(ps)) System.out.println("YES");
            else System.out.println("NO");
        }
        br.close();
    }

    private static boolean checkVPS(String ps) {
        Stack<Character> stack = new Stack<>();

        for (char parenthesis : ps.toCharArray()) {
            if(parenthesis == ')' && !stack.isEmpty() && stack.peek() == '(') stack.pop();
            else stack.push(parenthesis);
        }

        return stack.isEmpty();
    }
}
