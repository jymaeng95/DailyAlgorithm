package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1922 {
    static class Computer implements Comparable<Computer> {
        private int node;
        private int distance;

        public Computer(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Computer o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    private static int N,M;
    private static ArrayList<ArrayList<Computer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        boolean[] visited = new boolean[N];

        for(int i=0;i<N;i++){
            graph.add(new ArrayList<>());
        }

        //그래프 구성하기
        for(int j=0;j<M;j++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int distance = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Computer(end,distance));
            graph.get(end).add(new Computer(start,distance));
        }

        int dist = mst(visited);
        bw.write(String.valueOf(dist));
        br.close();
        bw.close();
    }

    private static int mst(boolean[] visited) {
        //우선순위 큐를 이용해 최소 거리를 가져와 사용
        PriorityQueue<Computer> pq = new PriorityQueue<>();
        pq.add(new Computer(0,0));
        int distance = 0;
        int count = 0;
        while(!pq.isEmpty()){
            Computer computer = pq.poll();
            if(visited[computer.node])
                continue;
            distance += computer.distance;
            visited[computer.node] = true;
            for(Computer cp : graph.get(computer.node)) {
                if (!visited[cp.node])
                    pq.add(cp);
            }
            count++;
            //컴퓨터의 개수만큼 큐를 돌린 경우 break
            if(count == N)
                break;
        }
        return distance;
    }
}
