package com.second.algorithm.softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_389_성적_평균 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] grade = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int student = 0; student < N; student++) {
            grade[student] = Integer.parseInt(st.nextToken());
        }

        // 누적합 구하기
        int[] prefixSum = new int[N + 1];
        for (int index = 1; index <= N; index++) {
            prefixSum[index] = prefixSum[index - 1] + grade[index - 1];
        }

        // 누적합으로 구간 계산
        for (int loop = 0; loop < K; loop++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int studentCount = end - start + 1;
            int gradeSum = prefixSum[end] - prefixSum[start - 1];

            double avg = Math.round((double)gradeSum /  studentCount * 100) / 100.0;
            System.out.printf("%.2f\n", avg);
        }

        br.close();
    }
}
