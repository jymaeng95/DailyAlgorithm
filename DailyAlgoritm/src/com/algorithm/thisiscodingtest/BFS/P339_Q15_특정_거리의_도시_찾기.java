package com.algorithm.thisiscodingtest.BFS;

import java.io.*;
import java.util.*;

public class P339_Q15_특정_거리의_도시_찾기 {
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
        List<List<Integer>> road = new ArrayList<>();
//        int[][] roads = new int[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            road.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

//            roads[fr][to] = 1;
            road.get(fr).add(to);
        }

        int[] rst = getMinDistanceCity(K, X, dist,road);
//        int[] rst2 = getMinDist(K,X,dist,roads);
        for (int r : rst) {
            bw.write(String.valueOf(r));
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    private static int[] getMinDist(int k, int x, int[] dist, int[][] roads) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        dist[x] = 0;
        while(!queue.isEmpty()) {
            int city = queue.poll();
            for(int i=1;i<roads.length;i++) {
                if(roads[city][i] == 1) {
                    if (dist[i] > dist[city] + 1) {
                        dist[i] = dist[city] + 1;
                    }

                    queue.offer(i);
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

    private static int[] getMinDistanceCity(int k, int x, int[] dist, List<List<Integer>> road) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        dist[x] = 0;
        while (!queue.isEmpty()) {
            int city = queue.poll();
            for (int next : road.get(city)) {
                if (dist[next] > dist[city] + 1) {
                    dist[next] = dist[city] + 1;
                }
                queue.offer(next);
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
