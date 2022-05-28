package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2644_촌수계산 {
    private static Map<Integer, List<Integer>> tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 가족 족보 트리 초기화
        tree = new HashMap<>();
        for(int person = 1; person <= N; person++) {
            tree.put(person, new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());

        for(int relation = 0; relation < M; relation++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            // 관계 설정
            tree.get(parent).add(child);
            tree.get(child).add(parent);
        }

        int rst = calculateRelation(N, start, end);
        System.out.println(rst);

        br.close();
    }

    private static int relation;
    private static int calculateRelation(int n, int start, int end) {
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        relation = -1;      // 관걔 설정 안되는 경우 -1이므로 초기화

        relation(start,end, 0, visited);
        return relation;
    }

    private static void relation(int vertex, int end, int count, boolean[] visited) {
        // 현재 관계가 찾고자 하는 end를 만나는 경우 = 촌수 계산이 가능한 경우
        if(vertex == end) {
            relation = count;
            return;
        }

        // 트리 구조이므로 다시 방문하지 않도록
        for (Integer next : tree.get(vertex)) {
            if(!visited[next]) {
                visited[next] = true;
                relation(next, end, count + 1, visited);
            }
        }
    }
}
