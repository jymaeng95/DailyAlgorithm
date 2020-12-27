package com.algorithm.Programmers.Lv2;

import java.util.*;

public class Solution_17683 {

    public static void main(String[] args) {
        String[] musicInfos ={"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String m = "ABC";

        System.out.println(solution(m,musicInfos));
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";
        Map<String, Integer> music = new HashMap<>();
        for(String info : musicinfos){
            StringTokenizer st = new StringTokenizer(info,",");
            String start = st.nextToken();
            String end = st.nextToken();
            String title = st.nextToken();
            String code = st.nextToken();

            int playTime = getPlayTime(start, end);

        }
        if(music.size() < 1)
            return "(None)";
        List<Map.Entry<String, Integer>> entry = new ArrayList<>(music.entrySet());
        Collections.sort(entry,Collections.reverseOrder((o1, o2) -> Integer.compare(o1.getValue(),o2.getValue())));
        return entry.get(0).getKey();
    }

    private static int getPlayTime(String start, String end) {
        StringTokenizer st = new StringTokenizer(start,":");
        int startHour = Integer.parseInt(st.nextToken());
        int startMinute = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(end,":");
        int endHour = Integer.parseInt(st.nextToken());
        int endMinute = Integer.parseInt(st.nextToken());
        return (endHour * 60 + endMinute) - (startHour * 60 + startMinute);
    }
}
