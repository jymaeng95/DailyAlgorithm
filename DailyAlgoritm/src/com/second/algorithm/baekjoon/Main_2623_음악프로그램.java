package com.second.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2623_음악프로그램 {
    private static int N, M;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        int[] indegree = new int[N + 1];
        for (int index = 0; index <= N; index++) {
            graph.add(new ArrayList<>());
        }

        // 순서 정보 저장
        for (int pd = 0; pd < M; pd++) {
            st = new StringTokenizer(br.readLine());
            int singerCount = Integer.parseInt(st.nextToken());
            int singer = 0;
            for (int index = 0; index < singerCount; index++) {
                int nextSinger = Integer.parseInt(st.nextToken());

                if (singer != 0) {
                    graph.get(singer).add(nextSinger);
                    indegree[nextSinger]++;
                }
                singer = nextSinger;
            }
        }

        int[] rst = startMusicBank(indegree);
        Arrays.stream(rst).forEach(System.out::println);
        br.close();
    }

    private static int[] startMusicBank(int[] indegree) {
        Queue<Integer> queue = new LinkedList<>();
        for (int singer = 1; singer <= N; singer++) {
            if (indegree[singer] == 0) queue.add(singer);
        }

        List<Integer> orders = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer singer = queue.poll();
            orders.add(singer);
            for (Integer nextSinger : graph.get(singer)) {
                indegree[nextSinger]--;
                if(indegree[nextSinger] == 0) {
                    queue.add(nextSinger);
                }
            }
        }

        if(orders.size() < N) return new int[]{0};
        return orders.stream().mapToInt(Integer::intValue).toArray();
    }
}
