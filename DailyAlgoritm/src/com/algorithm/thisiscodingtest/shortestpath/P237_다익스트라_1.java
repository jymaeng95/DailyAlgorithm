package com.algorithm.thisiscodingtest.shortestpath;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P237_다익스트라_1 {
    static class Node {
        private int vertex;
        private int dist;

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        public int getVertex() {
            return vertex;
        }

        public int getDist() {
            return dist;
        }

    }

    private static List<List<Node>> graph = new ArrayList<>();
    public static final int INF = (int) 1e9;
    private static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());   // vertex
        E = Integer.parseInt(st.nextToken());   // edge

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
        }

        boolean[] visited = new boolean[V + 1];
        int[] distance = new int[V + 1];

        Arrays.fill(distance, INF);
        dijkstra(start, distance, visited);

        for (int dist : distance) {
            if (dist == INF) {
                bw.write("INF");
                bw.newLine();
            } else {
                bw.write(String.valueOf(dist));
                bw.newLine();
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void dijkstra(int start, int[] distance, boolean[] visited) {
        distance[start] = 0;
        visited[start] = true;
        // 시작 노드와 연결된 노드 거리 업데이트
        for (Node node : graph.get(start)) {
            int vertex = node.getVertex();
            int dist = node.getDist();
            distance[vertex] = dist;
        }

        // 시작 노드 제외 반복
        for (int i = 0; i < V - 1; i++) {
            int node = getSmallestNode(distance, visited);
            visited[node] = true;
            for (Node nd : graph.get(node)) {
                int vertex = nd.getVertex();
                int dist = nd.getDist();
                int cost = distance[node] + dist;
                if (distance[vertex] > cost) {
                    distance[vertex] = cost;
                }
            }
        }
    }

    // 방문 하지 않은 노드 중 가장 거리가 가까운 노드
    private static int getSmallestNode(int[] distance, boolean[] visited) {
        int min = INF;
        int idx = 0;
        for (int i = 1; i <= V; i++) {
            if (distance[i] < min && !visited[i]) {
                min = distance[i];
                idx = i;
            }
        }
        return idx;
    }
}
