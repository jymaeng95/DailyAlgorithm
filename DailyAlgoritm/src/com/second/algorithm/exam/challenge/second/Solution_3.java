package com.second.algorithm.exam.challenge.second;

import java.util.*;

public class Solution_3 {
    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}};

        int[] sources = {1, 3, 5};
        int destination = 5;

        int[] rst = solution(n, roads, sources, destination);
        Arrays.stream(rst).forEach(System.out::println);

    }
    private static Map<Integer, List<Integer>> tree;
    private static List<List<Integer>> position;
    private static final int INF = (int) 1e9;

    private static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        position = new ArrayList<>();

        // 지점 정보 초기화
        for (int site = 0; site <= n; site++) {
            position.add(new ArrayList<>());
        }

        // 도로 연결
        for (int[] road : roads) {
            int startSite = road[0];
            int endSite = road[1];

            position.get(startSite).add(endSite);
            position.get(endSite).add(startSite);
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);

        findShortesPath(distance, destination);

        // 출발지로 부터 거리 확인
        int[] elapsedTime = new int[sources.length];
        for (int soldier = 0; soldier < sources.length; soldier++) {

            int time = distance[sources[soldier]];
            if(time != INF)
                elapsedTime[soldier] = time;
            else
                elapsedTime[soldier] = -1;
        }

        return elapsedTime;
    }

    private static void findShortesPath(int[] distance, int destination) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(destination, 0));

        while (!queue.isEmpty()) {
            Position postion = queue.poll();
            int startSite = postion.getSite();
            int time = postion.getTime();

            if (distance[startSite] != INF) continue;
            distance[startSite] = time;

            for (Integer nextSite : position.get(startSite)) {
                if(distance[nextSite] == INF) {
                    queue.add(new Position(nextSite, time + 1));
                }
            }
        }
    }

    static class Position {
        private int site;
        private int time;

        public Position(int site, int time) {
            this.site = site;
            this.time = time;
        }

        public int getSite() {
            return site;
        }

        public int getTime() {
            return time;
        }
    }
}
