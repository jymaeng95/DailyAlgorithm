package com.algorithm.Programmers.Lv1;

import java.util.HashMap;
import java.util.Map;

public class Solution_42576 {
    public static void main(String[] args) {
        String[] participant ={"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        solution(participant,completion);
    }
    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String,Integer> marathon = new HashMap<>();
        for(String pc : participant){
            if(marathon.containsKey(pc)) {
                marathon.put(pc, marathon.get(pc) + 1);
                continue;
            }
            marathon.put(pc,1);
        }
        for(String cp : completion){
            if(marathon.get(cp) > 1){
                marathon.replace(cp,marathon.get(cp),marathon.get(cp)-1);
                continue;
            }
            marathon.remove(cp);
        }
        answer = marathon.keySet().toString().replaceAll("[\\[\\]]","");
        return answer;
    }
}
