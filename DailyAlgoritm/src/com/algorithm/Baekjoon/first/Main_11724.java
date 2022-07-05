package com.algorithm.Baekjoon.first;

import java.io.*;
import java.util.*;

public class Main_11724 {
    /*
    방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
     */
    public static List<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[vertex+1];
        int count = 0;
        graph = new ArrayList<>();

        for(int i=0;i<=vertex;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<edge;i++){
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            graph.get(startNode).add(endNode);
            graph.get(endNode).add(startNode);
        }

        for(int i=1;i<=vertex;i++){
            if(!visited[i]) {
                bfs(i, visited);
                count++;
            }
        }
        bw.write(String.valueOf(count));
        br.close();
        bw.close();
    }

    private static void bfs(int i,boolean[] visited) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(i);
        while(!que.isEmpty()){
            int index = que.poll();
            if (visited[index])
                continue;
            visited[index] = true;
            for(int v : graph.get(index)){
                que.offer(v);
            }

        }
    }
}
