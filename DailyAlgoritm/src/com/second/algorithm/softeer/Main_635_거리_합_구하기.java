package com.second.algorithm.softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_635_거리_합_구하기 {

    private static Map<Integer, List<Node>> tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        tree = new HashMap<>();
        for (int vertex = 0; vertex <= N; vertex++) {
            tree.put(vertex, new ArrayList<>());
        }

        for (int edge = 0; edge < N - 1; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            tree.get(start).add(new Node(end, distance));
            tree.get(end).add(new Node(start, distance));
        }

        long[] rst = getMinDistance(N);
        Arrays.stream(rst).forEach(System.out::println);
        br.close();
    }

    private static long[] getMinDistance(int n) {

        long[] rst = new long[n];
        for (int vertex = 1; vertex <= n; vertex++) {
            boolean[] visited = new boolean[n + 1];

            rst[vertex-1] = dijkstra(vertex, visited, n);
        }

        return rst;
    }

    private static long dijkstra(int vertex, boolean[] visited, int n) {
        long[] distance = new long[n + 1];
        visited[vertex] = true;
        Arrays.fill(distance, (long) 1e12);
        distance[0] = distance[vertex] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(vertex, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int curVertex = node.getNode();
            long curDistance = node.getDistance();

            for (Node next : tree.get(curVertex)) {
                if(!visited[next.getNode()] && distance[next.getNode()] > distance[curVertex] + next.getDistance()) {
                    visited[next.getNode()] = true;
                    distance[next.getNode()] = distance[curVertex] + next.getDistance();
                    pq.add(next);
                }
            }
        }

        return Arrays.stream(distance).sum();
    }

    static class Node implements Comparable<Node> {
        private int node;
        private long distance;

        public Node(int node, long distance) {
            this.node = node;
            this.distance = distance;
        }

        public int getNode() {
            return node;
        }

        public long getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.distance, o.distance);
        }
    }
}
