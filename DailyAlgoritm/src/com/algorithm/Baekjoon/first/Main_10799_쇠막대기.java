package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_10799_쇠막대기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int rst = getSticks(str);
        System.out.println(rst);

        br.close();
    }

    private static int getSticks(String str) {
        Stack<Character> stack = new Stack<>();
        int stick = 0;
        for (int index = 0; index < str.length(); index++) {
            if(str.charAt(index) == '(') stack.push('(');
            else {
                stack.pop();
                if(str.charAt(index-1) == '(') stick += stack.size(); // 레이저인 경우 스택 사이즈 만큼 레이저의 왼쪽 부분이 잘라짐
                else stick += 1; // 막대가 끝난 경우에는 레이저를 자르고 남은 오른쪽 부분 한개를 추가해준다.
            }
        }
        return stick;
    }
}
