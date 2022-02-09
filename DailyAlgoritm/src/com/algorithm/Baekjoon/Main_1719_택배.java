package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1719_택배 {
    static class Delivery implements  Comparable<Delivery>{
        private int dest;
        private int time;

        public Delivery(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }

        public int getDest() {
            return dest;
        }

        public int getTime() {
            return time;
        }

        @Override
        public int compareTo(Delivery o) {
            return Integer.compare(this.time, o.time);
        }
    }
    private static int N,M;
    private static final int INF = 1001;
    private static List<List<Delivery>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }

        String [][] point = new String[N+1][N+1];

        for(int i=1;i<=N;i++) {
            Arrays.fill(point[i], "-");
        }
        for(int i =0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph.get(fr).add(new Delivery(to, time));
            graph.get(to).add(new Delivery(fr, time));
        }
        dijkstra(point);
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                System.out.print(point[i][j]+ " ");
            }
            System.out.println();
        }
        br.close();
    }

    private static void dijkstra(String[][] point) {
        for(int i=1;i<=N;i++) {
            int[] dist = new int[N+1];
            boolean[] visited = new boolean[N+1];
            int[] parent = new int[N+1];
            for(int j=1;j<=N;j++) {
                parent[j] = j;
            }
            Arrays.fill(dist, INF);
            PriorityQueue<Delivery> pq = new PriorityQueue<>();
            pq.add(new Delivery(i,0));
            dist[i] = 0;
            while(!pq.isEmpty()) {
                Delivery dv = pq.poll();
                int city = dv.getDest();
                if(visited[city]) continue;
                visited[city] = true;
                for(Delivery delivery: graph.get(city)) {
                    int dest = delivery.getDest();
                    int destTime = delivery.getTime();
                    if(!visited[dest] && dist[dest] > dist[city]+ destTime) {
                        dist[dest] = dist[city] + destTime;
                        pq.add(new Delivery(dest,dist[dest]));
                        parent[dest] = city;
                    }
                }
            }
            for(int j=1;j<=N;j++) {
                if(i == j) continue;
                makePoint(i,j, point, parent, j);
            }
        }
    }

    private static void makePoint(int start, int next, String[][] point, int[] parent, int end) {
        if(parent[next] == start) {
            point[start][end] = String.valueOf(next);
            return;
        }
        makePoint(start, parent[next], point, parent, end);

    }
}
