package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main_1013_Contact {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int loop = 0; loop < N; loop++) {
            String str = br.readLine();
            String pattern = "(100+1+|01)+";

            if(Pattern.matches(pattern, str)) System.out.println("YES");
            else System.out.println("NO");

        }

        br.close();
    }
}
