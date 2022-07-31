package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1119_그래프 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[][] graph = new String[N][N];
        for (int row = 0; row < N; row++) {
            String[] edges = br.readLine().split("");
            for (int col = 0; col < N; col++) {
                graph[row][col] = edges[col];
            }
        }

        int rst = getMinModifyRoad(graph);
        System.out.println(rst);

        br.close();
    }

    private static int getMinModifyRoad(String[][] graph) {
        // 다 이어지기 위해서 필요한 간선 개수
        int minEdge = N - 1;
        int edgeCount = (int) Arrays.stream(graph).flatMap(Arrays::stream)
                .filter(edge -> edge.equals("Y"))
                .count();

        // 최소 간선 개수보다 작은 경우 -1
        if (minEdge > edgeCount / 2) return -1;

        // 정점이 1개인 경우
        if (N == 1) return 0;

        // 연결된 그래프의 정점 개수 구하기
        boolean[] visited = new boolean[N];
        int graphCount = 0;
        for (int vertex = 0; vertex < N; vertex++) {
            vertexCount = 0;
            if (!visited[vertex]) {
                visited[vertex] = true;
                dfs(vertex, visited, graph);
                graphCount++;
            }

            // 간선 없이 정점만 있는 경우이므로 연결될 수 없다.
            if (vertexCount == 1) return -1;
        }

        // 분할된 그래프를 하나로 합치기 위해서는 분할된 그래프 개수 - 1번 합치기 진행
        return graphCount - 1;
    }

    private static int vertexCount;

    private static void dfs(int vertex, boolean[] visited, String[][] graph) {
        vertexCount++;
        for (int index = 0; index < N; index++) {
            if (!visited[index] && graph[vertex][index].equals("Y")) {
                visited[index] = true;
                dfs(index, visited, graph);
            }
        }
    }

}
