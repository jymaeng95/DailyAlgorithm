package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1865_웜홀 {
    static class City {
        private int end;
        private int dist;

        public City(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        public int getEnd() {
            return end;
        }

        public int getDist() {
            return dist;
        }
    }

    private static int V, E, W;
    private static int[] distance;
    private static final int INF = (int) 1e9;
    private static List<List<City>> cityMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int t = 0; t < TC; t++) {
            cityMap = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            for (int i = 0; i <= V; i++) {
                cityMap.add(new ArrayList<>());
            }

            for (int i = 0; i < E + W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                if (i < E) {
                    cityMap.get(start).add(new City(end, dist));
                    cityMap.get(end).add(new City(start, dist));
                } else {
                    cityMap.get(start).add(new City(end, -dist));
                }
            }

            distance = new int[V + 1];

            if (getWormhole()) System.out.println("YES");
            else System.out.println("NO");
        }

        br.close();
    }

    private static boolean getWormhole() {
        Arrays.fill(distance, INF);
        distance[1] = 0;
        for (int i = 1; i < V; i++) {
            // 모든 Edge
            for (int j = 1; j <= V; j++) {
                for (City city : cityMap.get(j)) {
                    int endCity = city.getEnd();
                    int dist = city.getDist();
                    if (distance[endCity] > distance[j] + dist) {
                        distance[endCity] = distance[j] + dist;
                    }
                }
            }
        }

        // Edge 개수
        for (int i = 1; i <= V; i++) {
            for (City city : cityMap.get(i)) {
                int end = city.getEnd();
                int dist = city.getDist();
                if (distance[end] > distance[i] + dist) {
                    return true;
                }
            }
        }
        return false;
    }
}
