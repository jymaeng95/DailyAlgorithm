package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1111_IQ_Test {
    private static int N;
    private static int[] results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        results = new int[N];
        for (int index = 0; index < N; index++) {
            results[index] = Integer.parseInt(st.nextToken());
        }

        String rst = solveIqTest();
        System.out.println(rst);

        br.close();
    }


    private static String solveIqTest() {
        // N이 1인 경우 어떤 조건도 답이 될 수있으므로 "A"
        if (N == 1) return "A";
        // N이 2인 경우 두개의값이 동일
        if (N == 2) {
            if (results[0] == results[1]) return String.valueOf(results[0]);
            else return "A";
        }

        // N이 3이상인 경우
        int first = results[1] - results[0];
        int second = results[2] - results[1];

        // 0으로 나눠떨어지는 경우
        if (first == 0) {
            for (int index = 2; index < N; index++) {
                if (results[0] != results[index]) return "B";
            }
            // 값이 동일한 경우
            return String.valueOf(results[N - 1]);
        }

        if (second % first != 0) return "B";
        int a = second / first;
        int b = results[1] - results[0] * a;

        for (int index = 2; index < N; index++) {
            if (results[index] != results[index - 1] * a + b) {
                return "B";
            }
        }

        return String.valueOf(results[N - 1] * a + b);
    }
}
