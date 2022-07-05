package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1939_중량제한 {
    private static List<List<Island>> bridges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        bridges = new ArrayList<>();
        for (int island = 0; island <= N; island++) {
            bridges.add(new ArrayList<>());
        }

        for (int bridge = 0; bridge < M; bridge++) {
            st = new StringTokenizer(br.readLine());
            int islandA = Integer.parseInt(st.nextToken());
            int islandB = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            bridges.get(islandA).add(new Island(islandB, weight));
            bridges.get(islandB).add(new Island(islandA, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int rst = getMaxWeight(start, end, N, M);
        System.out.println(rst);

        br.close();
    }

    private static int getMaxWeight(int start, int end, int n, int m) {
        PriorityQueue<Island> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        pq.add(new Island(start, 0));
        int maxWeight = 0;

        while (!pq.isEmpty()) {
            Island islandInfo = pq.poll();
            int island = islandInfo.getIsland();
            int weight = islandInfo.getWeight();
            if (island == end) {
                maxWeight = weight;
                break;
            }
            if (visited[island]) continue;
            visited[island] = true;
            for (Island nextIsland : bridges.get(island)) {
                // 현재 중량과 다음 중량 비교해서 현재 중량이 작은 경우 현재 중량으로 중량 갱신
                if(!visited[nextIsland.getIsland()]) {
                    if (island != start && weight < nextIsland.getWeight()) {
                        pq.add(new Island(nextIsland.getIsland(), weight));
                    } else {
                        pq.add(nextIsland);
                    }
                }
            }
        }
        return maxWeight;
    }

    static class Island implements Comparable<Island> {
        private int island;
        private int weight;

        public Island(int island, int weight) {
            this.island = island;
            this.weight = weight;
        }

        public int getIsland() {
            return island;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Island o) {
            return Integer.compare(o.weight, this.weight);
        }
    }
}
