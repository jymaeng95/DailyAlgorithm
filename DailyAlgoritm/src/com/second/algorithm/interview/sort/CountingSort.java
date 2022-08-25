package com.second.algorithm.interview.sort;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        System.out.println("============= 계수 정렬 (Ascending) ===============");
        countingSortAscending(arr);
        System.out.println("============= 계수 정렬 (Ascending) ===============");
    }

    private static void countingSortAscending(int[] arr) {
        // 수의 최대 범위 지정 필요 (여기선 최대값인 45로 지정)
        int[] count = new int[46];

        // 1. 각 원소 값 인덱스를 증가시킨다.
        for (int number : arr) {
            count[number]++;
        }

        // 2. 각 값의 인덱스를 확인하기 위해 누적합을 생성
        for (int number = 1; number < count.length; number++) {
            count[number] += count[number - 1];
        }

        // 3. 동일값에 대해 순서를 보장하기 위해 맨 뒤 인덱스 부터 정렬한다.
        int[] result = new int[arr.length];
        for (int index = arr.length - 1; index >= 0; index--) {
            int position = --count[arr[index]];
            result[position] = arr[index];

            System.out.print("[단계 : " + (arr.length - 1 - index) + "] ");
            System.out.println(Arrays.toString(result));
        }
    }
}
