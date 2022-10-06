package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main_2671_잠수함식별 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String engine = br.readLine();

        // (100+1+|01)+ => 10에다가 0이 반복되고 그 뒤에 1이 반복(1개이상) 이거나 01 -> 이 2개를 그룹으로 보고 이 그룹 중 하나랑 매칭 되는 것
        // 문자열이 완전한 매칭이되는 경우에만 잠수함
        if(Pattern.matches("(100+1+|01)+", engine)) System.out.println("SUBMARINE");
        else System.out.println("NOISE");
        br.close();
    }
}
