package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_12978_배달 {
    public static void main(String[] args) {
//        int N = 5;
//        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
//        int K = 3;
        int N = 6;
        int[][] road ={{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
        int K = 4;
        int rst = solution(N, road, K);
        System.out.println("rst = " + rst);
    }

    private static List<List<Village>> roads;
    private static int solution(int N, int[][] road, int K) {
        roads = new ArrayList<>();

        // 그래프 초기화
        for (int village = 0; village <= N; village++) {
            roads.add(new ArrayList<>());
        }

        // 그래프 엣지 초기화
        for (int[] info : road) {
            int start = info[0];
            int end = info[1];
            int time = info[2];

            roads.get(start).add(new Village(end, time));
            roads.get(end).add(new Village(start, time));
        }

        // 1번 마을 기준
        int[] timeList = getTimeList(1, N);

        return (int) Arrays.stream(timeList).filter(time -> time <= K).count();
    }

    private static int[] getTimeList(int start, int n) {
        int[] timeList = new int[n + 1];
        Arrays.fill(timeList, (int) 1e9);

        PriorityQueue<Village> pq = new PriorityQueue<>();
        pq.add(new Village(start, 0));
        timeList[start] = 0;

        while(!pq.isEmpty()) {
            Village village = pq.poll();
            int number = village.getVilage();

            for (Village next : roads.get(number)) {
                int nextVillage = next.getVilage();
                int nextTime = next.getTime();

                if(timeList[nextVillage] > timeList[number] + nextTime) {
                    timeList[nextVillage] = timeList[number] + nextTime;
                    pq.add(next);
                }
            }
        }
        return timeList;
    }

    static class Village implements Comparable<Village> {
        private int vilage;
        private int time;

        public Village(int vilage, int time) {
            this.vilage = vilage;
            this.time = time;
        }

        @Override
        public int compareTo(Village o) {
            return Integer.compare(this.time, o.time);
        }

        public int getVilage() {
            return vilage;
        }

        public int getTime() {
            return time;
        }
    }

}
