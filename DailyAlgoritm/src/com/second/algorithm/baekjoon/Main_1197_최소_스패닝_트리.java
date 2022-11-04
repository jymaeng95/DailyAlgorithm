package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197_최소_스패닝_트리 {
    private static List<List<Vertex>> graph;
    private static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

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
            graph.get(to).add(new Vertex(from, weight));
        }

        int rst = getMst();
        System.out.println(rst);

        br.close();
    }

    private static int getMst() {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(1, 0));

        boolean[] check = new boolean[V + 1];

        int sumWeight = 0;
        while (!pq.isEmpty()) {
            Vertex vertex = pq.poll();

            if (check[vertex.getVertex()]) continue;
            check[vertex.getVertex()] = true;
            sumWeight += vertex.getWeight();

            for (Vertex nextVertex : graph.get(vertex.getVertex())) {
                if (!check[nextVertex.getVertex()])
                    pq.add(nextVertex);
            }
        }

        return sumWeight;
    }

    static class Vertex implements Comparable<Vertex> {
        private final int vertex;
        private final int weight;

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
