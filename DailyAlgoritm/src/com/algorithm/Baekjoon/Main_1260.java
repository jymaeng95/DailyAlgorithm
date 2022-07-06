package com.algorithm.Baekjoon;

import java.io.*;
import java.util.*;

public class Main_1260 {
    /*
    첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다.
    다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다.
    어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.
    */
    public static ArrayList<LinkedList<Integer>> node;
    public static StringBuilder dfs(int start,boolean[] visited){
        StringBuilder sb = new StringBuilder();
        dfsUtil(start,visited,sb);
        return sb;
    }

    public static void dfsUtil(int start, boolean[] visited,StringBuilder sb) {
        if (visited[start]) return;
        sb.append(start + " ");
        visited[start] = true;
        for (int x : node.get(start)) {
            dfsUtil(x, visited, sb);
        }
    }

    public static StringBuilder bfs(int start,boolean[] visited){
        StringBuilder sb = new StringBuilder();

        Queue<Integer> qu = new LinkedList<>();
        qu.offer(start);
        while(!qu.isEmpty()){
            start = qu.poll();
            if(visited[start]) {
                continue;
            }
            visited[start] = true;
            sb.append(start+" ");
            for(int x : node.get(start)){
                qu.offer(x);
            }
        }
        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        node = new ArrayList<LinkedList<Integer>>();

        for(int i=0;i<=vertex;i++){
            node.add(new LinkedList<>());
        }
        for(int i=0;i<edge;i++){
            st = new StringTokenizer(br.readLine());
            int nodeFirst = Integer.parseInt(st.nextToken());
            int nodeSecond = Integer.parseInt(st.nextToken());

            node.get(nodeFirst).add(nodeSecond);
            node.get(nodeSecond).add(nodeFirst);

            Collections.sort(node.get(nodeFirst));
            Collections.sort(node.get(nodeSecond));
        }


        boolean[] dfsVisited = new boolean[vertex+1];
        boolean[] bfsVisited = new boolean[vertex+1];

        StringBuilder dfsSb = dfs(start,dfsVisited);
        StringBuilder bfsSb = bfs(start, bfsVisited);

        bw.write(dfsSb.toString());
        bw.newLine();
        bw.write(bfsSb.toString());

        bw.close();
        br.close();
    }
}
