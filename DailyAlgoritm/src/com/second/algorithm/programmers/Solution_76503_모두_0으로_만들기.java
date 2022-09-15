package com.second.algorithm.programmers;

import java.util.*;

public class Solution_76503_모두_0으로_만들기 {
    public static void main(String[] args) {
//        int[] a = {-5,0,2,1,2};
        int[] a = {5, -5, 5, 0, -10, 10, -5};
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}};

        long rst = solution(a, edges);
        System.out.println("rst = " + rst);
    }

    private static long solution(int[] a, int[][] edges) {
        // 가중치 합계가 0이 아닌 경우 0으로 만들기 불가능
        boolean allZero = true;
        long sum = 0;
        long[] weights = new long[a.length];
        int index = 0;
        for (int weight : a) {
            if(weight != 0 && allZero) allZero = false;
            sum += weight;
            weights[index++] = weight;
        }

        if (sum != 0) return -1;
        if(allZero) return 0;

        int[] connection = new int[a.length];   // 연결 개수 배열

        // 트리 초기화
        initTree(a.length, edges, connection);

        return makeZero(weights, connection);
    }

    private static long makeZero(long[] weights,  int[] connection) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] check = new boolean[weights.length];

        // 가중치가 0이 아니면서 connection이 1인 (리프노드)넣기
        for (int vertex = 0; vertex < weights.length; vertex++) {
            if (weights[vertex] != 0 && connection[vertex] == 1) {
                connection[vertex]--;
                queue.add(vertex);
            }
        }

        long count = 0;
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            check[vertex] = true;

            for (Integer nextVertex : tree.get(vertex)) {
                if (!check[nextVertex]) {
                    // 연산 진행
                    count += Math.abs(weights[vertex]);
                    weights[nextVertex] += weights[vertex];
                    weights[vertex] = 0;

                    // connection 하나 줄이기
                    connection[nextVertex]--;

                    // connection이 1개이거나 0이 아닌 경우만 queue에 넣기
                    if (connection[nextVertex] == 1 && weights[nextVertex] != 0) {
                        queue.add(nextVertex);
                    }
                }
            }
        }

        return count;
    }

    private static Map<Integer, List<Integer>> tree;

    private static void initTree(int count, int[][] edges, int[] connection) {
        tree = new HashMap<>();

        // vertex 초기화
        for (int vertex = 0; vertex < count; vertex++) {
            tree.put(vertex, new ArrayList<>());
        }

        // edge 입력
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);

            connection[edge[0]]++;
            connection[edge[1]]++;
        }
    }
}
