package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

    private static List<Integer> guess;

    private static String solveIqTest() {
        // N이 1인 경우 어떤 조건도 답이 될 수있으므로 "A"
        if (N == 1) return "A";
        // N이 2인 경우 두개의값이 동
        if (N == 2) {
            if (results[0] == results[1]) return String.valueOf(results[0]);
            else return "A";
        }

        guess = new ArrayList<>();
        int start = results[0];

        // 주어지는 결과의 절댓값이 100이기 때문에 a,b 모두 -100 ~ 100까지 가능 (1*100 + 0) (-1 * 100+0)(1*-100+0) (-1*-100 + 0)
        for (int a = -1000; a <= 1000; a++) {
            for (int b = -10100; b <= 10100; b++) {
                // 다음 결과가 동일하면 규칙으로 추측가능
                int next = start * a + b;
                if (next == results[1]) {
                    checkRule(2, results[1], a, b);
                }
            }
        }

        // 동일한 규칙으로 답을 찾을 수 없는 경우 "B"
        if (guess.isEmpty()) return "B";
        // 동일한 규칙으로 답이 여러개인 경우 "A"
        if (guess.size() > 1) return "A";

        // 그외 답 으로 출력
        return String.valueOf(guess.get(0));
    }

    private static void checkRule(int depth, int result, int a, int b) {
        if (depth == N) {
            // 규칙 찾은 경우 리스트에 저장
            guess.add(result * a + b);
            return;
        }

        int next = result * a + b;
        if (next == results[depth])
            checkRule(depth + 1, next, a, b);
    }
}
