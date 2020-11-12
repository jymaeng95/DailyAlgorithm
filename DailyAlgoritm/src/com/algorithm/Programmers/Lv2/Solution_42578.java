package com.algorithm.Programmers.Lv2;


//프로그래머스 LV2 (위장)

import java.util.*;

public class Solution_42578 {
    public static void main(String[] args) {
        String[][] clothes = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
        System.out.println(solution(clothes));
    }
    public static int solution(String[][] clothes) {
        int answer = 1;

        Map<String,Integer> clothCount = new HashMap<>();
        for(String[] cloth : clothes){
            //키가 포함되어 있으면 +1
            if(clothCount.containsKey(cloth[1])){
                clothCount.replace(cloth[1],clothCount.get(cloth[1])+1);
                continue;
            }
            //키가 없는 경우 1로 초기화
            clothCount.put(cloth[1],1);
        }
        for(int value : clothCount.values())
            answer *= (value+1);    //안 입은 경우 추가 후 모든 경우의 수 곱셈

        // 모두 안입은 경우는 없으므로 -1
        return answer -1;
    }

}
