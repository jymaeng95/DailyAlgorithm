package com.algorithm.Programmers.Lv2;

import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_17683_방금그곡 {
    public static void main(String[] args) {
        String m = "CC#BCC#BCC#BCC#B";
        String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};

        String rst = solution(m, musicinfos);
        System.out.println("rst = " + rst);
    }

    static class Music implements Comparable<Music>{
        private String melody;
        private int playTime;
        private String title;

        public Music(String melody, int playTime, String title) {
            this.melody = melody;
            this.playTime = playTime;
            this.title = title;
        }

        public String getMelody() {
            return melody;
        }

        public int getPlayTime() {
            return playTime;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public int compareTo(Music o) {
            return Integer.compare(this.playTime, o.playTime);
        }
    }
    private static PriorityQueue<Music> pq;
    private static String solution(String m, String[] musicinfos) {
        pq = new PriorityQueue<>();
        // Initialize
        String findMelody = m.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a");
        for(String musicInfo : musicinfos) {
            StringTokenizer st = new StringTokenizer(musicInfo, ",");
            String playStart = st.nextToken();
            String playEnd = st.nextToken();
            int playTime= getPlayTime(playStart, playEnd);

            String title = st.nextToken();
            String melody = st.nextToken().replace("C#", "c")
                    .replace("D#", "d")
                    .replace("F#", "f")
                    .replace("G#", "g")
                    .replace("A#", "a");
            int melodyLength = melody.length();
            int repeat = playTime / melodyLength;
            if(repeat > 0) melody = melody.repeat(repeat) + melody.substring(0,playTime % melodyLength);
            else melody = melody.substring(0, playTime);
            pq.add(new Music(melody, playTime, title));
        }

        int playMaxTime = 0;
        String title = "";
        while(!pq.isEmpty()) {
            Music music = pq.poll();
            int minute = 0;
            int playTime = music.getPlayTime();
            String melody = music.getMelody();
            if(melody.contains(findMelody) && playMaxTime < playTime) {
                title = music.getTitle();
            }
        }
        return title.length() > 0 ? title : "(None)";
    }

    private static int getPlayTime(String playStart, String playEnd) {
        StringTokenizer st = new StringTokenizer(playStart, ":");
        int startHour = Integer.parseInt(st.nextToken());
        int startMinute = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(playEnd, ":");
        int endHour = Integer.parseInt(st.nextToken());
        int endMinute = Integer.parseInt(st.nextToken());


        return (endHour * 60 + endMinute) - (startHour * 60 + startMinute);
    }
}
