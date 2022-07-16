package com.second.algorithm.programmers;

import java.util.*;

public class Solution_49189_가장_먼_노드 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        int rst = solution(n, edge);
        System.out.println(rst);
    }

    static class Node {
        private int vertex;
        private int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public int getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }
    }

    private static List<List<Integer>> graph;
    private static int solution(int n, int[][] edge) {
        // 그래프 초기화
        graph = new ArrayList<>();
        for (int vertex = 0; vertex <= n; vertex++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 연결
        for (int[] info : edge) {
            int start = info[0];
            int end = info[1];

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // 1번 정점시작 노드간 거리구하기
        int[] distance = searchDistance(n);

        // 최대값 구하기
        int max = Arrays.stream(distance).max().getAsInt();

        // 최대값 개수 리턴
        return (int)Arrays.stream(distance)
                .filter(dist -> dist == max)
                .count();
    }

    private static int[] searchDistance(int n) {
        final int INIT = -1;
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INIT);

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int curVertex = node.getVertex();
            int curDistance = node.getDistance();

            if(distance[curVertex] > INIT) continue;
            distance[curVertex] = curDistance;

            // 다음 노드 연결
            for (Integer nextVertex : graph.get(curVertex)) {
                // 거리 갱신 안된 경우만
                if(distance[nextVertex] == INIT) {
                    queue.add(new Node(nextVertex, curDistance + 1));
                }
            }
        }
        return distance;
    }
}
