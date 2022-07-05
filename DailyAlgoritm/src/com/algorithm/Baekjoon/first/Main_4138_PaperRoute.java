package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_4138_PaperRoute {
    static class Address implements Comparable<Address> {
        private int start;
        private int end;
        private int dist;

        public Address(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getDist() {
            return dist;
        }

        @Override
        public int compareTo(Address o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    private static List<Address> road;
    private static List<List<Address>> graph;
    private static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] campus = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            campus[i] = Integer.parseInt(br.readLine());
        }
        int[] parent = new int[N+1];
        graph = new ArrayList<>();
        for(int i=0;i<N+1;i++) {
            parent[i] = i;
            graph.add(new ArrayList<>());
        }

        road = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            road.add(new Address(fr, to, dist));
        }

        int rst = getMinDistance(N, parent, campus);
        System.out.println(rst);
        br.close();
    }

    private static int getMinDistance(int n, int[] parent, int[] campus) {
        int minDistance = Integer.MAX_VALUE;
        int cost = 0;
        dist = new int[n+1];
        Collections.sort(road);
        for(Address address : road) {
            int start = address.getStart();
            int end = address.getEnd();
            int distance = address.getDist();
            if(find(start, parent) != find(end, parent)) {
                union(start, end, parent);
                graph.get(start).add(new Address(start, end, distance));
                cost += distance;
            }
        }
        boolean[] visited = new boolean[n+1];
        getDistanceFromZero(0,0,visited);

        // 코스트 * 2 - 0부터 거리 + 캠퍼스 거리
        for(int i=0;i<n+1;i++) {

            minDistance = Math.min(minDistance, cost * 2 - dist[i] + campus[i]);
        }
        return minDistance;
    }

    private static void getDistanceFromZero(int x, int prevDist, boolean[] visited) {
        visited[x] = true;
        for(Address address :  graph.get(x)) {
            int end = address.getEnd();
            int distance = address.getDist();
            if(!visited[end]) {
                dist[end] = prevDist + distance;
                getDistanceFromZero(end, dist[end], visited);
            }
        }
    }

    private static void union(int start, int end, int[] parent) {
        int a = find(start, parent);
        int b = find(end, parent);
        if(a>b) parent[b] = a;
        else parent[a] = b;
    }

    private static int find(int x, int[] parent) {
        if(parent[x] != x)
            parent[x] = find(parent[x], parent);
        return parent[x];
    }
}