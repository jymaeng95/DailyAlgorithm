package com.algorithm.LeetCode.greedy;

import java.util.Stack;

public class Solution_921_Minimum_Add_To_Make_Parenthesis_Valid {
    public static void main(String[] args) {
        String s = "()))((";
        int rst = minAddToMakeValid(s);
        System.out.println("rst = " + rst);
    }

    private static int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        return stack.size();
    }
}
