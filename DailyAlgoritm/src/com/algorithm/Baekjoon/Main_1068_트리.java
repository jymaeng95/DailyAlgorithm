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
        for (int node = 0; node < N; node++) {
            tree.put(node, new LinkedList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int rootNode = 0;
        for (int node = 0; node < N; node++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1) {
                tree.get(parent).add(node);
            } else {
                rootNode = node;
            }
        }

        int removeNode = Integer.parseInt(br.readLine());
        int leafNode = getLeafNode(N, rootNode, tree);

        br.close();
    }

    private static int getLeafNode(int size, int root, Map<Integer, List<Integer>> tree) {


        return 0;
    }
}
