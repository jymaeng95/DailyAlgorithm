package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1167_트리의_지름 {
    static class Node {
        int vertex;
        int weight;

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

    }
    private static int maxWeight, maxVertex;
    private static int V;
    private static List<List<Node>> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();

        for (int vertex = 0; vertex <= V; vertex++) {
            tree.add(new ArrayList<>());
        }

        for (int vertex = 1; vertex <= V; vertex++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                tree.get(fr).add(new Node(to, weight));
                tree.get(to).add(new Node(fr, weight));
            }
        }

        int rst = getTreeLength();
        System.out.println(rst);

        br.close();
    }
    private static int getTreeLength() {
        maxWeight = Integer.MIN_VALUE;
        maxVertex = 1;

        boolean[] visited = new boolean[V+1];
        dfs(1,0, visited);

        visited = new boolean[V+1];
        dfs(maxVertex, 0, visited);

        return maxWeight;
    }
    private static void dfs(int start, int weight, boolean[] visited) {
        if(maxWeight < weight) {
            maxWeight = weight;
            maxVertex = start;
        }

        visited[start] = true;
        for(Node node : tree.get(start)) {
            if(!visited[node.getVertex()]) {
                dfs(node.getVertex(), node.getWeight() + weight, visited);
            }
        }
    }
}
