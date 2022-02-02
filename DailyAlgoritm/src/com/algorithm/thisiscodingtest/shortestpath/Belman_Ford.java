package com.algorithm.thisiscodingtest.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Belman_Ford {
    static class City {
        private int start;
        private int end;
        private int dist;

        public City(int start, int end, int dist) {
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
    }

    private static int V, W;
    private static List<City> wormhole;
    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        wormhole = new ArrayList<>();
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            wormhole.add(new City(start, end, dist));
        }
        int[] distance = new int[V + 1];
        Arrays.fill(distance, INF);
        boolean test = test(distance, 1);
        for (int x : distance)
            System.out.print(x + " ");
        System.out.println(test);
        br.close();
    }

    private static boolean test(int[] distance, int start) {
        distance[start] = 0;
        for (int i = 1; i <= V - 1; i++) {
            for (int j = 0; j < W; j++) {
                int startCity = wormhole.get(j).getStart();
                int endCity = wormhole.get(j).getEnd();
                int dist = wormhole.get(j).getDist();
                if (distance[startCity] == INF) continue;
                if (distance[endCity] > distance[startCity] + dist) {
                    distance[endCity] = distance[startCity] + dist;

                }
            }
        }

        for (int i = 0; i < W; i++) {
            int startCity = wormhole.get(i).getStart();
            int endCity = wormhole.get(i).getEnd();
            int dist = wormhole.get(i).getDist();
            if (distance[startCity] != INF && distance[endCity] > distance[startCity] + dist)
                return true;
        }
        return false;

    }
}

