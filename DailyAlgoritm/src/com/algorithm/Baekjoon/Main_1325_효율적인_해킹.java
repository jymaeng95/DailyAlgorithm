package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1325_효율적인_해킹 {
    private static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int vertex = 0; vertex <= N; vertex++) {
            graph.add(new ArrayList<>());
        }

        for(int edge = 0; edge < M; edge++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(to).add(from);
        }

        String rst = getHackingComputerCount(N, M);
        System.out.println(rst);
        br.close();
    }

    private static StringBuilder sb;
    private static int[] hacking;
    private static String getHackingComputerCount(int n, int m) {
        sb = new StringBuilder();
        hacking = new int[n + 1];
        for(int computer = 1; computer <= n ; computer++) {
//          hacking[computer] = hackingComputer(computer, n);
            boolean[] visited = new boolean[n + 1];
            hackingComputer(computer, computer, visited);
        }

        int maxCount = 0;
        for (int count : hacking) {
            maxCount = Math.max(count, maxCount);
        }

        for(int computer = 1; computer <= n; computer++) {
            if(maxCount == hacking[computer]) sb.append(computer).append(" ");
        }
        return sb.toString();
    }

    // bfs
    private static int hackingComputer(int computer, int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(computer);
        visited[computer] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            int hackedComputer = queue.poll();

            for (Integer nextComputer : graph.get(hackedComputer)) {
                if(!visited[nextComputer]) {
                    visited[nextComputer] = true;
                    queue.add(nextComputer);
                    count++;
                }
            }
        }
//        maxCount = Math.max(maxCount, count);
        return count;
    }
    //dfs
    private static void hackingComputer(int start, int computer, boolean[] visited) {
        for (int next : graph.get(computer)) {
            if(!visited[next]) {
                visited[next] = true;
                hackingComputer(start, next, visited);
                hacking[start]++;
            }
        }
    }
}
