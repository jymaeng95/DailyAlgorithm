package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1766_문제집 {
    private static int N, M;
    private static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int index = 0; index <= N; index++) {
            graph.add(new ArrayList<>());
        }


        int[] indegree = new int[N + 1];
        for (int loop = 0; loop < M; loop++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            indegree[to]++;
        }

        int[] rst = solveHomework(indegree);
        for (int x : rst) {
            System.out.print(x + " ");
        }
        br.close();
    }

    private static int[] solveHomework(int[] indegree) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int index = 1; index <= N; index++) {
            if(indegree[index] == 0) pq.add(index);
        }

        List<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            int work = pq.poll();
            list.add(work);

            for (Integer nextWork : graph.get(work)) {
                indegree[nextWork]--;
                if(indegree[nextWork] == 0) pq.add(nextWork);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
