package com.algorithm.thisiscodingtest.binarysearch;

public class P190_이진탐색_재귀 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 8;
        int idx = binarySearch(arr, 0, arr.length, target);
        System.out.println("arr[idx] = " + arr[idx]);
    }

    private static int binarySearch(int[] arr, int start, int end, int target) {
        if (start > end) return -1;
        int mid = (start + end) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearch(arr, start, mid - 1, target);
        } else {
            return binarySearch(arr, mid + 1, end, target);
        }
    }
}
