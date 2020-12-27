package com.algorithm.Programmers.Lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_17684 {
    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        solution(msg);
    }

    private static final String[] ALPHA = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public static int[] solution(String msg) {
        Map<String,Integer> dict = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<ALPHA.length;i++){
            dict.put(ALPHA[i], i+1);
        }

        String curMsg = String.valueOf(msg.charAt(0));
        for(int i=0;i<msg.length()-1;i++){
            String addMsg = curMsg + String.valueOf(msg.charAt(i+1));
            if(dict.containsKey(addMsg)) {
                curMsg = addMsg;
                continue;
            }
            dict.put(addMsg,dict.size()+1);
            list.add(dict.get(curMsg));
            curMsg = String.valueOf(msg.charAt(i+1));
        }
        list.add(dict.get(curMsg));
        System.out.println(list);


        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
