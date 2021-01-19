package com.algorithm.Programmers.Lv3;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution_42861_섬_연결하기 {
    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        int solution = solution(n, costs);
        System.out.println(solution);
    }

    static class Island implements Comparable<Island> {
        private int index;
        private int distance;

        public Island(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Island o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    private static ArrayList<ArrayList<Island>> route;
    private static boolean[] visited;

    public static int solution(int n, int[][] costs) {
        visited = new boolean[n];
        route = new ArrayList<>();
        for(int i=0;i<n;i++)
            route.add(new ArrayList<>());

        for (int i = 0; i < costs.length; i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int distance = costs[i][2];
            route.get(start).add(new Island(end, distance));
            route.get(end).add(new Island(start, distance));
        }

        return mst();
    }

    private static int mst() {
        PriorityQueue<Island> pq = new PriorityQueue<>();
        pq.add(new Island(0, 0));
        int distance = 0;
        while (!pq.isEmpty()) {
            Island island = pq.poll();
            if (visited[island.index])
                continue;
            visited[island.index] = true;
            distance += island.distance;
            for (Island land : route.get(island.index)) {
                pq.offer(land);
            }
        }
        return distance;
    }
}
