package com.algorithm.Programmers.Lv3;

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
        int playTime = timeToSecond(play_time);
        int advTime = timeToSecond(adv_time);
        int[] viewer = new int[playTime];       // 100시간 치
        long maxViewTime = 0;               //최대 시청 시간
        int advStart = 0;                   //광고 시작 시간

        for (String log : logs) {
            StringTokenizer st = new StringTokenizer(log, "-");
            int startTime = timeToSecond(st.nextToken());
            int endTime = timeToSecond(st.nextToken());

            // 해당 시점에 시청자 수 증가
            for (int second = startTime; second < endTime; second++) {
                viewer[second] += 1;
            }
        }

        int start = 0, end = advTime;
        // 최초 최대 광고 시간은 0초이상 광고플레이시간 미만 까지
        for (int second = 0; second < advTime; second++) {
            maxViewTime += viewer[second];
        }

        // 현재 부터 광고 시간까지 최대 시청시간
        long viewSum = maxViewTime;
        while (end < playTime) {
            viewSum = viewSum - viewer[start] + viewer[end];       //현재 시점에 광고 전체 시청시간 구하기 (이전 시점 광고 전체시간 에서 시작 시간  1초 빼주고 끝나는 시간 1초 증가)
            start++; end++;
            if (viewSum > maxViewTime) {
                maxViewTime = viewSum;      // 현재 시점 광고 전체 시청시간이 최대 광고 시청 시간보드 큰 경우
                advStart = start;           // 현재 시점 광고 시작 시점으로 갱신
            }

        }

        return secondToTime(advStart);
    }

    private static String secondToTime(int advStart) {
        StringBuilder sb = new StringBuilder();
        String hour = (advStart / 3600) < 10 ? "0" + (advStart / 3600) : String.valueOf(advStart / 3600);
        advStart %= 3600;
        String minute = (advStart / 60) < 10 ? "0" + (advStart / 60) : String.valueOf(advStart / 60);
        String second = (advStart % 60) < 10 ? "0" + (advStart % 60) : String.valueOf(advStart % 60);

        return sb.append(hour).append(":").append(minute).append(":").append(second).toString();
    }

    private static int timeToSecond(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        return hour * 3600 + minute * 60 + second;
    }
}
