package com.second.algorithm.programmers;

public class Solution_12924_숫자의_표현 {
    public static void main(String[] args) {
        int n = 15;
        int rst = solution(n);
        System.out.println(rst);
    }

    private static int solution(int n) {
        int count = 0;

        // 시작 수
        for (int start = 1; start <= n; start++) {
            int sum = start;
            // 다음 수 부터 for문
            for (int next = start + 1; next <= n; next++) {
                sum += next;
                if(sum == n) count++;   // 만들 수 있는 경우 갯수 증가
                if(sum >= n) break;     // 시간 효율성 위해 타겟 수 넘어가면 탈출
            }
        }

        return count+1;         // 자기 자신까지 더해야하므로 + 1
    }
}
