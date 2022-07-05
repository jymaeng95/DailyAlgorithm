package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.*;

public class Main_18352_특정_거리의_도시_찾기 {
    private static List<List<Integer>> graph;
    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());       // 도시 (vertex)
        int M = Integer.parseInt(st.nextToken());       // 도로 (edge)
        int K = Integer.parseInt(st.nextToken());       // 도달 거리
        int X = Integer.parseInt(st.nextToken());       // 시작 도시

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int vertex = 0; vertex <= N; vertex++) {
            graph.add(new ArrayList<>());
        }

        // 도로 연결
        for (int edge = 0; edge < M; edge++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
        }

        int[] rst = getMinDistanceCity(N, K, X);

        // BufferedWriter 안쓰면 출력 초과 에러
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int city : rst) {
            bw.write(String.valueOf(city));
            bw.newLine();
        }
        bw.flush();
        br.close();
    }

    private static int[] getMinDistanceCity(int n, int k, int x) {
        Queue<Integer> pq = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);

        pq.add(x);
        distance[x] = 0;        // 시작도시 거리 0
        visited[x] = true;      // 시작도시 방문 처리

        while (!pq.isEmpty()) {
            int city = pq.poll();
            for (int next : graph.get(city)) {
                // 거리 비교해서 최단 거리인 경우 갱신
                if (!visited[next] && distance[next] > distance[city] + 1) {
                    visited[next] = true;
                    distance[next] = distance[city] + 1;
                    pq.add(next);
                }
            }
        }

        // X 도시에서 도달할 수 있는 최단 거리 중 K인 도시 구하기
        List<Integer> rst = new ArrayList<>();
        for (int city = 1; city <= n; city++) {
            if (distance[city] == k) rst.add(city);
        }

        // K인 도시 없으면 return 0
        if (rst.size() < 1) return new int[]{-1};

        // 도시 오름차순 정렬 후 리턴
        Collections.sort(rst);
        return rst.stream().mapToInt(Integer::intValue).toArray();
    }
}
