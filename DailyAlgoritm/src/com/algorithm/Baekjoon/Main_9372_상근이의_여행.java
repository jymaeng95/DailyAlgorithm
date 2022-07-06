package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_9372_상근이의_여행 {
    private static List<List<Integer>> graph;
    private static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        for(int loop = 0; loop < testCase ; loop++) {
            graph = new ArrayList<>();
            count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for(int vertex = 0; vertex <= N; vertex++) {
                graph.add(new ArrayList<>());
            }

            for(int edge = 0; edge < M; edge++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }


            boolean[] visited = new boolean[N + 1];
            dfs(1, graph,visited);
            System.out.println(count);
        }

        br.close();
    }

    private static void dfs(int vertex, List<List<Integer>> graph, boolean[] visited) {
        visited[vertex] = true;
        for (int next : graph.get(vertex)) {
            if(!visited[next]) {
                count++;
                dfs(next, graph, visited);
            }
        }
    }
}
