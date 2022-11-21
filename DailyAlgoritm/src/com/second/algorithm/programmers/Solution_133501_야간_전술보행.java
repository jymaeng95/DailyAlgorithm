package com.second.algorithm.programmers;

import java.util.Arrays;

public class Solution_133501_야간_전술보행 {
    public static void main(String[] args) {
        int distance = 10;
        int[][] scope = {{3, 4}, {5, 8}};
        int[][] times = {{2, 5}, {4, 3}};

        int rst = solution(distance, scope, times);
        System.out.println("rst = " + rst);
    }

    private static int solution(int distance, int[][] scope, int[][] times) {
        int minDistance = distance;

        for (int index = 0; index < scope.length; index++) {
            Arrays.sort(scope[index]);
            int start = scope[index][0];
            int end = scope[index][1];
            int work = times[index][0];
            int rest = times[index][1];

            int mod = work + rest;
            while (start <= end) {
                // 현재 근무 시작 범위에 도달하는 경우 최대로 갈 수 있는  (최소값 갱신)
                if ((start - 1) % mod < work) {
                    minDistance = Math.min(minDistance, start);
                    break;
                }

                start++;
            }
        }

        return minDistance;
    }
}
