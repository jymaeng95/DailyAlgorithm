package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_16120_PPAP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        String rst = checkPPAP(str);
        System.out.println(rst);
        br.close();
    }

    private static String checkPPAP(String str) {
        Stack<Character> stack = new Stack<>();

        int index = 0;
        for (char c : str.toCharArray()) {
            if(c == 'P') stack.push(c);
            // A인 경우
            else {
                // A 다음 P인 경우 PPAP 성립
                // 다음 문자 확인 위해 인덱스 체크, PP삭제 위해 스택 사이즈 체크. 다음 문자 P인지 체크
                if(index < str.length() - 1 && stack.size() >= 2 && str.charAt(index+1) == 'P') {
                    stack.pop();
                    stack.pop();
                }
                // 이외의 경우 PPAP를 P로 대체할 수 없음
                else {
                    return "NP";
                }
            }
            index++;
        }

        return stack.size() == 1 ? "PPAP" : "NP";
    }
}
