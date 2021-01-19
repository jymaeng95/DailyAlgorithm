package com.algorithm.Programmers.Lv3;

import java.util.ArrayList;

public class Solution_43162_네트워크 {
    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int solution = solution(n, computers);
        System.out.println(solution);
    }

    private static ArrayList<ArrayList<Integer>> network;
    private static boolean[] visited;
    public static int solution(int n, int[][] computers) {
        network = new ArrayList<>(n);
        visited = new boolean[n];
        for(int i=0;i<n;i++){
            network.add(new ArrayList<>());
        }

        for(int i=0;i<computers.length;i++){
            for(int j = 0;j<computers[i].length;j++){
                if(computers[i][j] == 1)
                    network.get(i).add(j);
            }
        }
        int count = 0;
        for(int i =0;i<n;i++) {
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int index) {
        if(visited[index])
            return;
        visited[index] = true;
        for(int nw : network.get(index)){
            dfs(nw);
        }
    }
}
