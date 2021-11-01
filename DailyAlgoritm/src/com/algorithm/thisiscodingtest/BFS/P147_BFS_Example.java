package com.algorithm.thisiscodingtest.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P147_BFS_Example {
    private static List<ArrayList<Integer>> list;

    public static void main(String[] args) {
        list = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            list.add(new ArrayList<>());
        }

        list.get(0).add(1);
        list.get(0).add(2);
        list.get(0).add(7);

        list.get(1).add(0);
        list.get(1).add(6);

        list.get(2).add(3);
        list.get(2).add(4);

        list.get(3).add(2);
        list.get(3).add(4);

        list.get(4).add(2);
        list.get(4).add(3);

        list.get(5).add(6);

        list.get(6).add(5);
        ;
        list.get(6).add(7);

        list.get(7).add(0);
        list.get(7).add(6);

        boolean[] visited = new boolean[8];

        bfs(0, visited);

    }
    private static void bfs ( int i, boolean[] visited){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        visited[i] = true;
        while(!queue.isEmpty()) {
            int x = queue.poll();
            System.out.print(x+1+" ");
            for(int y : list.get(x)){
                if(!visited[y]) {
                    queue.add(y);
                    visited[y] = true;
                }
            }

        }
    }
}