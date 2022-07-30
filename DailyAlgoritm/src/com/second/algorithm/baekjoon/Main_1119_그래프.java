package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1119_그래프 {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

        // 연결된 그래프의 개수와 Edge 구하기 BFS 및
        boolean[] visited = new boolean[N];
        for (int vertex = 0; vertex < N; vertex++) {
            if(!visited[vertex])
                bfs(vertex, visited, graph);
        }
    }
}
