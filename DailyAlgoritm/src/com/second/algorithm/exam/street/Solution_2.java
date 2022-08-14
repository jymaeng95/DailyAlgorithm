package com.second.algorithm.exam.street;

import java.util.Arrays;

public class Solution_2 {
    public static void main(String[] args) {
        int[] A = {0, 3, 3, 7, 5, 3, 11, 1};

        int rst = solution(A);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] A) {
        // 인접한 인덱스가 없는 경우
        if(A.length == 1) return -2;

        final int MAX = 100_000_000;
        Arrays.sort(A);

        // 정렬해 인접한 값 계산 시 P,Q사이에 속한 값이 배열에 없기 때문
        int minDistance = MAX;
        for (int index = 1; index < A.length; index++) {
            minDistance = Math.min(minDistance, Math.abs(A[index - 1] - A[index]));
        }

        // 최소 거리가 MAX 이상인 경우 -1, 아닌 경우 최소 거리 반환
        return minDistance >= MAX ? -1 : minDistance;
    }
}
