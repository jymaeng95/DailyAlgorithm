package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1238_파티 {
    private static int N, M, X;
    private static List<List<Village>> roads;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        roads = new ArrayList<>();
        for (int village = 0; village <= N; village++) {
            roads.add(new ArrayList<>());
        }

        for (int road = 0; road < M; road++) {
            st = new StringTokenizer(br.readLine());
            int departure = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            roads.get(departure).add(new Village(destination, time));
        }

        int rst = goParty();
        System.out.println(rst);

        br.close();
    }

    private static final int INF = (int) 1e9;
    private static int goParty() {
        int[][] elapsedTime = new int[N + 1][N + 1];
        for (int village = 1; village <= N; village++) {
            Arrays.fill(elapsedTime[village], INF);
        }

        // 각 마을 별 도착 최소 시간 구하기
        for (int village = 1; village <= N; village++) {
            elapsedTime[village][village] = 0;
            getElapsedTime(elapsedTime, village);
        }

        int maxTime = 0;
        for (int village = 1; village <= N; village++) {
            // 각 마을 별 -> X -> 각 마을 소요시간 최대 구하기
            maxTime = Math.max(maxTime, elapsedTime[village][X] + elapsedTime[X][village]);
        }

        return maxTime;
    }

    // 각 마을 별 도착 최소 시간 구하기
    private static void getElapsedTime(int[][] elapsedTime, int village) {
        PriorityQueue<Village> pq = new PriorityQueue<>();
        pq.add(new Village(village, 0));

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            Village departure = pq.poll();
            int destination = departure.getDestination();

            for (Village nextVillage : roads.get(destination)) {
                int nextDestination = nextVillage.getDestination();
                int nextElapsedTime = nextVillage.getTime();
                if(elapsedTime[village][nextDestination] > elapsedTime[village][destination] + nextElapsedTime) {
                    elapsedTime[village][nextDestination] = elapsedTime[village][destination] + nextElapsedTime;
                    pq.add(nextVillage);
                }
            }
        }
    }

    static class Village implements Comparable<Village> {
        private int destination;
        private int time;

        public Village(int destination, int time) {
            this.destination = destination;
            this.time = time;
        }

        public int getDestination() {
            return destination;
        }

        public int getTime() {
            return time;
        }

        @Override
        public int compareTo(Village o) {
            return Integer.compare(this.time, o.time);
        }
    }
}
