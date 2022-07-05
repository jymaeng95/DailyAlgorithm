package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9935_문자열_폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        String explosionString = br.readLine();

        String rst = stringExplosion(target, explosionString);
        System.out.println(rst);

        br.close();
    }

    private static String stringExplosion(String target, String explosionString) {
        Stack<Character> stack = new Stack<>();

        // 첫 번째 문자열 폭발 성공
        for (char letter : target.toCharArray()) {
            stack.push(letter);
            // 문자열 최대 36 * 1,000,000 이므로 36,000,000 이므로 이중 for문 가능
            if (stack.size() >= explosionString.length()) {
                boolean explosion = true;
                for (int index = 0; index < explosionString.length(); index++) {
                    // 현재 스택 사이즈 - 폭발 문자열 길이 문자 모두 체크
                    if(stack.get(stack.size() - explosionString.length() + index) != explosionString.charAt(index)) {
                        explosion = false;
                        break;
                    }
                }

                // 폭발 성립시 폭발 (스택에서 문자열 길이만큼 팝)
                if(explosion) {
                    for(int index = 0; index < explosionString.length(); index++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.length() > 0 ? sb.reverse().toString() : "FRULA";
    }
}
