package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12904_A와_B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        int rst = isChangeWord(S, T);
        System.out.println(rst);

        br.close();
    }

    private static int isChangeWord(String s, String t) {
        // 1. t의 뒷 부분 판단
        // 2. A 인 경우 지우고 s와 비교
        // 3. B 인 경우 지우고 리버스 비교
        // 4. 문자열 길이가 동일해 졌을 때 다르면 false
        StringBuilder stringBuilder = new StringBuilder(t);
        while(s.length() != stringBuilder.length()) {
            int length = stringBuilder.length();
            int lastCharacter = stringBuilder.charAt(length - 1);
            stringBuilder.deleteCharAt(length-1);
            if(lastCharacter == 'B') {
                stringBuilder.reverse();
            }
        }

        return stringBuilder.toString().equals(s) ? 1 : 0;
    }
}
