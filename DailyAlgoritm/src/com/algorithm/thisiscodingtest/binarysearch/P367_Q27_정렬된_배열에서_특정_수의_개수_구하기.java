package com.algorithm.thisiscodingtest.binarysearch;

public class P367_Q27_정렬된_배열에서_특정_수의_개수_구하기 {
    public static void main(String[] args) {
        int N = 7;
        int target = 2;
        int[] arr = {1,1,2,2,2,2,3};

        int mid = N/2;
        int left = getFirstIdx(arr,mid-1,N,target);
        int right = getLastIdx(arr,0,mid,target);
    }

    private static int getLastIdx(int[] arr, int start, int end, int target) {
    }

    private static int getFirstIdx(int[] arr, int start, int end, int target) {
    }
}
