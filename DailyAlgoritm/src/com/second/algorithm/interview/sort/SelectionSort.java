package com.second.algorithm.interview.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        System.out.println("============= 선택 정렬 (Ascending) ===============");
        selectionSortAscending(arr);

        System.out.println("============= 선택 정렬 (Descending) ===============");
        arr = new int[]{10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        selectionSortDescending(arr);
    }

    /**
     * 선택 정렬
     * 원소를 넣을 위치는 차례대로 loop를 통해 지정되며, 해당 위치부터 배열 끝까지 중에 가장 작은 원소를 선택해 지정한 위치에 넣는다.
     * 불안정 정렬, 최선 On2, 평균 On2 최악 On2 -> 배열 끝까지 반드시 최소값을 비교하기 때문
     * @param arr 배열
     */
    private static void selectionSortAscending(int[] arr) {
        for (int position = 0; position < arr.length - 1; position++) {
            int minValuePosition = position;
            int minValue = arr[position];

            // 지정한 위치부터 배열끝까지 돌면서 최소 값과 인덱스를 탐색
            for (int index = position + 1; index < arr.length; index++) {
                if (minValue > arr[index]) {
                    minValue = arr[index];
                    minValuePosition = index;
                }
            }

            // 최소값을 지정한 위치에 넣어준다(스왑)
            int temp = arr[position];
            arr[position] = minValue;
            arr[minValuePosition] = temp;

            System.out.print("[단계 : " + position + "] ");
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void selectionSortDescending(int[] arr) {
        for (int position = 0; position < arr.length - 1; position++) {
            int maxValue = arr[position];
            int maxValueIndex = position;

            for (int index = position + 1; index < arr.length; index++) {
                if(arr[index] > maxValue) {
                    maxValueIndex = index;
                    maxValue = arr[index];
                }
            }

            // 최대값 지정 위치에 넣어줌
            int temp = arr[position];
            arr[position] = maxValue;
            arr[maxValueIndex] = temp;

            System.out.print("[단계 : " + position + "] ");
            System.out.println(Arrays.toString(arr));
        }
    }
}
