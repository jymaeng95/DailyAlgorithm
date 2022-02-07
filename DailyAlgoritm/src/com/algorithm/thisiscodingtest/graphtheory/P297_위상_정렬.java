package com.algorithm.thisiscodingtest.graphtheory;

import java.io.*;
import java.util.*;

public class P297_위상_정렬 {
    private static List<List<Integer>> graph;
    private static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int[] indegree = new int[V + 1];
        int[] rst = new int[V];
        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);

            indegree[to]++;
        }

        /*
         * 위상정렬 flow : 1) 진입 차수 0인 edge를 큐에 넣어줌, 2)  큐에서 edge 빼면서 진입차수 줄여주고 삭제
         */

        topologySort(indegree, rst);
        for(int x :  rst) {
            bw.write(x+" ");
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static void topologySort(int[] indegree, int[] rst) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=V;i++) {
            if(indegree[i] == 0) queue.add(i);
        }
        int i = 0;
        while(!queue.isEmpty()) {
            int vertex = queue.poll();

            rst[i] = vertex;
            i++;
            for(int v : graph.get(vertex)) {
                indegree[v]--;
                if(indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }
    }
}
