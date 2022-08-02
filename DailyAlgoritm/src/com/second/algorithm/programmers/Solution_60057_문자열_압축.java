package com.second.algorithm.programmers;

import java.util.Stack;

public class Solution_60057_문자열_압축 {
    public static void main(String[] args) {
//        String s = "aabbaccc";
//        String s = "ababcdcdababcdcd";
//        String s = "abcabcdede";
//        String s = "abcabcabcabcdededededede";
        String s = "xababcdcdababcdcd";
        int rst = solution(s);
        System.out.println("rst = " + rst);
    }

    private static int solution(String s) {
        int minLength = s.length();
        // 개수 별로 압축해보기
        for (int compact = 1; compact <= s.length(); compact++) {
            // 부분 문자열 만들기
            Stack<String> stack = makeSubString(s, compact);

            // 압축 문자열 만들기
            int compactLength = makeCompactString(stack);

            minLength = Math.min(minLength, compactLength);
        }

        return minLength;
    }

    private static Stack<String> makeSubString(String s, int compact) {
        Stack<String> stack = new Stack<>();

        // 첫번째 문자열 넣기
        stack.add(s.substring(0, compact));

        // 문자 잘라서 넣기
        int compactIndex = compact;
        while (compactIndex + compact <= s.length()) {
            stack.add(s.substring(compactIndex, compactIndex + compact));
            compactIndex += compact;
        }

        // 마지막에 남은 문자열 넣기
        stack.add(s.substring(compactIndex));

        return stack;
    }

    private static int makeCompactString(Stack<String> stack) {
        StringBuilder compactString = new StringBuilder();
        String curString = stack.pop();
        int repeatCount = 1;
        while (!stack.isEmpty()) {
            // 뽑으려는 문자가 동일하면
            if (stack.peek().equals(curString)) {
                repeatCount++;
                stack.pop();
            } else {
                // 반복이 2번 이상되는 경우에만 숫자 붙혀서 압축 아닌경우 그냥 문자열 붙혀주기
                if (repeatCount > 1) compactString.append(repeatCount).append(curString);
                else compactString.append(curString);

                curString = stack.pop();
                repeatCount = 1;
            }
        }

        // 마지막에 남은 문자 붙혀주기
        if (repeatCount > 1) compactString.append(repeatCount).append(curString);
        else compactString.append(curString);

        return compactString.length();
    }
}
