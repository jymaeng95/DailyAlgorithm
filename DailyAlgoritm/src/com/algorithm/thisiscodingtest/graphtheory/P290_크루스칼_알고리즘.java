package com.algorithm.thisiscodingtest.graphtheory;

import java.io.*;
import java.util.*;

public class P290_크루스칼_알고리즘 {
    static class Edge {
        private int vertexA;
        private int vertexB;
        private int dist;

        public Edge(int vertexA, int vertexB, int dist) {
            this.vertexA = vertexA;
            this.vertexB = vertexB;
            this.dist = dist;
        }
    }
    private static int V,E;
    private static List<Edge> edge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int[] parent = new int[V+1];
        for(int i =1;i<=V;i++) {
            parent[i] = i;
        }
        edge = new ArrayList<>();

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int vertexA = Integer.parseInt(st.nextToken());
            int vertexB = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edge.add(new Edge(vertexA, vertexB, dist));
        }

        Collections.sort(edge, Comparator.comparingInt(o -> o.dist));
        int cost = 0;
        for(Edge eg : edge) {
            int a = eg.vertexA;
            int b = eg.vertexB;
            int dist = eg.dist;

            if(find(a, parent) != find(b,parent)) {
                union(a,b,parent);
                bw.write(a +"->"+b + ":"+ dist);
                bw.newLine();
                cost += dist;
            }
        }
        bw.write(String.valueOf(cost));
        bw.flush();
        br.close();
        bw.close();
    }

    private static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);
        if(a > b) parent[a] = b;
        else parent[b] = a;
    }

    private static int find(int x, int[] parent) {
        if(parent[x] != x)
            parent[x] = find(parent[x], parent);
        return parent[x];
    }
}
