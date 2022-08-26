package com.second.algorithm.interview.sort;

import java.util.*;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        System.out.println("============= 기수 정렬 (Ascending) ===============");
        radixSortAscending(arr);
    }

    private static void radixSortAscending(int[] arr) {
        // 자릿수 배열 생성
        Map<Integer, Queue<Integer>> radix = new HashMap<>();

        // 최대 자릿수 확인
        int max = Arrays.stream(arr).max().getAsInt();
        int maxPos = 1;
        while (max / 10 != 0) {
            max /= 10;
            maxPos *= 10;
        }

        int pos = 1;    // 햔재 자리수
        while (pos <= maxPos) {

            for (int number : arr) {
                // 자릿수 = 인덱스
                int index = (number / pos) % 10;

                // 자릿수 없는 경우 초기화
                if (!radix.containsKey(index))
                    radix.put(index, new LinkedList<>());

                radix.get(index).add(number);
            }

            int arrIndex = 0;
            for (int index = 0; index < 10; index++) {
                // 현재 자릿수 숫자가 있는 경우 while문 돌면서 원소 빼서 배열에 넣기
                while (radix.containsKey(index) && !radix.get(index).isEmpty()) {
                    arr[arrIndex++] = radix.get(index).poll();
                }
                radix.replace(index, new LinkedList<>());
            }
            System.out.print("[자릿수 : " + pos + "] ");
            System.out.println(Arrays.toString(arr));
            pos *= 10;
        }
    }
}
