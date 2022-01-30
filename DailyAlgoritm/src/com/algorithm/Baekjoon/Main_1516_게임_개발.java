package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1516_게임_개발 {
    static class Node {
        private int start;
        private int end;
        private int time;

        public Node(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getTime() {
            return time;
        }
    }
    private static List<Node> edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] parent = new int[N+1];
        int[] time = new int[N+1];
        for(int i=0;i<N+1;i++) {
            parent[i] = i;
        }

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(end == -1) end = i+1;
            time[i+1] = second;
            edge.add(new Node(i+1, end, second));
        }

        int[] rst = new int[N+1];
        developGame(N, time, parent, rst);
        br.close();
    }

    private static void developGame(int n, int[] time, int[] parent, int[] rst) {
        for(Node node : edge) {
            int start = node.getStart();
            int end = node.getEnd();
            int second = node.getTime();

//            if(find(start, parent, time) != find(end,parent, time)) {
//                union()
//            }
        }

    }

    private static int find(int x, int second, int[] parent, int[] time) {
        if(parent[x] != x) {
            parent[x] = find(x, second, parent, time);
        }
        return parent[x];
    }
}
