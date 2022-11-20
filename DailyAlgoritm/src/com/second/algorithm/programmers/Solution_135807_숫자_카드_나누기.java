package com.second.algorithm.programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution_135807_숫자_카드_나누기 {
    public static void main(String[] args) {
        int[] arrayA = {14, 35, 119};
        int[] arrayB = {18, 30, 102};

        int rst = solution(arrayA, arrayB);
        System.out.println("rst = " + rst);
    }

    private static int solution(int[] arrayA, int[] arrayB) {
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        // A 배열의 약수
        List<Integer> divisorsA = getDivisors(arrayA[0]);

        // B 배열의 약수
        List<Integer> divisorsB = getDivisors(arrayB[0]);

        // 최댓값 A 구하기
        return Math.max(find(divisorsA, arrayA, arrayB), find(divisorsB, arrayB, arrayA));
    }

    private static int find(List<Integer> divisors, int[] standard, int[] target) {
        for (int divisor : divisors) {
            boolean divideStandard = isDivide(standard, divisor);
            boolean divideTarget = isNotDivide(target, divisor);

            if(divideStandard && divideTarget) return divisor;
        }
        return 0;
    }

    private static boolean isNotDivide(int[] target, int divisor) {
        for (int number : target) {
            if (number % divisor == 0) return false;
        }

        return true;
    }

    private static boolean isDivide(int[] standard, int divisor) {
        for (int  number: standard) {
            if (number % divisor != 0) return false;
        }

        return true;
    }

    private static List<Integer> getDivisors(int number) {
        List<Integer> divisors = new LinkedList<>();
        for (int divisor = 1; divisor <= Math.sqrt(number); divisor++) {
            if(number % divisor == 0) {
                if(divisor * divisor == number) divisors.add(divisor);
                else {
                    divisors.add(divisor);
                    divisors.add(number / divisor);
                }
            }
        }

        divisors.sort(Collections.reverseOrder());
        return divisors;
    }
}
