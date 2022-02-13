package com.algorithm.Programmers.Lv3;

import java.util.ArrayList;
import java.util.List;

public class Solution_76503_모두0으로_만들기 {

    public static void main(String[] args) {
        int[] a = {-2, 8, -5, -5, -3, 0, 5, 2};
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 6}, {2, 7}};
//        int[] a = {2,-2};
//        int[][] edges = {{0,1}};
//        int[] a = {-5,0,2,1,2};
//        int[][] edges = {{0,1},{3,4},{2,3},{0,3}};
//        int[] a = {-1, 0, -8, 2,2,1,1,1,1,1};
//        int[][] edges = {{0,1},{2,0},{3,2},{2,4},{3,5},{6,3},{7,3},{8,4},{4,9}};
        long solution = solution(a, edges);
        System.out.println("solution = " + solution);
    }


    private static List<List<Integer>> tree;
    private static long[] time;
    private static long count;

    private static long solution(int[] a, int[][] edges) {
        tree = new ArrayList<>();
        time = new long[a.length];
        boolean[] visited = new boolean[a.length];
        long answer = 0;
        for (int i = 0; i < a.length; i++) {
            answer += a[i];
            time[i] = a[i];
            tree.add(new ArrayList<>());
        }
        if (answer != 0) return -1;
        for (int[] edge : edges) {
            int fr = edge[0];
            int to = edge[1];

            tree.get(fr).add(to);
            tree.get(to).add(fr);
        }

        dfs(0, a, visited);
        return count;
    }

    private static long dfs(int start, int[] a, boolean[] visited) {
        if (visited[start]) return 0;
        if (tree.get(start).size() < 1) {
            count += Math.abs(a[start]);
            return a[start];
        }
        visited[start] = true;
        long cnt = time[start];
//        for(int to : tree.get(start)) { //찾아보니 enhanced for loop -> 런타임 에러
        for (int i = 0; i < tree.get(start).size(); i++) {
            if(!visited[tree.get(start).get(i)])
                cnt += dfs(tree.get(start).get(i), a, visited);
        }
        time[start] = cnt;
        count += Math.abs(time[start]);
        return time[start];
    }
}
