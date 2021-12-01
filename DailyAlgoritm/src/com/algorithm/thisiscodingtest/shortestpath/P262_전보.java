package com.algorithm.thisiscodingtest.shortestpath;

import java.io.*;
import java.util.*;

public class P262_전보 {
    static class Node implements Comparable<Node>{
        private int vertex;
        private int dist;

        public int getVertex() {
            return vertex;
        }

        public int getDist() {
            return dist;
        }

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    private static List<List<Node>> graph;
    private static int N,M,C;
    private static boolean[] visited;
    private static int[] distance;
    private static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N : 도시 개수 , M : 통로 개수 , C : 출발 도시
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to,dist));
        }

        visited = new boolean[N+1];
        distance = new int[N+1];
        Arrays.fill(distance,INF);

        int[] rst = sendWarningMessage();
        bw.write(rst[0] + " ");
        bw.write(String.valueOf(rst[1]));
        br.close();
        bw.close();
    }

    private static int[] sendWarningMessage() {
        int[] rst = new int[2];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance[C] = 0;
        pq.add(new Node(C,0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int city = node.getVertex();
            int dist = node.getDist();
            if(visited[city])
                continue;
            visited[city] = true;
            for(Node nd : graph.get(city)) {
                if(distance[nd.getVertex()] > nd.getDist() + dist ) {
                    distance[nd.getVertex()] = nd.getDist() + dist;
                }
            }
        }

        int possible = 0;
        int totalTime = 0;
        for(int i=1;i<=N;i++){
            if(distance[i] != INF){
                possible++;
                totalTime = Math.max(totalTime, distance[i]);
            }
        }

        rst[0] = possible -1;
        rst[1] = totalTime;
        return rst;
    }
}
