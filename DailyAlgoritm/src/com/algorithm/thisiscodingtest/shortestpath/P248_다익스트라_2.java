package com.algorithm.thisiscodingtest.shortestpath;

import java.io.*;
import java.util.*;

public class P248_다익스트라_2 {
    static class Node implements Comparable<Node> {
        private int vertex;
        private int dist;

        public int getVertex() {
            return vertex;
        }

        public int getDist() {
            return dist;
        }

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    private static boolean[] visited;
    private static int[] distance;
    private static List<List<Node>> graph;
    private static int V, E;
    private static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 만들기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
        }

        visited = new boolean[V + 1];
        distance = new int[V + 1];
        Arrays.fill(distance, INF);

        dijkstra(start);

        for(int i=1; i<distance.length; i++){
            if(distance[i] == INF)
                bw.write("INF");
            else bw.write(String.valueOf(distance[i]));
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int vertex = node.getVertex();
            if(visited[vertex])
                continue;
            visited[vertex] = true;
            for(Node nd : graph.get(vertex)) {
                if(!visited[nd.vertex] && distance[nd.vertex] > distance[vertex] + nd.dist) {
                    distance[nd.vertex] = distance[vertex] + nd.dist;
                    pq.offer(nd);
                }
            }
        }
    }
}
