package com.algorithm.LeetCode.simulation;

import java.util.Stack;

public class Solution_844_Backspace_String_Compare {
    public static void main(String[] args) {
        String s = "a#c", t = "d#c";
        boolean ans = backspaceCompare(s, t);
        System.out.println(ans);
    }

    private static boolean backspaceCompare(String s, String t) {
        String s1 = afterBackspace(s);
        String s2 = afterBackspace(t);

        return s1.equals(s2);
    }

    private static String afterBackspace(String t) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(char c : t.toCharArray()) {
            if(!stack.isEmpty() && c == '#'){
                stack.pop();
            }
            if(c != '#')
                stack.push(c);
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
