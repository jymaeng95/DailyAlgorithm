package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2012_등수_매기기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] expect = new int[N];

        for(int i=0;i<N;i++) {
            expect[i] = Integer.parseInt(br.readLine());
        }

        long rst = getDissatisfaction(N,expect);
        System.out.println(rst);

        br.close();
    }

    private static long getDissatisfaction(int n, int[] expect) {
        Arrays.parallelSort(expect);
        int grade = 1;
        long dissatisfaction = 0;
        for(int ex : expect) {
            dissatisfaction += Math.abs(ex - grade);
            grade++;
        }
        return dissatisfaction;
    }
}
