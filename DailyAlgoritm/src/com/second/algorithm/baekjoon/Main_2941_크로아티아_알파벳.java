package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2941_크로아티아_알파벳 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String replace = str.replaceAll("c=|c-|dz=|d-|lj|nj|s=|z=", ".");
        System.out.println(replace.length());

        br.close();
    }
}
