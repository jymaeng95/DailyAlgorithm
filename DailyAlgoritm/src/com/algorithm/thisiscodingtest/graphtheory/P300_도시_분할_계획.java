package com.algorithm.thisiscodingtest.graphtheory;

import java.io.*;
import java.util.*;

public class P300_도시_분할_계획 {
    static class Road {
        private int villageA;
        private int villageB;
        private int dist;

        public Road(int villageA, int villageB, int dist) {
            this.villageA = villageA;
            this.villageB = villageB;
            this.dist = dist;
        }

        public int getVillageA() {
            return villageA;
        }

        public int getVillageB() {
            return villageB;
        }

        public int getDist() {
            return dist;
        }
    }
    private static int V, E;
    private static List<Road> roadList;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int[] parent = new int[V+1];
        roadList = new ArrayList<>();
        for(int i =1;i<=V;i++) {
            parent[i] = i;
        }

        for(int i =0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int villageA = Integer.parseInt(st.nextToken());
            int villageB = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roadList.add(new Road(villageA, villageB, dist));
        }

        roadList.sort(Comparator.comparingInt(o -> o.dist));

        int cost = planningCityDivide(parent);
        bw.write(String.valueOf(cost));
        br.close();
        bw.close();
    }

    private static int planningCityDivide(int[] parent) {
        int cost = 0;
        int maxRoadDist = 0;
        for(Road road : roadList) {
            int villageA = road.getVillageA();
            int villageB = road.getVillageB();
            int dist = road.getDist();
            if(find(villageA, parent) != find(villageB, parent)) {
                union(villageA, villageB, parent);
                maxRoadDist = Math.max(maxRoadDist, dist);
                cost += dist;
            }
        }
        return cost - maxRoadDist;
    }

    private static void union(int villageA, int villageB, int[] parent) {
        villageA = find(villageA, parent);
        villageB = find(villageB, parent);

        if(villageA > villageB) parent[villageA] = villageB;
        else parent[villageB] = villageA;
    }

    private static int find(int village, int[] parent) {
        if(parent[village] != village)
            parent[village] = find(parent[village], parent);
        return parent[village];
    }
}
