package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13164_행복_유치원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(br.readLine());
        int[] students = new int[N];
        int[] diffHeight = new int[N];
        for(int height = 0; height < N; height++) {
            students[height] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int rst = makeTShirts(N, K, students, diffHeight);
        System.out.println(rst);
        br.close();
    }

    private static int makeTShirts(int n, int k, int[] students, int[] diffHeight) {
        // 앞 학생과의 키차이 저장
        for(int diff = 1; diff < n; diff++) {
            diffHeight[diff] = students[diff] - students[diff - 1];
        }

        // 가장 큰 키차이가 나는 학생을 다른 조로 배정
        Arrays.parallelSort(diffHeight);
        int cost = 0;
        for(int student = n - k ; student >= 0; student--) {
            cost += diffHeight[student];
        }

        return cost;
    }
}
