package com.algorithm.Programmers.Lv2;

import java.util.*;

public class Solution_42888 {
    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        solution(record);
    }
    public static String[] solution(String[] record) {
        Map<String,String> userInfo = new HashMap<>();
        List<String> list = new ArrayList<>();
        for(String rcd : record) {
            StringTokenizer st = new StringTokenizer(rcd);
            String act = st.nextToken();

            if(act.equals("Enter") || act.equals("Change")) {
                String userId = st.nextToken();
                String nickname = st.nextToken();
                userInfo.put(userId,nickname);
            }
        }


        for(String rcd : record) {
            StringTokenizer st = new StringTokenizer(rcd);
            String act = st.nextToken();
            String userId = st.nextToken();
            switch (act) {
                case "Enter" :
                    list.add(userInfo.get(userId)+"님이 들어왔습니다.");
                    break;
                case "Leave" :
                    list.add(userInfo.get(userId)+"님이 나갔습니다.");
                    break;
            }
        }

        String[] answer = new String[list.size()];
        return list.toArray(answer);
    }
}
