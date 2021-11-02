package com.algorithm.thisiscodingtest.sort;

public class P164_삽입_정렬 {
    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 0, 1, 3, 6, 2, 4, 8};

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                } else {
                    break;
                }
            }
        }

        for (int x : arr)
            System.out.println("x = " + x);
    }
}
