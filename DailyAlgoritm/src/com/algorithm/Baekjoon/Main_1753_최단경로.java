package com.algorithm.Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1753_최단경로 {
    static class Node implements Comparable<Node> {
        private int node;
        private int dist;

        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    private static int V, E, startVertex;
    private static ArrayList<ArrayList<Node>> graph;
    private static boolean[] visited;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        startVertex = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, dist));
        }
        visited = new boolean[V + 1];
        distance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            if (i == startVertex)
                continue;
            distance[i] = Integer.MAX_VALUE;
        }
        dijkstra(startVertex);
        for (int i = 1; i <= V; i++) {

            if (distance[i] == Integer.MAX_VALUE) {
                bw.write("INF");
                continue;
            }
            bw.write(String.valueOf(distance[i]));
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    private static void dijkstra(int startVertex) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startVertex, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.node])
                continue;
            visited[node.node] = true;
            for (Node nd : graph.get(node.node)) {
                if (!visited[nd.node] && distance[nd.node] > distance[node.node] + nd.dist) {
                    distance[nd.node] = distance[node.node] + nd.dist;
                    pq.offer(new Node(nd.node, distance[nd.node]));
                }
            }
        }
    }
}
