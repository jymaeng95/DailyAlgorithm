package com.algorithm.thisiscodingtest.binarysearch;

public class P190_이진탐색_반복 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 10;

        int start = 0;
        int end = arr.length -1;
        int idx = -1;
        while(start <= end){
            int mid = (start+end) / 2;
            if(arr[mid] == target){
                idx = mid;
                break;
            }
            else if(arr[mid] > target){
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        if(idx != -1) {
            System.out.println("arr[idx] = " + arr[idx]);
        }else {
            System.out.println("NO Target");
        }
    }
}
