package com.algorithm.Programmers.Lv2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_92341_주차_요금_계산 {
    static class Parking {
        private int inTime;
        private int totalTime;
        private String act;

        public Parking(int inTime, int totalTime, String act) {
            this.inTime = inTime;
            this.totalTime = totalTime;
            this.act = act;
        }
    }
    public static void main(String[] args) {
        int[] fees = {1, 461, 1, 10};
        String[] records = {"00:00 1234 IN"};

        int[] solution = solution(fees, records);
        for (int x : solution) System.out.println("x = " + x);
    }

    private static int[] solution(int[] fees, String[] records) {
        // 0 : 기본 시간, 1 : 기본 요금, 2 : 단위 시간, 3 : 단위 요금
        // 주차 요금 계산 : 기본 요금  + ((누적 시간 - 기본 시간) / 단위 시간 ) * 단위 요금
        // 출력 : 차량 번호 오름차순
        // 출차 안된 경우 :
        Map<String, Parking> parkingMap = new HashMap<>();
        for(String rc : records) {
            StringTokenizer st = new StringTokenizer(rc);
            String[] s = st.nextToken().split(":");
            String number = st.nextToken();
            String act = st.nextToken();

            int time = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
            if(act.equals("IN")) {
                if(parkingMap.containsKey(number)) {
                    Parking parking = parkingMap.get(number);
                    parkingMap.replace(number,new Parking(time, parking.totalTime, "IN"));
                }
                else {
                    parkingMap.put(number, new Parking(time, 0, "IN"));
                }
            }
            else {
                Parking parking = parkingMap.get(number);
                parking.totalTime += time - parking.inTime;
                parkingMap.replace(number, new Parking(0, parking.totalTime, "OUT"));
            }
        }
        for(String key : parkingMap.keySet()) {
            if (parkingMap.get(key).act.equals("IN")) {
                Parking parking = parkingMap.get(key);
                parking.totalTime += (23 * 60 + 59) - parking.inTime;
                parkingMap.replace(key, new Parking(0, parking.totalTime,"OUT"));
            }
        }

        Object[] keys = parkingMap.keySet().toArray();
        Arrays.sort(keys);
        for(Object key :keys) {
            Parking parking = parkingMap.get(key);
            System.out.print(key);
            System.out.print(" "+parking.inTime);
            System.out.print(" "+parking.totalTime);
            System.out.println(" "+parking.act);
        }
        int[] answer = new int[keys.length];
        // 주차 요금 계산 : 기본 요금  + ((누적 시간 - 기본 시간) / 단위 시간 ) * 단위 요금
        for(int i=0;i<answer.length;i++) {
            int totalTime = parkingMap.get(keys[i]).totalTime;
            if(totalTime - fees[0] < 0) answer[i] = fees[1];
            else answer[i] = fees[1] + (int) Math.ceil(((double)(totalTime - fees[0]) / fees[2])) * fees[3];
        }
        return answer;
    }
}
