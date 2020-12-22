package com.algorithm.Programmers.Lv2;

import java.util.Arrays;
import java.util.Collections;

public class Solution_12941 {
    public static void main(String[] args) {
        int[] A = {1,4,2};
        int[] B = {5,4,4};
        System.out.println(solution(A,B));
    }

    public static int solution(int[] A, int[] B) {
        Arrays.parallelSort(A);
        Integer[] b = new Integer[B.length];
        for(int i=0;i<B.length;i++){
            b[i] = B[i];
        }
        Arrays.parallelSort(b, Collections.reverseOrder());
        int sum = 0;
        for(int i=0;i<A.length;i++){
            sum += A[i] * b[i];
        }
        return sum;
    }
}
