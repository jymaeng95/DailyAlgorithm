package com.algorithm.Programmers.Lv1;

public class Solution_12921_소수_찾기 {
    public static void main(String[] args) {
        int solution = solution(10);
        System.out.println("solution = " + solution);
    }

    private static int solution(int n) {
        int answer = 0;
        boolean[] prime = new boolean[n + 1];

        for (int i = 2; i * i <= n; i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = true;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!prime[i]) answer++;
        }
        return answer;
    }
}
