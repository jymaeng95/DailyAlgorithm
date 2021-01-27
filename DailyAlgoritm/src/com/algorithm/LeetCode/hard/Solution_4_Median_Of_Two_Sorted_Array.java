package com.algorithm.LeetCode.hard;

import java.util.Arrays;

public class Solution_4_Median_Of_Two_Sorted_Array {
    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2,4};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);

    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length+ nums2.length];
        for(int i =0;i<nums1.length;i++){
            arr[i] = nums1[i];
        }
        for(int j =0;j<nums2.length;j++){
            arr[j+nums1.length] = nums2[j];
        }
        Arrays.parallelSort(arr);
        double median = 0.0;
        if(arr.length %2 ==0)
            median = (arr[arr.length/2] + arr[(arr.length/2)-1]) / 2.0;
        else
            median = arr[arr.length/2];
        return median;
    }
}
