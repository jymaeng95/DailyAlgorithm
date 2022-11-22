package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_132266_부대복귀 {
    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}};
        int[] sources = {1, 3, 5};
        int destination = 5;

        int[] rst = solution(n, roads, sources, destination);
        Arrays.stream(rst).forEach(System.out::println);
    }

    private static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> graph = initGraph(n, roads);
        int[] distance = comeBack(n, destination, graph);

        int[] move = new int[sources.length];
        for (int index = 0; index < sources.length; index++) {
            if(distance[sources[index]]== (int) 1e9) move[index] = -1;
            else move[index] = distance[sources[index]];
        }

        return move;
    }

    private static int[] comeBack(int n, int destination, List<List<Integer>> graph) {
        PriorityQueue<Soldier> pq = new PriorityQueue<>();
        pq.add(new Soldier(destination, 0));

        int[] distance = new int[n + 1];
        Arrays.fill(distance, (int) 1e9);
        distance[destination] = 0;

        while (!pq.isEmpty()) {
            Soldier soldier = pq.poll();
            int army = soldier.getArmy();
            int dist = soldier.getDistance();

            for (Integer nextArmy : graph.get(army)) {
                if (distance[nextArmy] > dist + 1) {
                    distance[nextArmy] = dist + 1;
                    pq.add(new Soldier(nextArmy, dist + 1));
                }
            }
        }

        return distance;
    }

    private static List<List<Integer>> initGraph(int n, int[][] roads) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int army = 0; army <= n; army++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int departure = road[0];
            int destination = road[1];

            graph.get(departure).add(destination);
            graph.get(destination).add(departure);
        }

        return graph;
    }

    private static class Soldier implements Comparable<Soldier>{
        private final int army;
        private final int distance;

        public Soldier(int army, int distance) {
            this.army = army;
            this.distance = distance;
        }

        public int getArmy() {
            return army;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Soldier o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
