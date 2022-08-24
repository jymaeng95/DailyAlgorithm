package com.second.algorithm.interview.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {10, 27, 45, 28, 17, 1, 3, 29, 32, 27, 7, 9, 30, 12};
        sorted = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1);

        
    }

    // top-down
    private static int[] sorted;

    private static void mergeSort(int[] arr, int left, int right) {
        // 배열 사이즈가 1개로 작아지면 분할 종료
        if (left == right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        // 병합
        merge(arr, left, mid, right);
        System.out.println("arr = " + Arrays.toString(arr) + ", left = " + left + ", right = " + right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int idx = left;

        while (l <= mid && r <= right) {
            // 왼쪽 배열이 작거나 같은 경우 (안정 정렬)
            if (arr[l] <= arr[r]) {
                sorted[idx] = arr[l];
                l++;
                idx++;
            }
            // 오른쪽 배열이 왼쪽 배열 보다 큰 경우
            else {
                sorted[idx] = arr[r];
                r++;
                idx++;
            }
        }

        // 왼쪽이 먼저 끝난 경우
        if (l > mid) {
            while (r <= right) {
                sorted[idx] = arr[r];
                r++;
                idx++;
            }
        }
        // 오른쪽이 먼저 끝난 경우
        else {
            while (l <= mid) {
                sorted[idx] = arr[l];
                l++;
                idx++;
            }
        }

        // sorted 정렬된 것을 기존 배열에 복사
        for (int index = left; index <= right; index++) {
            arr[index] = sorted[index];
        }
    }
}
