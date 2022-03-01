package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197_최소신장트리 {
    static class Node implements Comparable<Node> {
        private int vertex;
        private int weight ;

        public Node(int vertex, int weight) {
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
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    private static int V,E;
    private static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for(int vertex = 0; vertex <= V; vertex++) {
            graph.add(new ArrayList<>());
        }
        for(int edge = 0; edge < E; edge++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(fr).add(new Node(to,weight));
            graph.get(to).add(new Node(fr,weight));
        }

        int rst = getMSTWegith();
        System.out.println(rst);

        br.close();
    }

    private static int getMSTWegith() {
        boolean[] visited = new boolean[V+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int weight = 0;
        pq.add(new Node(1,0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.getVertex();

            if(visited[cur]) continue;;
            visited[cur] = true;
            weight += node.getWeight();
            for(Node next : graph.get(cur)) {
                if(!visited[next.getVertex()])
                    pq.add(new Node(next.getVertex(), next.getWeight()));
            }
        }

        return weight;
    }
}
