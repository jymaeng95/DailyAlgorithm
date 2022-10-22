package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1753_최단경로 {
    private static int V, E;
    private static List<List<Vertex>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int vertex = 0; vertex <= V; vertex++) {
            graph.add(new ArrayList<>());
        }

        for (int edge = 0; edge < E; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Vertex(to, weight));
        }

        int[] rst = getShortenPath(start);
        for (int index = 1; index <= V; index++) {
            if(rst[index] == INF) System.out.println("INF");
            else System.out.println(rst[index]);
        }

        br.close();
    }

    private static final int INF = 200_001;
    private static int[] getShortenPath(int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        int[] distances = new int[V + 1];
        Arrays.fill(distances, 200_001);

        distances[start] = 0;
        pq.add(new Vertex(start, 0));

        while (!pq.isEmpty()) {
            Vertex vertex = pq.poll();
            int node = vertex.getVertex();

            for (Vertex next : graph.get(node)) {
                int nextNode = next.getVertex();
                int nextWeight = next.getWeight();
                if (distances[nextNode] > nextWeight + distances[node]) {
                    distances[nextNode] = nextWeight + distances[node];
                    pq.add(new Vertex(nextNode, distances[nextNode]));
                }
            }
        }

        return distances;
    }

    static class Vertex implements Comparable<Vertex> {
        private int vertex;
        private int weight;

        public Vertex(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int getVertex() {
            return vertex;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
