package com.algorithm.Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1068_트리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> tree = new HashMap<>();
        // 트리 초기화
        for (int node = 0; node < N; node++) {
            tree.put(node, new LinkedList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int rootNode = 0;

        // 트리 연결
        for (int node = 0; node < N; node++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) {
                tree.get(parent).add(node);
            } else {
                rootNode = node;
            }
        }

        // 제거할 노드
        int removeNode = Integer.parseInt(br.readLine());
        leaf = 0;

        getLeafNode(rootNode, removeNode, tree);
        System.out.println(leaf);

        br.close();
    }

    private static int leaf;

    private static void getLeafNode(int start, int remove, Map<Integer, List<Integer>> tree) {
        // 삭제할 노드 도달 시 이후는 DFS 안함
        if (start == remove) {
            return;
        }

        // 연결된 노드가 없으면 리프노드
        if (tree.get(start).size() < 1) {
            leaf++;
            return;
        }

        // 연결된 노드 방문 처리 및 자식 노드 방문
        for (int next : tree.get(start)) {
            if(next == remove && tree.get(start).size() == 1) {
                leaf++;
            }
            getLeafNode(next, remove, tree);
        }
    }
}
