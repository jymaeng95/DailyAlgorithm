package com.algorithm.Programmers.Lv2;

import java.util.Stack;

public class Solution_12909 {
    public static void main(String[] args) {
        String s = "(()(";
        System.out.println(solution(s));
    }
    private static boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(!stack.isEmpty() && c==')') {
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        return stack.isEmpty();
    }
}
