package com.algorithm.Programmers.Lv1;

import java.util.ArrayList;
import java.util.List;

public class Solution_12906_같은_숫자는_싫어 {
    public static void main(String[] args) {
        int[] arr = {1,1,3,0,2,1,5,4,6};
        int[] solution = solution(arr);
    }
    public static int[] solution(int []arr) {
        List<Integer> list = new ArrayList<>();
        int first = arr[0];
        for(int i=1;i<arr.length;i++){
            if(first == arr[i])
                continue;
            list.add(first);
            first = arr[i];
        }
        list.add(first);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
