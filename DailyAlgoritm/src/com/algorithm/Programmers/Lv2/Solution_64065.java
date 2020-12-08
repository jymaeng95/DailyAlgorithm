package com.algorithm.Programmers.Lv2;

import java.util.*;

public class Solution_64065 {
    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        solution(s);
    }
    public static int[] solution(String s) {
        //튜플은 가장 많이 나온 원소 순서대로 저장됨을 확인
        //중괄호 제거
        s = s.replaceAll("[{}]","");
        Map<String,Integer> count = new HashMap<>();
        for(String num : s.split(",")){
            if(count.containsKey(num)){
                count.replace(num,count.get(num)+1);
                continue;
            }
            count.put(num,1);
        }

        //value로 정렬
        List<Map.Entry<String, Integer>> list = new ArrayList<>(count.entrySet());
        Collections.sort(list,Collections.reverseOrder(Map.Entry.comparingByValue()));

        //기존 코드
//        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        int[] answer = new int[count.size()];
        int i=0;
        for(Map.Entry<String,Integer> entry : list){
            answer[i] = Integer.parseInt(entry.getKey());
            i++;
        }

        return answer;
    }
}
