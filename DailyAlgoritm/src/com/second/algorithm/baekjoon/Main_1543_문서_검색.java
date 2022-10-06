package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_1543_문서_검색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String docs = br.readLine();
        String pattern = br.readLine();

        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(docs);
        int count = 0;
        while(matcher.find()) count++; // 매치되는 패턴을 찾으면 증가

        System.out.println(count);
        br.close();
    }
}