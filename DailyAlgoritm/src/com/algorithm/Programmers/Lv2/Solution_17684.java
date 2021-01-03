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
            String nextMsg = String.valueOf(msg.charAt(i + 1));

            //현재 글자와 다음 글자를 합쳐줌
            String addMsg = curMsg + nextMsg;

            //사전에 있는 단어의 경우 계속 이어붙히기
            if(dict.containsKey(addMsg)) {
                curMsg = addMsg;
                continue;
            }

            //사전에 없는 경우 사전에 넣어줌
            dict.put(addMsg,dict.size()+1);

            //색인 번호 리스트에 넣기
            list.add(dict.get(curMsg));
            curMsg = nextMsg;
        }
        list.add(dict.get(curMsg));


        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
