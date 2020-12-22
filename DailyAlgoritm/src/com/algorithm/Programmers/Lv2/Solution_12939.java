package com.algorithm.Programmers.Lv2;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_12939 {
    public static void main(String[] args) {
        String s ="-2 1 3 -15";
        System.out.println(solution(s));
    }

    public static String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int[] arr = new int[st.countTokens()];
        for(int i=0;i<arr.length;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.parallelSort(arr);
        return arr[0]+" "+arr[arr.length-1];
    }
}
