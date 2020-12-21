package com.algorithm.Programmers.Lv2;

import java.util.Stack;
//짝지어 제거하기
public class Solution_12973 {
    public static void main(String[] args) {
        String s ="bccbcc";
        int solution = solution(s);
        System.out.println(solution);
    }

    public static int solution(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(!stack.isEmpty() && stack.peek()==s.charAt(i)) {
                stack.pop();
                continue;
            }
            stack.push(s.charAt(i));
        }

        return stack.size() > 0 ? 0 : 1;
    }
}
