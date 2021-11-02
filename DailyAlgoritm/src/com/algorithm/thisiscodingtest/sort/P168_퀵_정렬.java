package com.algorithm.thisiscodingtest.sort;

public class P168_퀵_정렬 {
    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 0, 1, 3, 6, 2, 4, 8};

        quickSort(0, arr.length - 1, arr);
        for (int x : arr) {
            System.out.println("x = " + x);
        }
    }

    private static void quickSort(int start, int end, int[] arr) {
        if (start >= end) return;
        int left = start + 1;
        int pivot = start;
        int right = end;

        while (left <= right) {
            while (left <= end && arr[left] <= arr[pivot]) {
                left++;
            }
            while (right > start && arr[right] >= arr[pivot]) {
                right--;
            }
            if (left > right) {
                int tmp = arr[pivot];
                arr[pivot] = arr[right];
                arr[right] = tmp;
            } else {
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }
        quickSort(start, right - 1, arr);
        quickSort(right + 1, end, arr);
    }
}
