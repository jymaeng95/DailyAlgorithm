package com.second.algorithm.programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_42861_섬_연결하기 {
    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        int rst = solution(n, costs);

        System.out.println("rst = " + rst);
    }

    private static List<List<Node>> graph;
    private static int solution(int n, int[][] costs) {
        // 어떤 노드부터 시작하더라도 제일 비용이 낮은 edge를 선택해야한다.
        graph = new ArrayList<>();

        initGraph(n, costs);
        return makeMST(n, costs);
    }

    private static int makeMST(int n, int[][] costs) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        pq.add(new Node(0, 0));

        int totalCost = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int vertex = node.getDest();
            int cost = node.getCost();

            if (visited[vertex]) continue;

            // 현재 정점 방문 안한 경우에만 총 cost 증가
            visited[vertex] = true;
            totalCost += cost;
            for (Node nextNode : graph.get(vertex)) {
                if (!visited[nextNode.getDest()]) {
                    pq.add(nextNode);
                }
            }
        }
        return totalCost;
    }

    private static void initGraph(int n, int[][] costs) {
        // 그래프 정점 초기화
        for (int node = 0; node < n; node++) {
            graph.add(new ArrayList<>());
        }

        // 간선 초기화
        for (int[] cost : costs) {
            int start = cost[0];
            int end = cost[1];
            int value = cost[2];

            graph.get(start).add(new Node(end, value));
            graph.get(end).add(new Node(start, value));
        }
    }

    static class Node implements Comparable<Node> {
        private int dest;
        private int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        public int getDest() {
            return dest;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

}
