package com.algorithm.Programmers.Lv3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_49191_순위 {
    public static void main(String[] args) {
        int n = 5;
        int[][] results ={{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        int rst = solution(n, results);
        System.out.println("rst = " + rst);
    }
    private static List<List<Integer>> graph;
    private static int solution(int n, int[][] results) {
        int answer = 0;
        int[] indegree = new int[n+1];
        graph = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0;i< results.length;i++) {
            int win = results[i][0];
            int lose = results[i][1];
            graph.get(lose).add(win);
            indegree[win]++;
        }
        int[] rst = getRanking(indegree, n, results);
        for(int x : rst)
            System.out.println("x = " + x);
        return answer;
    }

    private static int[] getRanking(int[] indegree, int n, int[][] results) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=n;i++) {
            if(indegree[i] == 0)
                queue.add(i);
        }
        int[] rst = new int[n];
        int i =0;
        while(!queue.isEmpty()) {
            int lose = queue.poll();
            rst[i++] = lose; 
            for(int win : graph.get(lose)) {
                indegree[win]--;
                if(indegree[win] == 0) {
                    queue.add(win);
                }
            }
        }
        return rst;
    }
}
