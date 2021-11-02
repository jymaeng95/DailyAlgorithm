package com.algorithm.thisiscodingtest.sort;

public class P159_선택_정렬 {
    public static void main(String[] args) {
        int[] arr = {7,5,9,0,1,3,6,2,4,8};
        for(int i=0;i<arr.length;i++){
            int minIdx = i;
            for(int j = i;j<arr.length;j++){
                if(arr[minIdx] > arr[j]){
                    minIdx = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }
        
        for(int x:arr)
            System.out.println("x = " + x);
    }
}
