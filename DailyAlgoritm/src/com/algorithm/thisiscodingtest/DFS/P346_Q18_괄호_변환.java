package com.algorithm.thisiscodingtest.DFS;

import java.util.Stack;

public class P346_Q18_괄호_변환 {
    public static void main(String[] args) {
        String p = "()))((()";
        String rst = changeBracket(p);
        System.out.println("rst = " + rst);
    }

    private static String changeBracket(String p) {
        // 1. 입력이 빈 문자열인 경우 빈문자열 return
        if(p.length() == 0)  return "";

        // 2. 문자열을 균형잡힌 괄호 문자열로 분리 (최대 균형잡힌 문자열)
        int left = 0;
        int right = 0;
        String u = "",v ="";
        for(int i=0;i<p.length();i++) {
            if(p.charAt(i) == '(') left++;
            if(p.charAt(i) == ')') right++;
            // 더이상 나눌 수 없는 균형잡힌 문자열
            if(left !=0 && right != 0 && left == right) {
                u += p.substring(0,i+1);
                v += p.substring(i+1);
                break;
            }
        }

        // 3. 수행 결과 문자열 u에 이어붙히고 리턴
        // 3-1. 올바른 괄호 문자열인 경우 v에 대해 1단계 부터 다시 수행
        if(correctBracket(u)) return u + changeBracket(v);

        // 4. u가 올바른 괄호 문자열 아닌 경우
        StringBuilder sb =new StringBuilder();
        // 4-1. 빈 문자열에 첫 번째 문자로 '(' 붙히기
        sb.append('(');
        // 4-2. 문자열 v에 대해 1단계부터 재귀적 수행 결과 이어붙히기
        sb.append(changeBracket(v));
        // 4-3. ')'를 다시 붙히기
        sb.append(')');
        // 4-4. u의 첫번째 맘지막 문제 제거 후 나머지 문자열 괄호 뒤집어 뒤에 붙히기
        sb.append(swapBracket(u.substring(1,u.length()-1)));

        return sb.toString();
    }

    private static boolean correctBracket(String u) {
        Stack<Character> stack = new Stack<>();
        for(char c : u.toCharArray()) {
            if(c == '(') stack.push(c);
            else if(c == ')' && !stack.isEmpty())
                stack.pop();
        }
        return stack.size() < 1;
    }

    private static String swapBracket(String u) {
        StringBuilder sb = new StringBuilder();
        for(char c: u.toCharArray()) {
            if(c == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }
}
