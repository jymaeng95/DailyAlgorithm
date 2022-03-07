package com.algorithm.Programmers.Lv3;

import java.util.PriorityQueue;

public class Solution_17678_1차_셔틀버스 {
    public static void main(String[] args) {
//        String[] timetable = {"08:00", "08:01", "08:02", "08:03"};      // 마지막 버스에 자리가 남는 경우
//        String[] timetable = {"09:10", "09:09", "08:00"};             // 마지막 자리인 경우 pq.peek() - 1 or 현재 버스시간 중 작은걸로
//        String[] timetable = {"09:00", "09:00", "09:00", "09:00"};
//        String[] timetable = {"00:01", "00:01", "00:01", "00:01", "00:01"};
//        String[] timetable = {"23:59"};
//        String[] timetable = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
//        String[] timetable = {"00:01", "00:01", "00:01","00:01", "00:01", "00:02", "00:03", "00:04"};
        String[] timetable = {"10:00"};
        int n = 7;
        int t = 10;
        int m = 12;

        String rst = solution(n, t, m, timetable);
        System.out.println(rst);
    }

    private static String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (String times : timetable) {
            String[] time = times.split(":");
            pq.add(Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
        }

        // n = 하루 n회 t = t분 간격으로 m = 1번에 m 명까지
        int maxCap = n * m;
        int busCount = n;
        int busArrive = 540; // 9:00
        int curCap = m;
        int conArriveTime = 1439;
        while (!pq.isEmpty() && busArrive < 1440 && busCount > 0) {
            int curPersonArrive = pq.peek();

            // 현재 버스에 한자리 이상 남고 현재 사람이 버스 도착시간 보다 빨리 온 경우
            if (curPersonArrive <= busArrive && curCap > 0) {
                conArriveTime = pq.poll() - 1; //대기열에서 빼주면서 콘이 와야하는 시간 계산 (마지막 탄 사람 -1)
                curCap--;       // 현재 버스 자리 빼주기
                maxCap--;       // 모든 버스 자리에 대해 빼주기
                // 현재 버스 자리가 0석이 된 경우 버스 보내기
                if (curCap == 0) {
                    busArrive += t;     // 다음 버스 시간
                    curCap = m;         // 다음 버스 자리 m석
                    busCount--;         // 버스 횟수 차감
                }
            }
            // 사람이 버스 도착 시간 보다 늦게 온 경우 현재 버스 보냄
            else if (curPersonArrive > busArrive) {
                conArriveTime = busArrive; // 현재 버스가 마지막 일 수도 있기 때문에 콘이 와야하는 시간 = 버스 시간
                busArrive += t;         // 다음 버스 시간
                maxCap -= curCap;       // 모든 버스 자리에 대해 현재 빈 자리 빼주기
                curCap = m;             // 다음 버스 빈자리 m석
                busCount--;             // 버스 보내기
            }
        }
        // 자리 있는 경우 마지막 버스 시간
        StringBuilder answer = new StringBuilder();
        if (maxCap <= 0) busArrive = conArriveTime;
        String hh = busArrive / 60 > 9 ? String.valueOf(busArrive / 60) : "0" + busArrive / 60;
        String mm = busArrive % 60 > 9 ? String.valueOf(busArrive % 60) : "0" + busArrive % 60;
        answer.append(hh).append(":").append(mm);

        return answer.toString();
    }
}
