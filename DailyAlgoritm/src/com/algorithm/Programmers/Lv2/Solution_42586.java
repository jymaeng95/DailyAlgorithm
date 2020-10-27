package com.algorithm.Programmers.Lv2;

import java.util.*;

//프로그래머스 (LV2 기능 개발)
public class Solution_42586 {
    public Collection<Integer> solution(int[] progresses, int[] speeds) {
        int[] day = new int[progresses.length];
        for(int i=0;i<day.length;i++)
            day[i] = 0;
        int i=0;
        for (int pg : progresses){
            while(pg<100){
                pg+=speeds[i];
                day[i]++ ;
            }
            if(i!=0) {
                if(day[i]<day[i-1])
                    day[i] = day[i-1];
            }
            i++;
        }

        Map<Integer,Integer> map = new LinkedHashMap<>();
        for(int dy :  day){
            if(map.containsKey(dy)){
                map.put(dy,map.get(dy)+1);
            }else
                map.put(dy,1);
        }

        return map.values();
    }

    public static void main(String[] args) {
        Solution_42586 sl = new Solution_42586();
        int[] progress = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};
        System.out.println(sl.solution(progress,speeds));
    }
}
