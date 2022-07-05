package com.algorithm.Baekjoon.first;

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
        System.out.println(rst);
        br.close();
    }


    private static int getTreeLength(int size, Map<Integer, List<Node>> tree) {
        startNode = maxStartLength = 0;
        boolean[] visited = new boolean[size + 1];

        // 루트 노드부터 가장 먼 노드 찾기
        getMaxLength(1, visited, tree,  0);

        // 가장 먼 노드로 다시 DFS
        visited = new boolean[size + 1];
        maxStartLength = 0;     // 최장 지름 길이 초기화
        getMaxLength(startNode, visited, tree, 0);
        return maxStartLength;
    }


    private static int startNode, maxStartLength;
    private static void getMaxLength(int node, boolean[] visited, Map<Integer, List<Node>> tree, int weight) {
        visited[node] = true;
        for (Node next : tree.get(node)) {
            if (!visited[next.getNode()]) {
                if (next.getWeight() + weight > maxStartLength) {
                    startNode = next.getNode();
                    maxStartLength = next.getWeight() + weight;
                }
                getMaxLength(next.getNode(), visited, tree, weight + next.getWeight());
            }
        }
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
