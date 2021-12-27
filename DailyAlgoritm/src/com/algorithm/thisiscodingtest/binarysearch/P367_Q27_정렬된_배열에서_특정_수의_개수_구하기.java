package com.algorithm.thisiscodingtest.binarysearch;

public class P367_Q27_정렬된_배열에서_특정_수의_개수_구하기 {
    public static void main(String[] args) {
        int N = 7;
        int target = 2;
        int[] arr = {1,1,2,2,2,2,3};

        int left = getFirstIdx(arr,0,N-1,target);
        int right = getLastIdx(arr,0,N-1,target);

        if(left == 0) System.out.println(-1);
        else System.out.println(right - left + 1);
    }

    private static int getFirstIdx(int[] arr, int start, int end, int target) {
        if(start > end) return -1;
        int mid = (start + end) /2 ;

        // 가장 첫번째 index 인 경우
        if((mid == 0 || arr[mid-1] < target) && arr[mid] == target) {
            return mid;
        }

        if(arr[mid] >= target) {
            return getFirstIdx(arr,start,mid-1,target);
        }
        else {
            return getFirstIdx(arr,mid+1, end, target);
        }
    }

    private static int getLastIdx(int[] arr, int start, int end, int target) {
        if(start > end) return -1;
        int mid = (start + end) / 2;

        // 가장 마지막 index 인 경우
        if((mid == arr.length-1 || arr[mid+1] > target) && arr[mid] == target) {
            return mid;
        }

        if(arr[mid] > target) {
            return getLastIdx(arr,start,mid-1,target);
        }
        else {
            return getLastIdx(arr,mid+1,end,target);
        }
    }
}
