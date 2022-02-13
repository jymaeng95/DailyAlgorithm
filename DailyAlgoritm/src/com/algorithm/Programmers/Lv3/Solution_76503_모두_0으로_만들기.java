package com.algorithm.Programmers.Lv3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_76503_모두_0으로_만들기 {
    public static void main(String[] args) {
//        int[] a = {-2, 8, -5, -5, -3, 0, 5, 2};
//        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 6}, {2, 7}};
//        int[] a = {2,-2};
//        int[][] edges = {{0,1}};
//        int[] a = {-5,0,2,1,2};
//        int[][] edges = {{0,1},{3,4},{2,3},{0,3}};
        int[] a = {-1, 0, -8, 2,2,1,1,1,1,1};
        int[][] edges = {{0,1},{2,0},{3,2},{2,4},{3,5},{6,3},{7,3},{8,4},{4,9}};
        long solution = solution(a, edges);
        System.out.println("solution = " + solution);
    }

    static class Node {
        private int num;
        private long weight;
        private List<Node> children = new ArrayList<>();
        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        public int getNum() {
            return num;
        }

        public long getWeight() {
            return weight;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setWeight(long weight) {
            this.weight = weight;
        }
    }
    private static Map<Integer, Node> tree;
    private static long count;
    private static long solution(int[] a, int[][] edges) {
        // 합이 0 이 되지 않으면 실패
        long answer = 0;
        for(int x : a) {
            answer += x;
        }
        if(answer != 0) return -1;

        tree = new HashMap<>();
        for(int [] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            if(tree.containsKey(parent) || (!tree.containsKey(parent) && !tree.containsKey(child))) makeTree(parent,child,a);
            else makeTree(child, parent,a);
        }

        dfs(0, a);
        return count;
    }

    private static long dfs(int start, int[] a) {
        if(tree.get(start).getChildren().size() < 1) {
            count += Math.abs(a[start]);
            return a[start];
        }
        long cnt = tree.get(start).getWeight();

        for(Node child : tree.get(start).getChildren()) {
            cnt += dfs(child.getNum(), a);
        }

        tree.get(start).setWeight(cnt);
        count += Math.abs(tree.get(start).getWeight());
        return tree.get(start).getWeight();
    }

    private static void makeTree(int parent, int child, int[] a) {
        Node parentNode, childNode;
        if(tree.containsKey(parent)) parentNode = tree.get(parent);
        else parentNode = new Node(parent, a[parent]);
        if(tree.containsKey(child)) childNode = tree.get(child);
        else childNode = new Node(child, a[child]);

        parentNode.getChildren().add(childNode);

        tree.put(parent, parentNode);
        tree.put(child, childNode);
    }
}
