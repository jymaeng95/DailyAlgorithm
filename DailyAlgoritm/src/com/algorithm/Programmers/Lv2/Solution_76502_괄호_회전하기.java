package com.algorithm.Programmers.Lv2;

import java.util.Stack;

public class Solution_76502_괄호_회전하기 {
    public static void main(String[] args) {
        String s = "[](){}";
//        String s = "}]()[{";
//        String s = "[)(]";
//        String s ="}}}";
        int solution = solution(s);
        System.out.println("solution = " + solution);
    }

    public static int solution(String s) {
        int answer = 0;
        StringBuilder sb = new StringBuilder(s);
        int loop = 0;
        while(loop < sb.length()) {
            Stack<Character> stack = new Stack<>();
            for(char c : sb.toString().toCharArray()) {
                if(!stack.isEmpty()) {
                    if((stack.peek() == '{' && c == '}') || (stack.peek() == '[' && c == ']') || (stack.peek() == '(' &&  c == ')')) {
                        stack.pop();
                    }
                    else stack.push(c);
                }
                else {
                    stack.push(c);
                }
            }
            if(stack.size() < 1) answer++;
            String left = sb.substring(0,1);
            sb.deleteCharAt(0);
            sb.append(left);

            loop++;
        }

        return answer;
    }
}
