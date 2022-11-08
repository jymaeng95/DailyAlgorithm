package com.second.algorithm.programmers;

import java.util.Arrays;

public class Solution_12987_숫자_게임 {
    public static void main(String[] args) {
        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};

        int rst = solution(A, B);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int score = 0;
        int indexA = 0;

        for (int indexB = 0; indexB < B.length; indexB++) {
            if (A[indexA] < B[indexB]) {
                score++;
                indexA++;
            }
        }

        return score;
    }
}
