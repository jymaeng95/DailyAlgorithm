package com.algorithm.Programmers.Lv1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_12910_나누어_떨어지는_숫자_배열 {
    public static void main(String[] args) {
        int[] arr = {5,9,7,10};
        int[] solution = solution(arr, 5);
    }
    public static int[] solution(int[] arr, int divisor) {
        List<Integer> list = new ArrayList<>();
        for(int num : arr){
            if(num % divisor == 0){
                list.add(num);
            }
        }
        Collections.sort(list);
        if(list.size()<1)
            return new int[] {-1};
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
