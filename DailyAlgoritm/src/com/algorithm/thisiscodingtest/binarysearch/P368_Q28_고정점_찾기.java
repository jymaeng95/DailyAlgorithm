package com.algorithm.thisiscodingtest.binarysearch;

public class P368_Q28_고정점_찾기 {
    public static void main(String[] args) {
        int N = 7;
//        int[] arr = {-15, -4,2,8,9,13,15};
        int[] arr = {-15,4,3,8,9,13,15};
        
        int index = getFixPoint(arr,0,N);
        System.out.println("index = " + index);

    }

    private static int getFixPoint(int[] arr, int start, int end) {
        if(start > end) return -1;
        int mid = (start + end) /2;
        
        // 타겟인 경우
        if(arr[mid] == mid) return mid;
        
        if(arr[mid] > mid) return getFixPoint(arr,start,mid-1);
        else return getFixPoint(arr,mid+1,end);
    }
}
