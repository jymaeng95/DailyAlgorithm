package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main_9996_한국이_그리울_땐_서버에_접속하지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String pattern = br.readLine();

        for (int loop = 0; loop < N; loop++) {
            String str = br.readLine();

            String[] split = pattern.split("\\*"); // *로 파싱

            // ^접두사 [중간에 알파벳 소문자 반복]되는게 있거나 없거나 접미사$ 패턴 매칭되는 것 찾기
            boolean result = Pattern.matches("^" + split[0] + "[a-z]*" + split[1] + "$", str);

            if(result) System.out.println("DA");
            else System.out.println("NE");
        }

        br.close();

    }
}
