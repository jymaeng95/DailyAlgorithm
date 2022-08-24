package com.second.algorithm.interview.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        System.out.println("============= 퀵 정렬 (Ascending) ===============");
        quickSortAscending(0, 0, arr.length - 1, arr);

        System.out.println("============= 퀵 정렬 (Descending) ===============");
        arr = new int[]{10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};

    }

    /**
     * 퀵 정렬
     * 분할 정복 방식을 통해 임의의 값을 피봇으로 설정, 피봇 기준 왼쪽은 피봇보다 작은 값, 오른쪽은 피봇보다 큰 값
     *
     * @param arr
     */
    private static void quickSortAscending(int index, int left, int right, int[] arr) {
        if (left >= right) return;

        int pivot = partition(left, right, arr);
        System.out.print("[단계 : " + index + "] ");
        System.out.println(Arrays.toString(arr));

        // 피봇 기준 분할
        quickSortAscending(index + 1, left, pivot - 1, arr);
        quickSortAscending(index + 1, pivot, right, arr);
    }

    private static int partition(int left, int right, int[] arr) {
        int mid = (left + right) / 2;
        int pivot = arr[mid];

        int l = left, r = right;
        while (l <= r) {
            // 왼쪽을 피봇보다 큰 값을 만날때까지 이동
            while (pivot > arr[l]) l++;

            // 오른쪽을 피봇보다 작은 값을 만날때까지 이동
            while (pivot < arr[r]) r--;

            // l이 r보다 왼쪽에 있는 경우 서로 Swap
            if (l <= r) {
                swap(l, r, arr);
                l++;
                r--;
            }
        }

        return l;
    }

    private static void swap(int l, int r, int[] arr) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
