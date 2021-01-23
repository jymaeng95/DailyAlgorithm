package com.algorithm.Programmers.Lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_49189_가장_먼_노드 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        int solution = solution(n, edge);
        System.out.println(solution);
    }
    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] visited;
    private static int[] dist;
    public static int solution(int n, int[][] edge) {
        graph = new ArrayList<>();
        for(int i =0;i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] eg :  edge){
            graph.get(eg[0]).add(eg[1]);
            graph.get(eg[1]).add(eg[0]);
        }
        visited = new boolean[n+1];
        dist = new int[n+1];
        bfs();

        Arrays.parallelSort(dist);
        int max = dist[dist.length-1];
        int count = 0;
        for(int i =0;i<dist.length;i++) {
            if(dist[i] == max)
                count++;
        }
        return count;
    }

    private static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        while(!que.isEmpty()) {
            int node = que.poll();
            if(visited[node])
                continue;
            visited[node] = true;
            for(int nd : graph.get(node)){
                que.offer(nd);
                if(nd != 1) {
                    if (dist[nd] == 0)
                        dist[nd] = dist[node] + 1;
                    else
                        dist[nd] = Math.min(dist[nd], dist[node] + 1);
                }
            }
        }
    }
}
