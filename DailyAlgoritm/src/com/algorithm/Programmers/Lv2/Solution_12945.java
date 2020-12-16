package com.algorithm.Programmers.Lv2;

public class Solution_12945 {
    public static void main(String[] args) {
        int n = 1000;
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        int answer = fibonacci(n);
        return answer;
    }

    private static int fibonacci(int n) {
        int[] memo = new int[n+1];
        memo[0] = 0;
        memo[1] = 1;
        for(int i=2;i<=n;i++){
            memo[i] = (memo[i-2] + memo[i-1]) % 1234567;
        }
        return memo[n];
    }
}
