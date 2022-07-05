package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1707_이분_그래프 {

    private static List<List<Integer>> graph;
    private static int[] colors;
    private static final int RED = 1;
    private static final int BLUE = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        for(int testCase = 0; testCase < K; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            colors = new int[V+1];
            // 그래프 초기화
            for(int vertex =0 ; vertex <= V; vertex++) {
                graph.add(new ArrayList<>());
            }

            // 그래프 연결
            for(int edge = 0; edge < E; edge++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            boolean rst = false;
            // 모든 정점에 대해서
            for(int vertex = 1; vertex <= V; vertex++) {
                if(colors[vertex] == 0) {
                    rst = isBipartiteGraph(vertex, RED);
                }
                if(!rst) break;
            }
            if(rst) System.out.println("YES");
            else System.out.println("NO");
        }
        br.close();
    }

    private static boolean isBipartiteGraph(int start, int color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        colors[start] = color;

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next : graph.get(cur)) {
                // 인접 정점 색이 동일하면 이분 그래프가 아님
                if(colors[cur] == colors[next]) return false;

                // 인접 정점 색칠 안된 경우 현재 정점 반대 색깔로 색칠
                if(colors[next] == 0) {
                    colors[next] = colors[cur] * -1;
                    queue.add(next);
                }
            }
        }
        return true;
    }
}
