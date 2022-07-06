package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1300_K번째_수_풀이중 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int rst = getNumber(N,K);
        System.out.println(rst);

        br.close();
    }

    private static int getNumber(int n, int k) {
        return ((k/n)+1) * ((k%n)+1);
    }
}

