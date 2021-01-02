package com.algorithm.Programmers.Lv2;

import java.util.*;

public class Solution_17683 {

    public static void main(String[] args) {
        String[] musicInfos ={"00:01,00:09,FOO,CCB#CCB", "04:00,04:08,ZZZ,CCB", "04:00,04:08,ZZZ,ABC"};
        String m = "CCB";

        System.out.println(solution(m,musicInfos));
    }

    public static String solution(String m, String[] musicinfos) {
        String replace = m.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a");

        Map<String, Integer> music = new HashMap<>();
        for(String info : musicinfos){
            StringTokenizer st = new StringTokenizer(info,",");
            String start = st.nextToken();
            String end = st.nextToken();
            String title = st.nextToken();
            String code = st.nextToken().replace("C#", "c")
                    .replace("D#", "d")
                    .replace("F#", "f")
                    .replace("G#", "g")
                    .replace("A#", "a");

            int playTime = getPlayTime(start, end);
            System.out.println(playTime);
            int loop = playTime / code.length();
            int cutCode = playTime % code.length();
            StringBuilder realCode = new StringBuilder();
            for(int i = 0; i<loop;i++){
                realCode.append(code);
            }
            realCode.append(code, 0, cutCode);
            if(realCode.toString().contains(replace)){
                music.put(title,playTime);
            }
        }
        if(music.size() < 1)
            return "(None)";

        List<Map.Entry<String, Integer>> entry = new ArrayList<>(music.entrySet());
        entry.sort(Collections.reverseOrder((o1, o2) -> {
            if(o1.getValue() >= o2.getValue())
                return 1;
            else
                return -1;
        }));
        return entry.get(0).getKey();
    }

    private static int getPlayTime(String start, String end) {
        StringTokenizer st = new StringTokenizer(start,":");
        int startHour = Integer.parseInt(st.nextToken());
        int startMinute = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(end,":");
        int endHour = Integer.parseInt(st.nextToken());
        int endMinute = Integer.parseInt(st.nextToken());

        if(startHour != 0 && endHour == 0) {
            endHour = 24;
        }

        return (endHour * 60 + endMinute) - (startHour * 60 + startMinute);
    }
}
