package com.algorithm.thisiscodingtest.DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P142_DFS_Example {
    private static List<ArrayList<Integer>> list;
    public static void main(String[] args) {
        list = new ArrayList<>();
        for(int i =1; i<=8;i++) {
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

        list.get(6).add(5);;
        list.get(6).add(7);

        list.get(7).add(0);
        list.get(7).add(6);

        boolean[] visited = new boolean[8];

        dfs(0,visited);
    }

    private static void dfs(int i, boolean[] visited) {
        // 방문 처리
        visited[i] = true;
        System.out.print(i+1+" ");
        for(int x : list.get(i)){
            if(!visited[x]) {
                dfs(x,visited);
            }
        }
    }

}
