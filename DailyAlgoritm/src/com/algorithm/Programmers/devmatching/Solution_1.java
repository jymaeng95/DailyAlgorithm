package com.algorithm.Programmers.devmatching;

import java.util.*;

public class Solution_1 {
    public static void main(String[] args) {
        int n = 8;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {4, 0}, {5, 1}, {6, 1}, {7, 2}, {7, 3}, {4, 5}, {5, 6}, {6, 7}};
        int k = 4;
        int a = 0;
        int b = 3;
        int rst = solution(n, edges, k, a, b);
        System.out.println(rst);
    }

    static class Line {
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return start == line.start && end == line.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
    private static List<List<Integer>> graph;
    private static Stack<Integer> stack;
    private static Set<Line> lineSet;
    private static boolean[] visited;
    public static int solution(int n, int[][] edges, int k, int a, int b) {
        int answer = -1;
        // 송전선 연결
        graph = new ArrayList<>();
        for(int vertex = 0; vertex <= n; vertex++) {
            graph.add(new ArrayList<>());
        }

        for(int edge = 0; edge < edges.length; edge++) {
            int towerA = edges[edge][0];
            int towerB = edges[edge][1];

            graph.get(towerA).add(towerB);
            graph.get(towerB).add(towerA);
        }

        // 경로 초기화
        lineSet = new HashSet<>();
        visited = new boolean[n+1];
        stack = new Stack<>();
        // DFS를 통해 탐색 목적지까지 탐색
        // 시작 a, 종료 b, 가중치 k이하 weight= 0

        searchPath(a, b, k, 0);

        return lineSet.size()/2;
    }

    private static void searchPath(int start, int end, int k, int weight) {
        if(start == end) {
            stack.add(end);
            // 스택에서 패스 지나온 패스 확인
            if(weight <= k) {
                if(!stack.isEmpty()) {
                    int cur = stack.get(0);
                    for(int size = 1; size < stack.size(); size++) {
                        int next = stack.get(size);
                        lineSet.add(new Line(cur, next));
                        lineSet.add(new Line(next, cur));

                        // cur = next로 바꾸기
                        cur = next;
                    }
                }
            }
            return;
        }

        visited[start] = true;
        stack.add(start);
        for(int next : graph.get(start)) {
            if(!visited[next]) {
                searchPath(next, end, k, weight+1);
                visited[next] = false;
                stack.pop();
            }
        }
    }
}
