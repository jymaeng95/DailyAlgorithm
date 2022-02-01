package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1865_웜홀 {
    static class City implements Comparable<City> {
        private int city;
        private int dist;

        public City(int city, int dist) {
            this.city = city;
            this.dist = dist;
        }

        public int getCity() {
            return city;
        }

        public int getDist() {
            return dist;
        }

        @Override
        public int compareTo(City o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    private static int V, E, W;
    private static boolean[] visited;
    private static int[] distance;
    private static List<List<City>> graph;
    private static int[][] cityDistance;
    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            graph = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            cityDistance = new int[V + 1][V + 1];
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
                Arrays.fill(cityDistance[i], INF);
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                cityDistance[start][end] = dist;
            }
            getCityDistance();

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                graph.get(start).add(new City(end, dist));
            }

            boolean possible = false;
            for (int i = 1; i <= V; i++) {
                visited = new boolean[V + 1];
                distance = new int[V + 1];
                Arrays.fill(distance, INF);
                getWormhole(i);

                possible = checkPossible(i);
                if(possible) break;
            }
            if(possible) System.out.println("YES");
            else System.out.println("NO");
        }

        br.close();
    }

    private static boolean checkPossible(int start) {
        for(int i =1;i<=V;i++) {
            if(distance[i] != INF && cityDistance[start][i] - distance[i] < 0) return true;
        }
        return false;
    }

    private static void getWormhole(int start) {
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.offer(new City(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()) {
            City city = pq.poll();
            int end = city.getCity();
            if(visited[end]) continue;;
            visited[end] = true;
            for(City ct : graph.get(end)) {
                if(!visited[ct.getCity()] && distance[ct.getCity()] > distance[end] + ct.getDist()) {
                    distance[ct.getCity()] = distance[end] + ct.getDist();
                    pq.add(ct);
                }
            }
        }

    }

    private static void getCityDistance() {
        for (int i = 1; i < V + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                for (int k = 1; k < V + 1; k++) {
                    if (j == k) {
                        cityDistance[j][k] = 0;
                        continue;
                    }
                    cityDistance[j][k] = Math.min(cityDistance[j][k], cityDistance[j][i] + cityDistance[i][k]);
                    cityDistance[k][j] = cityDistance[j][k];
                }
            }
        }
    }
}
