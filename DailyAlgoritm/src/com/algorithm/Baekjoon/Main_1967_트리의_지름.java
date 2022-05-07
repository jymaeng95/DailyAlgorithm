package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1967_트리의_지름 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, List<Node>> tree = new HashMap<>();

        // 트리 초기화
        for (int node = 0; node <= N; node++) {
            tree.put(node, new ArrayList<>());
        }

        // 트리 연결
        for (int edge = 0; edge < N - 1; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree.get(parent).add(new Node(child, weight));
            tree.get(child).add(new Node(parent, weight));
        }

        int rst = getTreeLength(N, tree);
        br.close();
    }

    private static int length;

    private static int getTreeLength(int size, Map<Integer, List<Node>> tree) {
        length = 0;

        for (int node = 1; node <= size; node++) {
            boolean[] visited = new boolean[size + 1];
            dfs(node, tree, visited);
        }

        return length;
    }

    private static void dfs(int node, Map<Integer, List<Node>> tree, boolean[] visited) {
        // DFS 구현
    }

    static class Node {
        private int node;
        private int weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public int getNode() {
            return node;
        }

        public int getWeight() {
            return weight;
        }
    }
}
