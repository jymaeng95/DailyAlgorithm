package com.algorithm.Programmers.Lv2;

public class Solution_92335_K진수에서_소수_개수_구하기 {
    public static void main(String[] args) {
        int n = 20002020;
        int k = 10;
        int solution = solution(n, k);
        System.out.println("solution = " + solution);
    }

    private static int solution(int n, int k) {
        int answer = 0;

        StringBuilder sb = new StringBuilder();
        while (n > k) {
            sb.append(n%k);
            n /= k;
        }
        sb.append(n);
        sb.reverse();

        if (sb.length() < 1) return 0;
        String[] num = sb.toString().split("0");

        for (String str : num) {
            if(str.length() < 1) continue;
            long prime = Long.parseLong(str);
            if (prime < 2) continue;
            boolean isPrime = true;
            for (long i = 2; i * i <= prime; i++) {
                if (prime % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) answer++;
        }
        return answer;
    }
}
