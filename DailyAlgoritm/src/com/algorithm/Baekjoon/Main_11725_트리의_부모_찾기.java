package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11725_트리의_부모_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<Integer, List<Integer>> tree = new HashMap<>();

        // 트리 초기화
        for(int node = 1; node <= N; node++) {
            tree.put(node, new ArrayList<>());
        }

        // 트리에 간선 정보 저장
        for(int edge = 0; edge < N-1; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        int[] rst = findParent(N, tree);
        for(int node = 2; node <= N; node++) {
            System.out.println(rst[node]);
        }

        br.close();
    }

    private static int[] findParent(int n, Map<Integer, List<Integer>> tree) {
        /**
         * 1. 루트가 1을 기준으로 잡았으므로 1부터 DFS 탐색
         * 2. parents 배열 : child의 부모를 저장하는 배열
         * 3. DFS탐색하면서 현재 노드와 연결된 자식노드의 부모는 현재 노드이므로 parent[child] = parent
         */

        int[] parents = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, tree, visited, parents);

        return parents;
    }

    private static void dfs(int parent, Map<Integer, List<Integer>> tree, boolean[] visited, int[] parents) {
        visited[parent] = true;
        for (Integer child : tree.get(parent)) {
            if(!visited[child]){
                parents[child] = parent;
                dfs(child, tree, visited, parents);
            }
        }
    }
}
