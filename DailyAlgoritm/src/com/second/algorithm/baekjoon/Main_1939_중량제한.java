package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1939_중량제한 {

    private static int N, M, factoryA, factoryB;
    private static List<List<Bridge>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 섬 초기화
        graph = new ArrayList<>();
        for (int island = 0; island <= N; island++) {
            graph.add(new ArrayList<>());
        }


        // 다리 초기화
        long maxWeight = 0;
        for (int bridge = 0; bridge < M; bridge++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 다리의 최대 중량
            maxWeight = Math.max(maxWeight, weight);

            graph.get(start).add(new Bridge(end, weight));
            graph.get(end).add(new Bridge(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        factoryA = Integer.parseInt(st.nextToken());
        factoryB = Integer.parseInt(st.nextToken());

        long rst = getMaxWeight(maxWeight);

        System.out.println(rst);

        br.close();
    }

    private static long getMaxWeight(long maxWeight) {
        // 중량 범위 1 <= C <= 10억
        long min = 1;
        long max = maxWeight;

        // max 구하기
        while (min <= max) {
            long mid = (min + max) / 2;

            // 공장에 도달하는 경우 중량을 늘려본다.
            if (reachFactory(mid)) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return max;
    }

    private static boolean reachFactory(long weight) {
        boolean[] visited = new boolean[N+1];

        // 팩토리 A에서 출발 (중량이 무거운 순으로 정렬)
        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        pq.add(new Bridge(factoryA, 0));


        while (!pq.isEmpty()) {
            Bridge bridge = pq.poll();

            // 팩토리 B에 도달한 경우
            int island = bridge.getIsland();
            if (island == factoryB) return true;

            // 거리가 먼 순으로 넣기 때문에 만약 다음 노드를 방문한 상태인 경우
            if(visited[island]) continue;
            visited[island] = true;

            for (Bridge nextBridge : graph.get(island)) {
                // 다리가 버틸 수 있는 경우에만 큐에 넣어주기
                if(nextBridge.getWeight() >= weight)
                    pq.add(nextBridge);
            }
        }
        return false;
    }

    static class Bridge implements Comparable<Bridge> {
        private int island;
        private int weight;

        public Bridge(int island, int weight) {
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
        public int compareTo(Bridge o) {
            return Integer.compare(o.weight, this.weight);
        }
    }
}
