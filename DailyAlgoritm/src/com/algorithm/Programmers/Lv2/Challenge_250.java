package com.algorithm.Programmers.Lv2;

import java.util.Stack;

public class Challenge_250 {
    public static void main(String[] args) {
        String s = "baabaa";
        int solution = solution(s);
        System.out.println("solution = " + solution);
    }

    private static int solution(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            }
            else  {
                stack.push(c);
            }
        }

        return stack.size() > 0 ? 0 : 1;
    }
}
