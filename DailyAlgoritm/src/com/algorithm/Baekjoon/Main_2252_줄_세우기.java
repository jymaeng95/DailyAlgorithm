package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2252_줄_세우기 {
    private static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for(int number = 0; number <= N; number++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[N+1];
        for(int order = 0; order < M; order++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            graph.get(front).add(back);
            indegree[back]++;
        }

        String order = makeOrder(indegree, N);
        System.out.println(order);
        br.close();
    }

    private static String makeOrder(int[] indegree, int n) {
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder order = new StringBuilder();
        for(int student = 1; student <= n; student++) {
            if(indegree[student] == 0) queue.add(student);
        }
        while(!queue.isEmpty()) {
            int student = queue.poll();
            order.append(student).append(" ");

            for(int behind : graph.get(student)) {
                indegree[behind]--;
                if(indegree[behind] == 0) queue.add(behind);
            }
        }
        return order.toString();
    }
}
