package com.algorithm.thisiscodingtest.BFS;

import java.io.*;
import java.util.*;

public class P339_Q15_특정_거리의_도시_찾기 {
    private static  List<List<Integer>> road;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] dist = new int[N + 1];
        Arrays.fill(dist, 300001);
        road = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            road.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            road.get(fr).add(to);
        }

        int[] rst = getMinDistanceCity(K, X, dist,visited);
        for (int r : rst) {
            bw.write(String.valueOf(r));
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static int[] getMinDistanceCity(int k, int x, int[] dist, boolean[] visited) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(x);
        dist[x] = 0;
        while (!pq.isEmpty()) {
            int city = pq.poll();
            if(visited[city]) continue;
            visited[city] = true;
            for (int next : road.get(city)) {
                if (!visited[next] && dist[next] > dist[city] + 1) {
                    dist[next] = dist[city] + 1;
                    pq.offer(next);
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == k)
                list.add(i);
        }
        if (list.isEmpty())
            list.add(-1);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
