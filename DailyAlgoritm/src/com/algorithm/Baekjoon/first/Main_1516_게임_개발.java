package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1516_게임_개발 {

    private static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] indegree = new int[N+1];
        int[] second = new int[N+1];
        graph = new ArrayList<>();
        for(int i=0;i<N;i++) {
            graph.add(new LinkedList<>());
        }
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            second[i+1] = time;
            while(st.hasMoreTokens()) {
                int end = Integer.parseInt(st.nextToken());
                if(end == -1) break;
                graph.get(i).add(end);
                indegree[i+1]++;
            }
        }

        int[] rst = new int[N];
        developGame(N, second, indegree, rst);
        for(int x : rst) {
            System.out.println("x = " + x);
        }
        br.close();
    }

    private static void developGame(int n, int[] second, int[] indegree, int[] rst) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<indegree.length;i++ ) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }

        int i=0;
        while(!queue.isEmpty()) {
            int vertex = queue.poll();
            for(int v : graph.get(vertex)) {
                indegree[v]--;
                if(indegree[v] == 0) {
                    queue.add(v);
                    rst[v] = second[v]+second[vertex];
                }
            }
        }
    }
}
