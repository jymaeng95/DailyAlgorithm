package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_6588_골드바흐의_추측 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] prime = new boolean[1000001];
        makePrimeNumber(prime, 1000000);

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            String exp = guessGoldBach(N, prime);
            System.out.println(exp);
        }

        br.close();
    }

    private static void makePrimeNumber(boolean[] prime, int n) {
        // 에라토스테네스의 체
        for (int start = 2; start * start <= n; start++) { // i * i => n을 루트 씌운 것과 동잃
            if (!prime[start]) {
                for (int pow = start * start; pow <= n; pow += start) { // 2 * 2부터 시작해서 2를 더함
                    prime[pow] = true;
                }
            }
        }
    }

    private static String guessGoldBach(int n, boolean[] prime) {
        StringBuilder exp = new StringBuilder();


        for (int start = 2; start <= n; start++) {
            if (!prime[start] && !prime[n - start]) {
                exp.append(n).append(" = ").append(start).append(" + ").append(n - start);
                break;
            }
        }

        return exp.length() > 0 ? exp.toString() : "Goldbach's conjecture is wrong.";
    }
}
