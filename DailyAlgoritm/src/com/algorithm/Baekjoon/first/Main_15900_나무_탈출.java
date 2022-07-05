package com.algorithm.Baekjoon.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15900_나무_탈출 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int vertex = 1; vertex <= N; vertex++) {
            tree.put(vertex, new ArrayList<>());
        }

        for (int edge = 1; edge < N; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        String rst = checkWinEscapeTree(1, tree, N);
        System.out.println(rst);
        br.close();
    }

    private static int count;
    private static String checkWinEscapeTree(int root, Map<Integer, List<Integer>> tree, int n) {
        count = 0;
        boolean[] visited = new boolean[n + 1];

        visited[root] = true;
        playEscapeTree(root, tree, visited, 0);

        // 짝수번 진행한 경우 성원이가 다음에 놓을 말이 없음, 홀수인 경우 성원이 승
        return count % 2 == 0 ? "No" : "Yes";
    }

    private static void playEscapeTree(int node, Map<Integer, List<Integer>> tree, boolean[] visited, int turn) {
        // 아직 방문하지 않았으면 서 연결된 노드가 부모밖에 없다면 리프 노드
        if (!visited[node] && tree.get(node).size() == 1) {
            count += turn;

            return;
        }

        // 현재 노드 방문 처리
        visited[node] = true;
        for (int child : tree.get(node)) {
            if (!visited[child]) {
                playEscapeTree(child, tree, visited, turn + 1);
            }
        }
    }
}
