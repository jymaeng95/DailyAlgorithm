package com.second.algorithm.programmers;

import java.util.StringTokenizer;

public class Solution_72414_광고_삽입 {
    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        String rst = solution(play_time, adv_time, logs);
        System.out.println("rst = " + rst);
    }

    private static String solution(String play_time, String adv_time, String[] logs) {
        if (play_time.equals(adv_time)) return "00:00:00";

        int playTime = convertToSecond(play_time);
        long[] viewers = new long[playTime+1];

        for (String log : logs) {
            StringTokenizer st = new StringTokenizer(log, "-");
            int startTime = convertToSecond(st.nextToken());
            int endTime = convertToSecond(st.nextToken());

            // 시작과 마지막에 대해 표시
            viewers[startTime]++;
            viewers[endTime]--;
        }

        // 처음 부터 마지막 체크
        for (int second = 1; second < playTime; second++) {
            viewers[second] += viewers[second - 1];
        }

        // 누적합 구하기
        long[] prefix = new long[playTime + 1];
        for (int second = 1; second <= playTime; second++) {
            prefix[second] = prefix[second - 1] + viewers[second - 1];
        }

        // 누적합으로 확인
        long totalViewers = 0;
        int startTime = 0;
        int advTime = convertToSecond(adv_time);
        for (int second = 0; second <= playTime - advTime; second++) {
            long accViewers = prefix[second + advTime] - prefix[second];
            if (totalViewers < accViewers) {
                startTime = second;
                totalViewers = accViewers;
            }
        }

        return convertToString(startTime);
    }

    private static String convertToString(int startTime) {
        int hours = startTime / 3600;
        int minutes = (startTime % 3600) / 60;
        int seconds = startTime % 3600 % 60;

        String hour = hours < 10 ? "0" + hours : hours + "";
        String minute = minutes < 10 ? "0" + minutes : minutes + "";
        String second = seconds < 10 ? "0" + seconds : seconds + "";

        return String.format("%s:%s:%s", hour, minute, second);
    }

    private static int convertToSecond(String play_time) {
        StringTokenizer st = new StringTokenizer(play_time, ":");
        int hours = Integer.parseInt(st.nextToken());
        int minutes = Integer.parseInt(st.nextToken());
        int seconds = Integer.parseInt(st.nextToken());

        return hours * 3600 + minutes * 60 + seconds;
    }
}
