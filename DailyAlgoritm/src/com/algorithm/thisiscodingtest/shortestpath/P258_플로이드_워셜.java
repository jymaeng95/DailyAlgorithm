package com.algorithm.thisiscodingtest.shortestpath;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P258_플로이드_워셜 {
    private static int[][] graph;
    private static int V, E;
    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        graph = new int[V + 1][V + 1];
        for (int i = 1; i < V + 1; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from][to] = dist;
        }

        // 모든 노드에 대해서 반복
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 1; k <= V; k++) {
                    if (j == k) {
                        graph[j][k] = 0;
                        continue;
                    }
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++)
                bw.write(graph[i][j] + " ");
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
